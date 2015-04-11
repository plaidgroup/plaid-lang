# Introduction #

At the core of Plaid's ability to model protocols through typestate is the state change operation by which object transition from one state to another.  However, exactly how state change should work is not clear.  The semantics of the operator and supporting constructs in the language are still a work in progress.  This page attempts to explain and motivate the semantics that are in place as of July 2010.


# Representing Transitions #

The primary role of the state change operation at runtime is to cause a change in an object's typestate.  Practically, this will involve the removal and addition of members from the object.  The primary question is how to determine which members are updated.  Let us look again at the file example introduced in PlaidTutorial2.

```
state File {
  val filename;
}

state OpenFile case of File {
  var handle;

  method read();
  method close();
}

state ClosedFile case of File {
  method open();
}
```

Our intuition of a file abstraction is that it represents a way to access a particular file.  Furthermore, because there is overhead in interacting with the data source, we require an action (opening) prior to accessing the data, and expect that a user will clean up after they are done with the file by closing the file.  Thus, there are two states in our protocol for accessing a file (`OpenFile` and `ClosedFile`) which will each provide methods for performing the necessary steps to transition to the other.

Let us consider the changes that are needed to transition from the `CloseFile` state to the `OpenFile state`.  First, we will need to gain a handle on the underlying file resource from the file system.  Second we will need to update the members of our object to provide the correct interactions that can happen with an `OpenFile`.  In this case, we need to remove the `open()` method (we don't want to reinitialize our file system handle) and add the `close()` and `read()` methods as well as a field holding the file resource.  One part of the object that should not change, however, is the `filename` field.  Regardless of whether the file abstraction is in the `OpenFile` or `ClosedFile` state, it is still represents access to the same file resource, as expressed by declaring the immutable `filename` field in the top level `File` state.  Thus, the state change operator also needs to hold some portions of the object constant.

Using this understanding of how state change should operate when transitioning from the `ClosedFile` state to the `OpenFile` state, we can more precisely understand the semantics of the operator as used in the `open()` method:

```
state ClosedFile case of File {
        
        method open() {
                this <- OpenFile {
                        var handle = java.io.BufferedReader.new(java.io.FileReader.new(filename));
                };
        }
}

```

To start, let's look at the structures of the `this` object and the state it is changing to. The first important characteristic of the object is exactly what state they are in. As we learned in the PlaidTutorial2, we can use the match construct to determine which state, `OpenFile` or `ClosedFile`, a `File` object is in. This is because being a `case of File` creates a runtime tag to associate with objects in those states.  Thus, the `this` object will have the `ClosedFile` tag and the `OpenFile` state that it is transitioning to would be represented by the `OpenFile` tag.  The tags of the transitioning object and its eventual state tell us exactly what the differences are between the old state and the new state with respect to a particular protocol.  In this example, both `OpenFile` and `ClosedFile` are substates of `File`, so `File` will stay constant while `ClosedFile` will be replaced by `OpenFile`.  To make this precise, we need information about the members of the current object and where they were defined, and similarly for the members that come with the new state.

We know that the `this` object is in the `ClosedFile` state.  Thus, its members are: `{ method open() from ClosedFile, val filename from File }`.  Note that we have annotated the members with the state that they were originally defined in. We could represent the `OpenFile` state as the following list of members: `{ method read() from OpenFile, method close() from OpenFile, var handle from OpenFile }`.  Note that the `filename` field does not appear since it is an abstract field for which no value has been provided.

So how will this state change proceed:
  1. We remove all the members from the existing object that would not be included in the incoming state.
  1. We get rid of the tag associated with the current state from this protocol.
  1. We add all members from the incoming state that are not already included in the current object.
  1. We add the tag associated with the new state.

In our example, `open()` is removed since it is defined in the outgoing `ClosedFile` state and its tag is removed as well.  The `read()` and `close()` method and the `handle` field will be added since they are defined in the `OpenFile` state and the `OpenFile` tag will be added as well.  In this case, we added everything from the incoming state, but had `filename` from `File` had a default value, for instance, it would not be added since the `File` portion of the object is not changing.

## Some More Details ##

This is in fact a slightly simple example.  Two things might make it more complicated.  First, a more complicated protocol with more deeply nested substates, and other portions of the object.  However, in both cases, we can follow the prescription above to understand the effect.  In the first case, even with more nested cases, you can still determine what both the difference and overlap between the two states are.  As the description of the state change algorithm indicates, you simply remove everything from the existing object that would not overlap (ie everything defined in substates of the first common state) and then add everything from the incoming state except that stuff which you didn't remove.  This has the benefit of preserving fields defined in the overlapping portions.

For other parts of the object which are not defined in the protocol, they aren't included in the portion to be removed, so they stick around.  This allows protocols to be composed with other pieces of functionality which are not impacted when a state change occurs in the protocol.

## Limitations of the Current Implementation ##

There are some limitations of the current implementation.  Although Plaid will eventually contain mechanisms for overriding members defined in superstates, this functionality is not in place.  Since composition operators which remove members from object and states are also not in place, it is hard to remove members or avoid conflicts.

The place of definition for members is also undergoing some flux.  Currently, abstract members such as `filename` which are initialized during the `new` statement are actually listed as 'anonymous' members.  This may result in them not be removed when they should.  For instance, on the transition back to `ClosedFile`, the `handle` field would stick around.  This will be changing shortly.

## Wipe Away ##

The backdoor which can be used until these features are fleshed out and implemented is to do state change without tags.  If there are no conflicting tags between an object and its incoming state, then state change reverts to its original semantics whereby the entire object is cleared of members and reconstituted with the new state.  This provides a bludgeon to remove members if necessary, but should be used with the understanding that it might not always be a part of the language.

# Conclusion #

In this tutorial, we attempted to walk you through a protocol transition using the state change operator and showed how our intuition about that change translates into an actual algorithm.