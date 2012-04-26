package plaid.tests.runtime;

public class ExampleClass {
	public static int addTwo(int x, int y) { 
		return x + y; 
	}
	
	public static String appendFoo(String x) { 
		return x + "Foo";
	}
	
	public final String s; 
	
	public ExampleClass() {
		s = "noArgs";
	}
	
	public ExampleClass(String s) {
		this.s = s;
	}
	
	public ExampleClass(Object o1, Object o2) {
		this.s = "object object";
	}
	
	public ExampleClass(Object o1, String s) {
		this.s = "object " + s;
	}
	
	public ExampleClass(String s, Object o) {
		this.s = s + " object";
	}
	
	public ExampleClass appendFoo(Object o) {
		return new ExampleClass(this.s + " objectFoo");
	}
	
	public static void main(String[] args) {
		
		String s = appendFoo("foo");
		
		byte f = 5;
		Integer d = new Integer(f);
		
	}
	
}
