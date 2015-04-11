This tutorial assumes a basic understanding of Java, as well as functional programming.

# `HelloWorld.plaid` #

Plaid's basic syntax is very much like Java's. Take a look at HelloWorld.plaid:

```
// Hello, world!
package coreExamples.HelloWorld;

import java.lang.System;

method main() {
    System.out.println("Hello, world!");
}
```

Looks pretty familiar, doesn't it? But let's look at it piece by piece.

Comments are defined from a `//` to the end of the line, or between `/*` and `*/`.

`package coreExamples.HelloWorld;` specifies the package, `coreExamples.HelloWorld`, that the definitions in the file belong to.

`import java.lang.System` import's Java's `System` object, which is possible thanks to Plaid's Java interoperability. The Plaid standard library is imported by default.

The `method` keyword defines a method. We use it here to define the `main` method, which where execution will start if you run `HelloWorld`.

We use the imported Java `System` object to print to `stdout`.

That's all there is to it!

# Control Flow #

Plaid does not have primitive control flow operators. Instead, constructs like `if` and `while` are part of the standard library, defined using functions. For clarity, methods are Java-like methods declared using the `method` keyword, and functions are first-class, functional programming-style lambdas.

```
method sanityCheck() {
    if( 5 > 3 ) {
        System.out.println("That's a relief.");
    };
}
```

This works as you'd expect. The `if` statement is actually a function that takes two expressions. The first is the condition. If it is true, the second expression (either a block of code or a single statement) is executed. Since `if` is a function, `if (foo) {bar};` is a normal statement, and needs to be terminated with a semicolon. The same holds true for `ifElse` and `while`, as we are about to see.

```
method sanityCheck2() {
    ifElse( 5 > 3 ) {
        System.out.println("That's a relief.");
    }{ //Else
        System.out.println("Oh no!");
    };
}
```

The `ifElse` function takes three expressions. The first is the condition. If it is true, the second expression, the if-block, is executed. If it is false, the third expression, the else-block, is executed.

```
method oneToTen() {
    var i = 0;
    while { i < 10; } {
        i = i + 1;
        System.out.println(i);
    };
}
```

The `while` function takes two block expressions; the first is the conditional and the second is the method body. Note the curly braces around the conditional. This is because the lambda needs to evaluate the conditional on each iteration. If it had simply taken a boolean value, like the `if` statement, it would not be able to reevaluate it.

# A few notes on code blocks #

A block of code, such as a method body, consists of a series of statements, surrounded by curly braces, and terminated by semicolons.

The last statement of a block is the return value. It returns that value automatically, without the need for a `return` keyword. The final semicolon of a block is optional.

```
// A legal implementation of the signum function.
// Eschews all optional semicolons.
method signum(x) {
    ifElse (x > 0) {
        1
    }{
        ifElse (x < 0) {
            -1
        }{
            0
        }
    }
}
```
```
// Another legal implementation of the signum function.
// Uses all optional semicolons.
method signum(x) {
    ifElse (x > 0) {
        1;
    }{
        ifElse (x < 0) {
            -1;
        }{
            0;
        };
    };
}
```

# Declaring and using lambdas, matching #

We've already seen some lambdas in use, but here's how to declare them.

```
method main() {
    var factorial = fn (x) => {
        ifElse (x == 0) {
            1;
        }{
            x * factorial (x - 1);
        };
    };
    var y = factorial(6);
    System.out.println(y);
}
```

We declare a lambda using `fn (x) => y`, where `x` is its argument and `y` is a statement or block to be executed. We then assign the lambda to the variable `factorial`. We call it just like a method.

Lambdas can take any number of arguments, including none.

```
method main() {
    var shout = fn () => System.out.println("HEY!");
    shout();
}
```

As mentioned, the `if` statement is a function. Here's how you might declare it:

```
var if = fn (cond, body) {
    match(bool) {
        case True {
            body();
        }
        case False {}
    }
}
```

But then you would have to use it like this: `if( 5 > 3, {foo(); bar();});`, which is a little ugly. We can clean this up using a little currying.

```
var if = fn (bool) => {
    match (bool) {
        case True {
            var do = fn (func) => func ();
            do;
        }
        case False {
            var dont = fn (func) => unit;
            dont;
        }
    };
};
```

`if (cond) {block}` first evaluates `if(cond)`. `cond` is evaluated to `true` or `false`, and `if` uses `match` to switch on it. (The `match` construct is covered in more detail in PlaidTutorial2.) If it's `true`, it returns the `do` lambda. `do {block}` is then evaluated, which runs the code in the block. If it's false, it returns the `dont` lambda, which ignores its argument and does nothing.

To learn about typestate, see PlaidTutorial2.