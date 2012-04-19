package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
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
		Assert.assertTrue(Util.overloadedInstanceMethod(test, "o", ((PlaidJavaObject)Util.string("test"))).equals("String"));
		Assert.assertTrue(Util.overloadedInstanceMethod(test, "o", ((PlaidJavaObject)Util.javaToPlaid(this))).equals("Object"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	@Test
	public void twoArgs() {
		
		try {
			Assert.assertTrue(Util.overloadedInstanceMethod(test, "t", ((PlaidJavaObject)Util.javaToPlaid(this)), ((PlaidJavaObject)Util.javaToPlaid(this))).equals("O2"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}	
		
		try {
			Util.overloadedInstanceMethod(test, "t", ((PlaidJavaObject)Util.string("test")), ((PlaidJavaObject)Util.string("test")));
			Assert.fail();
		} catch (PlaidIllegalOperationException e) {
			//pass
		}	
		
	}
	
	@Test
	public void ifacesAmbig() {
		
		try {
			Util.overloadedInstanceMethod(test, "u", ((PlaidJavaObject)Util.javaToPlaid(iTest)));
			Assert.fail();
		} catch (PlaidIllegalOperationException e) {
			//pass
		}	
		
		try {
			Assert.assertTrue(Util.overloadedInstanceMethod(test,"v",((PlaidJavaObject)Util.javaToPlaid(iTest2))).equals("o1234"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}	
		 
	}
	
	
	@Test
	public void intVlong() {
		
		try {
			Assert.assertTrue(Util.overloadedInstanceMethod(test, "w", ((PlaidJavaObject)Util.javaToPlaid(int5))).equals("int"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}	

		 
	}
	
	@Test
	public void boxedVunboxed() {
		
		try {
			Assert.assertTrue(Util.overloadedInstanceMethod(test, "x", ((PlaidJavaObject)Util.javaToPlaid(int5))).equals("Integer"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}	
		
		try {
			Assert.assertTrue(Util.overloadedInstanceMethod(test, "x", ((PlaidJavaObject)Util.javaToPlaid(5))).equals("Integer"));
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}

		 
	}
	
	@Test
	public void boxedFail() {
		
		try {
			Util.overloadedInstanceMethod(test, "y", ((PlaidJavaObject)Util.javaToPlaid(int5)));
			Assert.fail();
		} catch (PlaidIllegalOperationException e) {
			//pass
		}

	}
	
	@Test
	public void staticOverloadsInstance() {
		//can call a static method through an instance
		try {
			Object foo = Util.overloadedInstanceMethod(test, "z", ((PlaidJavaObject)Util.string("foo")));
			Assert.assertTrue(Util.overloadedInstanceMethod(test, "z", ((PlaidJavaObject)Util.string("foo"))).equals("string"));
			
		} catch (PlaidIllegalOperationException e) {
			Assert.fail();
		}

	}
	
	
	public static class OverloadTestClass {
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
		
		public String y(Double d) { return "Double"; }
		public String y(Float d) { return "Float"; }
		
		public String z(Object o) { return "object"; }
		public static String z(String s) { return "string"; }
	}
	

	public interface i1 { }
	public interface i2 { } 
	public interface i3 { } 
	public interface i4 { } 
	public class o12 implements i1,i2 { }
	public class o1234 implements i1,i2,i3,i4 { }
}
