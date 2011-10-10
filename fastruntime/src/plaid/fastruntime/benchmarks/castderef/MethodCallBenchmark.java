package plaid.fastruntime.benchmarks.castderef;

import com.google.caliper.SimpleBenchmark;


public class MethodCallBenchmark extends SimpleBenchmark {
	
	private CachedFoo foo;
	private PlaidObjectTemplate o;
	private int test = 0;
	
	@Override protected void setUp() throws Exception {
		this.foo = new CachedFoo();
		this.o = new PlaidObjectTemplate(foo, null);
		this.test = 0;
	}
	public void timeCastDerefCall(int reps) {
		for (int i = 0; i < reps; i++) {
			test+=((Foo)o.dispatch).foo(o,i);
		}
	}
	
	public void timeCall(int reps) {
		for (int i = 0; i < reps; i++) {
			test+=foo.foo(null,i);
		}
	}
	
	public static void main(String[] args) {
		com.google.caliper.Runner.main(MethodCallBenchmark.class, args);
	}
}
