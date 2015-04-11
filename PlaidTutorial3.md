# Introduction #

To represent a singly-linked list we choose a Lisp-like approach using the cons and nil constructs. A simple list containing the numbers one to five is thus represented by:
```
(cons 1 (cons 2 (cons 3 (cons 4 (cons 5 nil)))))
```
Implementing this in Plaid is easy. We create a state `MyListCell` and two substates `MyCons` and `MyNil`; we use a "My" prefix here to prevent naming collisions with states in Plaid's standard library.
```
state MyListCell {
}

state MyCons case of MyListCell {
    var value;
    var next;

    method append(elem);
}

state MyNil case of MyListCell {
    method append(elem);
}
```
`MyCons` has two fields, one to hold the cell's value and one to point to the next cell. Implementing the `append` method in `MyCons` is straightforward as we just need to call append on our successor:
```
method append(elem) {
        this.next.append(elem);
}
```
The more interesting part is implementing `append` in `MyNil`. In that case we have to add a new element to the list which contains the new value. In more typestate oriented terms, the last cell in the list (which is a `MyNil` at the moment) needs to change its state from `MyNil` to `MyCons`. As we've seen in the file example, we can implement this state change using Plaid's state change operator `<-`:
```
state MyNil case of MyListCell {
    method append(elem) {
        this <- MyCons with {
            var value = elem;
            var next = new MyNil;
        };
    }
}
```
To make using lists easier we create a `MyList` state where we can put all the methods we would like to support like `map`, `filter` and `length`.
```
state MyList {
    var head = new MyNil;

    method prepend(elem) {
        this.head = new MyCons with { var value = elem; var next = this.head; };
    }

    method append(elem) {
        this.head.append(elem);
    }
}
```
So, let's implement the `map` method for our list. The `map` operation returns a list constructed by applying a function to all values in its argument list. To implement a method like `map` we can use Plaid's `match` construct which allows us to inspect what state a list cell is currently in. In this way we can iterate over all list cells and choose the appropriate action.
```
state MyList {
    method map(f) {
        new MyList with { var head = this.mapHelper(f, this.head); }
    }

    method MyListCell mapHelper(f, MyListCell curr) {
        match (curr) {
            case MyCons {
                val newVal = f(curr.value);
                new MyCons with { var value = newVal; var next = this.mapHelper(f, curr.next); }
            }
            case MyNil {
                curr
            }
        };
    }
}
```
Using Plaid's support for closures we can now write very concise code to calculate the prefix sum:
```
// Calculate inclusive prefix sum
var sum = 0;
val sums = l.map(fn (n) => { sum = sum + n; sum; });
```

The following full example shows a list state which implements `length`, `map`, `filter`, `foldr` and `zip` and shows some examples on how to use those methods.
```
package coreExamples.functionalStyle;

state MyListCell {
}

state MyNil case of MyListCell {
    method append(elem) {
        this <- MyCons with {
            var value = elem;
            var next = new MyNil;
        };
    }
}

state MyCons case of MyListCell {
    var Int value;
    var next;

    method append(elem) {
        this.next.append(elem);
    }
}

state MyList {
    var head = new MyNil;

    method prepend(elem) {
        this.head = new MyCons with { var value = elem; var next = this.head; };
    }

    method append(elem) {
        this.head.append(elem);
    }

    method map(f) {
        new MyList with { var head = this.mapHelper(f, this.head); }
    }

    method MyListCell mapHelper(f, MyListCell curr) {
        match (curr) {
            case MyCons {
                val newVal = f(curr.value);
                new MyCons with { var value = newVal; var next = this.mapHelper(f, curr.next); }
            }
            case MyNil {
                curr
            }
        };
    }

    method MyList zip(MyList list2) {
        new MyList with { var head = this.zipHelper(this.head, list2.head); }
    }

    method MyListCell zipHelper(MyListCell node1, MyListCell node2) {
        match(node1) {
            case MyCons {
                match(node2) {
                    case MyCons {
                        val newVal = new Pair with {
                            fst = node1.value;
                            snd = node2.value;
                        };
                        new MyCons with {var value = newVal; var next = this.zipHelper(node1.next, node2.next); }
                    }
                    case MyNil {
                        new MyNil;
                    }
                }
            }
            case MyNil {
                new MyNil;
            }
        }
    }

    method filter(p) {
        new MyList with { var head = this.filterHelper(p, this.head); }
    }

    method MyListCell filterHelper(p, MyListCell curr) {
        match (curr) {
            case MyCons {
                ifElse (p(curr.value)) {
                    new MyCons with { var value = curr.value; var next = this.filterHelper(p, curr.next); };
                } {
                    this.filterHelper(p, curr.next);
                };
            }
            case MyNil {
                curr
            }
        }
    }

    method foldr(f, z) {
        foldrHelper(f, z, this.head);
    }

    method foldrHelper(f, z, MyListCell curr) {
        match (curr) {
            case MyCons {
                f(curr.value, foldrHelper(f, z, curr.next));
            }
            case MyNil {
                z
            }
        }
    }

    method length() {
        this.lengthHelper(this.head);
    }

    method lengthHelper(MyListCell curr) {
        match (curr) {
            case MyCons {
                1 + this.lengthHelper(curr.next);
            }
            case MyNil {
                0
            }
        }
    }
}

method for(lower, upper, body) {
    var i = lower;
    while { i < upper; } {
        body(i);
        i = i + 1;
    };
}

method print(s, MyList l) {
    val p = java.lang.System.out.print;

    p(s);
    p(": [ ");
    l.map(fn (n) => { p(n); p(" "); });
    p("]");

    java.lang.System.out.println("");
}

method printPairList(s, MyList l) {
    val p = java.lang.System.out.print;

    p(s);
    p(": [ ");
    l.map(fn (n) => {
        p("(");
        p(n.fst);
        p(",");
        p(n.snd);
        p(") ");
    });
    p("]");

    java.lang.System.out.println("");
}

method isPrime(num) {
    ifElse (num < 2) {
        false
    } {
        val l = new MyList;
        for(2, num, fn (i) => l.append(i));

        l.filter(fn (i) => num % i == 0).length() == 0;
    };
}

method main() {
    var l = new MyList;
    for(0, 20, fn (i) => l.append(i));
    print("List", l);

    // Calculate inclusive prefix sum
    var sum = 0;
    val sums = l.map(fn (n) => { sum = sum + n; sum; });
    print("Prefix sum", sums);

    // Calculate sum of all elements
    val overallSum = l.foldr(fn (x, y) => x + y, 0);
    java.lang.System.out.print("Overall sum: ");
    java.lang.System.out.println(overallSum);

    // Find all primes
    val primes = l.filter(isPrime);
    print("Primes", primes);

    // Zip two lists together
    val zipped = l.zip(sums);
    printPairList("Zipped", zipped);
}
```