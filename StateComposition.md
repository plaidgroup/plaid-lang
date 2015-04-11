# Introduction #

Plaid aims to have a simple and flexible composition mechanism to provide important properties such as separate development, software evolution, and component reuse.  These mechanisms are still in their infancy and are an area of active research.  One of the main challenges with composition and particularly modular composition is its interaction with state change.  The current version of Plaid implements some basic ideas that we hope will provide a testbed for further development of the system.  This tutorial aims to explain what is currently implemented through examples.  Feedback on the system, especially by those who have used it, is most welcome and appreciated.

# Composition, State Change, and Definition Location #

Plaid's composition model is designed to allow programmers to flexibly reuse states by combining them in novel ways.  While there has already been extensive research into the theory behind composition, Plaid adds an extra twist with the state change operator.  State change updates an object's composition.  In particular, it realizes the transition between two states in a protocol.  However, in a world with composition, we can define objects which have additional members outside their protocol, or even participate in multiple protocols.  In order to remain modular and avoid customization of a protocol for each context it might be used in (what else it might be composed with), it becomes evident that state change must not change members outside of the protocol.

These considerations motivated the restricted update semantics for state change described informally in the StateChangeSemantics tutorial.  We more formally describe the algorithm here:

For a state change x <- S where x consists at least partly of a state T which is in the same protocol hierarchy as S, the operation takes the following actions on the object x:

  1. e remove all the members from the existing object defined in T and its ancestors, except those states which are also ancestors of S.
  1. e get rid of the tag associated with T.
  1. e add all members from S that are not defined in the common ancestors of S and T.
  1. e add the tag associated S.

An important thing to note is that the semantics of state change depend heavily on where members are defined.  Thus, the definition location is something we must keep track of at runtime (in the lingo of the Traits community, we cannot have flattening).  In simple cases, this is easy.  But as we add abstract members and overriding to the language, it becomes significantly more complicated, as we will see.

# Plaid Composition #
In this section, we will describe Plaid's composition model and the syntax which supports it.  The several different ways of inducing composition differ only in where we record members as being defined when the states are ultimately instantiated at runtime.

## Units of Composition ##

The basic unit of composition in Plaid is a state.  As we have seen previously, states are much like classes in that they are a collection of members (fields and methods).  States can be defined both by giving the collection a name as in a state declaration:

```
state Foo {
    val f = 1;
    method m() { f } 
}
```

or anonymously as in
```
val bar = new { val g = 99; method n() { this.g } };`
```
where the value `bar` will be an instance of the anonymous state in the curly braces after the `new` keyword.  (Note that the `this.g` is required in the anonymous method definition where as not in the `f()` method of the state definition.  This is due to the way that scoping is handled in the current implementation (July 2010).)

The definition location in each case is clear.  `f` and `m()` are defined in the state `Foo` where as `g` and `n()` are defined anonymously.

## Basic Composition ##

Composition in Plaid is invoked using the `with` keyword.  `with` operates on two states and produces a new state.  For instance, we could compose the state `Foo` with our anonymous state when instantiating it:

```
val baz = new Foo with { val g = 99; method n() { this.g } };
```

Composition is symmetric, so we would get the same object if instead we wrote:

```
val baz = new { val g = 99; method n() { this.g } } with Foo;
```

The object in question will have the following members:

  * `f` - an immutable field defined in `Foo`
  * `g` - an immutable field defined anonymously
  * `m()` - a method defined in `Foo`
  * `n()` - a method defined anonymously

Alternatively, we could name the composition before instantiating it by declaring the state as itself a composition:

```
state FooBar = Foo with { val g = 99; method n() { g } }
```

This changes the definition locations.  Now, the members coming from `Foo` are as before, but the anonymous definitions are now associated with the `FooBar` state.  Thus, the members are:

  * `f` - an immutable field defined in `Foo`
  * `g` - an immutable field defined in `FooBar`
  * `m()` - a method defined in `Foo`
  * `n()` - a method defined in `FooBar`

## Protocol Hierarchies ##

There are times when simply declaring a new state to be a composition of two states is not adequate.  In particular, when defining a protocol, it can be useful to test whether a given object is in a given state.  For this purpose, we can define states as a `case of` another state.  The `case of` operator also induces a composition, recognizing that typically sub-states retain the functionality defined in their parents.  For instance, take a simple hierarchy that represents a reader of some data source which cannot be read past the end of file character:

```
state Reader { val location; }

