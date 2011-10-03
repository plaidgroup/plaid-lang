package plaid.fastruntime.benchmarks.castderef;

public class CachedFoo implements Foo {
	public int foo(PlaidObject thisVar, int arg2) {
		return StaticFoo.foo(thisVar,arg2);
	}
}
