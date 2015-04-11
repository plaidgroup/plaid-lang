# Collections Hierarchy (plaid.collections) #

At the base of the tree we have Collection, which does not contain any mutating methods, only:
  * map - apply a function to each element, adding the result of this operation to a new collection and returning this collection.
  * reduce - pairwise application of a function to the contents of the collection: result = base op e1 op e2 ... eN.
  * find - applies a function which returns a boolean to each element, returning the first element where the function returns true.
  * doUntil - applies a function which returns a boolean to each element, until that function returns true.
  * do - applies a function to all elements in the collection.
  * forall - applies a function which returns a boolean to each element, returning true if the function returns true for all elements in the collection.
  * exists - applies a function which returns a boolean to each element, returning true if there exists at least one element in the collection for which the function returns true.
  * size - the number of elements in the collection.

The implementation of a collection type is made easier by only requiring three methods actually be overridden for a particular collection: size(), doUntil() and and map(). All other methods are implemented using doUntil(), though a specific Collection is of course free to replace these implementations with more efficient ones if this is possible.

There are three broad categories of collections:

  * List`<T>` case of Collection`<T>` - ordered, finite collection of elements of type T.
    * ArrayList - implementation which uses a single array to store elements. Array will be reallocated to grow/shrink as the list changes size.
    * LinkedList - implementation which uses cons and nil to compose singly linked lists.
  * Set case of Collection`<Comparable<T>>` - unordered, finite collection of elements of type T, where e : T exists in the collection at most once.
    * TreeSet`<Orderable<T>>` - implementation of set based on an AATree, which gives worst case O(log n) guarantees for insertion, deletion and lookup.
    * HashSet`<Hashable<T>>` - to be implemented, based on a growing list of buckets to partition values based on ranges of the hash space.
  * Map case of Collection`<Pair<K,V>>` - partial map from K values to V values.
    * TreeMap`<Orderable<K>,V>` - implementation based on AATree.
    * HashMap`<Hashable<K> with Comparable<K,V>` - implementation similar to HashSet, using the hash of the key for partitioning.

## Mutable vs Immutable ##

Collection defines an immutable interface, as do List, Set and Map. So to complement these, we have a set of traits that can be mixed in with those types to produce mutable variants:
  * MutableCollection - provides methods add(elem), remove(elem).
  * MutableMap - provides put(key, value), removeKey(elem).

The intention is that all the collection types listed above will have these mutable traits mixed in. This gives us the option to later implement persistent data structures with different signatures for add, remove etc. that return a new collection every time.