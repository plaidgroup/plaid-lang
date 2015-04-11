# Introduction #

This short tutorial introduces the concept of typestate and shows how it can be used in Plaid.

# Motivation #

State is a fundamental property of many objects, e.g. a simple object representing a file can be in the states `Open` and `Closed` with the methods `open()` and `close()` providing the necessary state transitions. Object states are often modeled using state machines but current programming languages provide little support for expressing these states and state transitions in the code. Often the only way is to use a field, e.g. an integer, in the object to keep track of the current state which is error-prone and as the number of states grows the programmer is more likely to forget handling certain cases. For example, calling `open()` on an already opened file is an error which must be handled by the programmer manually.

# Typestate #

Typestate allows the type system to track the state of objects and allows the programmer to express stateful designs much more clearly. In the file example, a file in state `Open` simply does not have an `open()` method and calling it is thus an error. In  Plaid, we can express this as follows:

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

As one can see, states look somewhat similar to classes in object-oriented languages and can contain fields and methods. However, typestate allows us to express various facts explicitly that would have been implicit otherwise, e.g. that reading from a closed file makes no sense.

Of course, to implement a state machine, we must also have a way of expressing state transitions to allow the object to change its state in the course of execution. This is what the state change operator `<-` is for in Plaid. Let's take a look of a sample implementation of `open()` to see how it is used:

```
state ClosedFile case of File {
	
	method open() {
		this <- OpenFile { 
			var handle = java.io.BufferedReader.new(java.io.FileReader.new(filename));
		};
	}
}
```

This is pretty straightforward. We change the state of `this` to the state `OpenFile` and initialize the handle field of `OpenFile`. Plaid makes it simple to interface with Java code so we are using Java's IO classes here. Note that the state change operation preserves the value of the filename field defined in the File state, so we do not have to re-initialize it.

To check which state an object is in, Plaid supports the `match` construct:
```
match (file) {
	case OpenFile {
		java.lang.System.out.println("File is open.");
	}
	case ClosedFile {
		java.lang.System.out.println("File is closed.");
	}
};
```

Using all this, we can now proceed to write a small sample application
```
package coreExamples.file;

state File {
	val filename;
}

state OpenFile case of File {
	var handle;
	
	method read() {
		handle.readLine();
	}

	method close() {
		this <- ClosedFile;
	}
}

state ClosedFile case of File {
	
	method open() {
		this <- OpenFile {
			var handle = java.io.BufferedReader.new(java.io.FileReader.new(filename));
		};
	}
}

method main() {
	val testfile = "test.txt";
	
	var file = new ClosedFile { val filename = testfile; };
	file.open();
	java.lang.System.out.println(file.read());
	java.lang.System.out.println(file.read());
	java.lang.System.out.println(file.read());
	file.close();
	match (file) {
		case OpenFile {
			java.lang.System.out.println("Error: file should be closed.");
		}
		case ClosedFile {
			java.lang.System.out.println("Success: file is closed.");
		}
	};

	// This raises an error because the file has already been closed and
	// read() is not available in the ClosedFile state.
	java.lang.System.out.println(file.read());
}
```

To learn more, see PlaidTutorial3.