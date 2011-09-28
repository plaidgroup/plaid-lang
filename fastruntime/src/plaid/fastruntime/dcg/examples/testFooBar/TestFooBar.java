package plaid.fastruntime.dcg.examples.testFooBar;

import java.util.HashMap;
import java.util.Map;

import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.MethodImplementation;
import plaid.fastruntime.dcg.MethodSpecification;

import com.google.caliper.SimpleBenchmark;

public class TestFooBar extends SimpleBenchmark {
	private Map<Class<? extends MethodSpecification>, Class<? extends MethodImplementation>> map;
	private Map<Class<? extends MethodSpecification>, Class<? extends MethodImplementation>> map2;
	public Foo fo;
		
	@Override protected void setUp() throws Exception {
		map = new HashMap<Class<? extends MethodSpecification>, Class<? extends MethodImplementation>>();
		map.put(Foo.class, FooImpl.class);
		map.put(Bar.class, BarImpl.class);
		map2 = map = new HashMap<Class<? extends MethodSpecification>, Class<? extends MethodImplementation>>();
		map2.put(Foo.class, FooImpl.class);
		map2.put(Bar.class, BarImpl.class);
		map2.put(Baz.class, BazImpl.class);
	}
	
	public void timeFooBar(int reps) {
		for (int i = 0; i < reps; i++) {
			DispatchGenerator gen = new DispatchGenerator();
			fo = (Foo)gen.createClass(map);
		}
	}
	
	public void timeFooBarBaz(int reps) {
		for (int i = 0; i < reps; i++) {
			DispatchGenerator gen = new DispatchGenerator();
			fo = (Foo)gen.createClass(map2);
		}
	}
	
	public static void main(String[] args) {
		com.google.caliper.Runner.main(TestFooBar.class, args);
	}
}