state HasData case of Reader {
	val data;
	method read() {
		val next = data.getNext();
		if (next == EOF) { this <- EOF };
		next
	}
}

state EOF case of Reader { }
```

The basic `Reader` defines a field, `location` which records where the data comes from (recall the `filename` field from the File example).  As `HasData` is a `case of` reader, it includes the `location` field because its actually definition is a composition of `Reader` and the declarations of `HasData`.

Similar to the composition of `FooBar`, members composed from existing named states keep their definition location.  Thus, the state `HasData` has the following members:

  * `location` - an immutable field defined in `Reader`
  * `data` - an immutable field defined in `HasData`
  * `read()` - a method defined in `HasData`

## Initialization and Other Means of Providing Abstract Members ##

The description of the members of the `HasData` state leaves out a critical piece of information, namely that `location` and `data` are abstract fields that must be initialized.  There are several ways that we might want to provide those values, and in particular where we want the values to ultimately be defined in terms of the runtime semantics of state change.

### Initialization ###

In many cases, we may simply want the provided members to act as if they were defined and provided in the place where the abstract declaration was made.  For that purpose, Plaid has the notion of an initializing state.  These follow directly after a named state as in the following code snippet:

```
val reading = new HasData { location = "test.txt"; data = ...; } 
```

The `location` and `data` fields which are abstract in the `HasData` state are provided.  Furthermore, the instantiated object will reflect the fact that those members are defined where they were originally declared abstract:

  * `location`="test.txt" - an immutable field defined in `Reader`
  * `data`=... - an immutable field defined in `HasData`
  * `read()` - a method defined in `HasData`

### Other States ###

In some cases, we might want a different state to provide the value for a field or a method implementation.  We could write the above initialization slightly differently:

```
val reading = new HasData with { location = "test.txt"; data = ...; } 
```

The additional `with` changes the semantics from an initialization of the `HasData` state (which induces a specialized composition) to a composition of `HasData` with an anonymous state that provides two fields that were previously abstract.  The distinction is that the provided fields are in this case anonymous and are not part of the protocol hierarchy.  Thus, they would not ever be removed by a restricted update state change.  This could be desirable, or not desirable depending on the context.  Explicitly, the members of this object are

  * `location`="test.txt" - an immutable field defined anonymously
  * `data`=... - an immutable field defined in anonymously
  * `read()` - a method defined in `HasData`

You could also declare a named state which includes the `location` and `data` fields with values and compose that with `HasData` to get a similar effect where these members were defined in a different non-anonymous state.

## Overriding ##

In some cases, we might want to define extensions to states or hierarchies which replace or augment existing members.  For example, we might want to enhance our `Reader` to handle more complex data structures which need to be cleaned up when they have reached the end of file.  We have three options which vary to a small degree:

### Define a New State and Override ###

To this end, we could create a new state which overrides the `read()` as follows:
```
state HasDataFromFile case of HasData {
	
	override method read() {
		val next = data.getNext();
		if (next == EOF) { 
			data.closeFile();
			this <- EOF
		};
		next
	}
}
```
The `override` keyword indicates that the previous definition given in `HasData` will be replaced by the definition in `HasDataFromFile`.  Additionally, the new implementation of `read()` will be marked as defined in `HasDataFromFile`.

### Override During Initialization ###
We could also achieve this effect without defining a new state.  We could simply override the read function at the time of instantiation of `HasData` using the initialization state:

```
val dataObj = ...;
val reading = new HasData { 
	location = "test.txt";
	data = dataObj; 
	override method read() { /* same as in HasDataFromFile, modulo some explicit
                                    'this' qualifiers */ }
}
```

Here the overriden `read()` method is declared as defined in `HasData`, which is the same as its original definition.

### Override from another State ###

Finally, we might want to define the `read()` method outside of the `Reader` protocol hierarchy.  Say in our application, we want to customize the `Reader` to always have the `read()` method available and have it always return the end of file symbol after it is at the end.  We could instead override `read()` in an anonymous state during initialization:

```
val dataObj = ...;
val reading = new HasData { location = "test.txt"; data = dataObj; } with { 
	override method read() { /* same as in HasDataFromFile, modulo some explicit
                                    'this' qualifiers */ }
}
```

Now, when this object is instantiated, the `read()` method is no longer provided within the hierarchy and so the member would be marked as defined in an anonymous state and thus would not be updated by a state change within the `Reader` hierarchy.

To be continued...