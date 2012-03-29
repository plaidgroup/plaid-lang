package plaid.fastruntime.dcg.test;

public class TestClass {
	public static int addTwo(int x, int y) { 
		return x + y; 
	}
	
	public static String appendFoo(String x) { 
		return x + "Foo";
	}
	
	public final String s; 
	
	public TestClass() {
		s = "noArgs";
	}
	
	public TestClass(String s) {
		this.s = s;
	}
	
	public TestClass(Object o1, Object o2) {
		this.s = "object object";
	}
	
	public TestClass(Object o1, String s) {
		this.s = "object " + s;
	}
	
	public TestClass(String s, Object o) {
		this.s = s + " object";
	}
	
	public TestClass appendFoo(Object o) {
		return new TestClass(this.s + " objectFoo");
	}
	
	public static void main(String[] args) {
		
		String s = appendFoo("foo");
		
		byte f = 5;
		Integer d = new Integer(f);
		
	}
	
}
