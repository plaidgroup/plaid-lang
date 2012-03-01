package plaid.fastruntime.dcg.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;

public class GenerateStaticTestClass {
	@Test
	public  void test() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> result = Util.JAVA_GEN.getStaticJavaClass(TestClass.class);
		Method appendFooMethod = result.getMethod("appendFoo", plaid.fastruntime.PlaidObject.class);
		Assert.assertTrue(appendFooMethod != null);
		PlaidObject s = Util.string("foo");
		Object fooAppendObject = appendFooMethod.invoke(null, s);
		Assert.assertTrue(fooAppendObject instanceof PlaidJavaObject);
		Object fooAppendJavaObject = ((PlaidJavaObject)fooAppendObject).getJavaObject();
		Assert.assertTrue(fooAppendJavaObject instanceof String);
		Assert.assertEquals("fooFoo", fooAppendJavaObject.toString());
	}
	
	public static void main(String[] args ) {
		//plaid.fastruntime.dcg.StaticJavaGenerator.saveStaticJavaObject(TestClass.class);
		Util.JAVA_GEN.saveStaticJavaObject(TestClass.class);
	}
}
