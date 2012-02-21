package plaid.fastruntime.test;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public class OverloadDispatchTest {

	private Object test = new OverloadTestClass();
	private Object iTest = new o12();
	private Object iTest2 = new o1234();
	private Object int5 = 5;
	
	@Test
	public void oneArg() {
		
		
		try {
		Assert.assertTrue(Util.staticOverloadingCall(test, "o", "test").equals("String"));
		Assert.assertTrue(Util.staticOverloadingCall(test, "o", this).equals("Object"));
		} catch (PlaidIllegalOperationException e) {
			Assert.fail();
		}	
	}
	
	@Test
	public void twoArgs() {
		
		try {
			Assert.assertTrue(Util.staticOverloadingCall(test, "t", this, this).equals("O2"));
		} catch (PlaidIllegalOperationException e) {
			Assert.fail();
		}	
		
		try {
			Util.staticOverloadingCall(test, "t", "test", "test");
			Assert.fail();
		} catch (PlaidIllegalOperationException e) {
			//pass
		}	
		
	}
	
	@Test
	public void ifacesAmbig() {
		
		try {
			Util.staticOverloadingCall(test, "u", iTest);
			Assert.fail();
		} catch (PlaidIllegalOperationException e) {
			//pass
		}	
		
		try {
			Assert.assertTrue(Util.staticOverloadingCall(test,"v",iTest2).equals("o1234"));
		} catch (PlaidIllegalOperationException e) {
			Assert.fail();
		}	
		 
	}
	
	
	@Test
	public void intVlong() {
		
		try {
			Assert.assertTrue(Util.staticOverloadingCall(test, "w", int5).equals("int"));
		} catch (PlaidIllegalOperationException e) {
			//e.printStackTrace();
			Assert.fail();
		}	

		 
	}
	
	@Test
	public void intVInteger() {
		
		try {
			Assert.assertTrue(Util.staticOverloadingCall(test, "x", int5).equals("Integer"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}	
		
		try {
			Assert.assertTrue(Util.staticOverloadingCall(test, "x", 5).equals("Integer"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}

		 
	}

//	public static void main(String[] args) {
//		System.out.println(long.class.isAssignableFrom(int.class));
//		System.out.println(int.class.isAssignableFrom(long.class));
//		System.out.println(int.class.isAssignableFrom(int.class));
//		int test = 5;
//		System.out.println(long.class.isInstance(test));
//		System.out.println(boolean.class.isAssignableFrom(Boolean.class));
//		System.out.println(Boolean.class.isAssignableFrom(boolean.class));
//	}
	
	
	public class OverloadTestClass {
		public String o(Object o) { return "Object"; }
		public String o(String s) { return "String"; }
		
		public String t(Object o1, Object o2) { return "O2"; }
		public String t(Object o1, String s2) { return "OS"; }
		public String t(String s1, Object o2) { return "SO"; }
		//public String t(String s1, String s2) { return "S2"; }
		
		public String u(i1 x) { return "i1"; }
		public String u(i2 x) { return "i2"; }
		
		public String v(i1 x) { return "i1"; }
		public String v(i2 x) { return "i2"; }
		public String v(i3 x) { return "i3"; }
		public String v(i4 x) { return "i4"; }
		public String v(o1234 x) { return "o1234"; }
		
		public String w(int i) { return "int"; }
		public String w(long j) { return "long"; }
		
		public String x(int i) { return "int"; }
		public String x(Integer i) { return "Integer"; }
		
		
	}
	

	public interface i1 { }
	public interface i2 { } 
	public interface i3 { } 
	public interface i4 { } 
	public class o12 implements i1,i2 { }
	public class o1234 implements i1,i2,i3,i4 { }
}
