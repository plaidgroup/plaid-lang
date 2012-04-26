package plaid.tests.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public class GenerateStaticTestClass {
	@Test
	public  void test() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> result = Util.JAVA_GEN.getStaticJavaClass(ExampleClass.class);
		Method appendFooMethod = result.getMethod("appendFoo", plaid.fastruntime.PlaidObject.class);
		Assert.assertTrue(appendFooMethod != null);
		PlaidObject s = Util.string("foo");
		Object fooAppendObject = appendFooMethod.invoke(null, s);
		Assert.assertTrue(fooAppendObject instanceof PlaidJavaObject);
		Object fooAppendJavaObject = ((PlaidJavaObject)fooAppendObject).getJavaObject();
		Assert.assertTrue(fooAppendJavaObject instanceof String);
		Assert.assertEquals("fooFoo", fooAppendJavaObject.toString());
		
		//nullary constructor
		Method nullaryConstructor = result.getMethod(NamingConventions.GENERATED_CONSTRUCTOR);
		Assert.assertTrue(nullaryConstructor != null);
		Object newTestClass = nullaryConstructor.invoke(null);
		Assert.assertTrue(newTestClass instanceof PlaidJavaObject);
		Object newTestClassJavaObject = ((PlaidJavaObject)newTestClass).getJavaObject();
		Assert.assertTrue(newTestClassJavaObject instanceof ExampleClass);
		Assert.assertTrue(((ExampleClass) newTestClassJavaObject).s.equals("noArgs"));
		
		//unary constructor
		Method unaryConstructor = result.getMethod(NamingConventions.GENERATED_CONSTRUCTOR, plaid.fastruntime.PlaidObject.class);
		Assert.assertTrue(unaryConstructor != null);
		Object newTestClass2 = unaryConstructor.invoke(null,s);
		Assert.assertTrue(newTestClass2 instanceof PlaidJavaObject);
		Object newTestClassJavaObject2 = ((PlaidJavaObject)newTestClass2).getJavaObject();
		Assert.assertTrue(newTestClassJavaObject2 instanceof ExampleClass);
		Assert.assertTrue(((ExampleClass) newTestClassJavaObject2).s.equals("foo"));
		
		//overloaded constructor - one choice only
		Method overloadedConstructor = result.getMethod(NamingConventions.GENERATED_CONSTRUCTOR, plaid.fastruntime.PlaidObject.class, plaid.fastruntime.PlaidObject.class);
		Assert.assertTrue(overloadedConstructor != null);
		PlaidObject thisPlaid = Util.javaToPlaid(this);
		Object newTestClass3 = overloadedConstructor.invoke(null,thisPlaid,thisPlaid);
		Assert.assertTrue(newTestClass3 instanceof PlaidJavaObject);
		Object newTestClassJavaObject3 = ((PlaidJavaObject)newTestClass3).getJavaObject();
		Assert.assertTrue(newTestClassJavaObject3 instanceof ExampleClass);
		Assert.assertTrue(((ExampleClass) newTestClassJavaObject3).s.equals("object object"));
		
		//two choices
		Object newTestClass4 = overloadedConstructor.invoke(null,thisPlaid,s);
		Assert.assertTrue(newTestClass4 instanceof PlaidJavaObject);
		Object newTestClassJavaObject4 = ((PlaidJavaObject)newTestClass4).getJavaObject();
		Assert.assertTrue(newTestClassJavaObject4 instanceof ExampleClass);
		Assert.assertTrue(((ExampleClass) newTestClassJavaObject4).s.equals("object foo"));
		
		//ambiguous choices
		try {
			overloadedConstructor.invoke(null,s,s);
			Assert.fail();
		} catch (InvocationTargetException e) {
			//pass
			Assert.assertTrue(true);
		}
		
	}
	
	public static void main(String[] args ) {
		//plaid.fastruntime.dcg.StaticJavaGenerator.saveStaticJavaObject(TestClass.class);
		Util.JAVA_GEN.saveStaticJavaObject(ExampleClass.class);
	}
}
