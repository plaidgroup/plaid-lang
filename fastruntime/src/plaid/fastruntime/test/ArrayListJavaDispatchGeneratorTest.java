package plaid.fastruntime.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.Util;

public class ArrayListJavaDispatchGeneratorTest {

	@Test
	public void test() {
		ArrayList<String> al = new ArrayList<String>();
		PlaidJavaObject alPlaid = Util.JAVA_GEN.createPlaidJavaObject(al);
		boolean test = alPlaid.getJavaObject() instanceof ArrayList;
		Assert.assertTrue(test);
	}

}
