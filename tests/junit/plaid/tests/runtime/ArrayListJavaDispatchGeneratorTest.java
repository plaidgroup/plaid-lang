package plaid.tests.runtime;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;

import plaid.generated.Iget$1$plaid;
import plaid.generated.Iadd$1$plaid;

public class ArrayListJavaDispatchGeneratorTest {

	@Test
	public void test() {
		ArrayList<String> al = new ArrayList<String>();
		PlaidObject alPlaid = Util.javaToPlaid(al);
		boolean test = ((PlaidJavaObject) alPlaid).getJavaObject() instanceof ArrayList;
		Assert.assertTrue(test);
	}
	
	@Test
	public void test2() {
		ArrayList<PlaidObject> al = new ArrayList<PlaidObject>();
		PlaidObject stringToAdd = Util.string("Test");
		PlaidObject alPlaid = Util.javaToPlaid(al);
		PlaidObject indexToGet = Util.integer(0);
		Class<?> alDispatch = alPlaid.getDispatch().getClass();
//		for(Class<?> iface : alDispatch.getInterfaces()) {
//			System.out.println(iface.getSimpleName());
//		}
		((Iadd$1$plaid)alPlaid.getDispatch()).add(alPlaid, stringToAdd);
		PlaidObject returned = ((Iget$1$plaid)alPlaid.getDispatch()).get(alPlaid, indexToGet);
		Assert.assertTrue("returned value is not PlaidJavaObject", returned instanceof PlaidJavaObject);
		Object javaReturned = ((PlaidJavaObject) returned).getJavaObject();
		Assert.assertEquals("returned value is not the same string we put in", javaReturned , "Test");
	}

}
