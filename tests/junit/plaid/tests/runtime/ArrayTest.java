package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.collections.makeA_rray;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.generated.Iset$2$plaid;
import plaid.generated.Iget$1$plaid;

public class ArrayTest {

	@Test
	public void test() {
		PlaidObject myArray = makeA_rray.invoke$plaid(Util.integer(3));
		set(myArray,0,"Josh");
		set(myArray,1,"Karl");
		set(myArray,2,"Sven");
		Assert.assertEquals(get(myArray,2), "Sven");
		Assert.assertEquals(get(myArray,1), "Karl");
		Assert.assertEquals(get(myArray,0), "Josh");
		set(myArray,2,"Jonathan");
		Assert.assertEquals(get(myArray,2), "Jonathan");
	}
	
	private void set(PlaidObject myArray, int index, String value) {
		((Iset$2$plaid)myArray.getDispatch()).set(myArray, Util.integer(index), Util.string(value));
	}
	
	private String get(PlaidObject myArray, int index) {
		PlaidObject poString = ((Iget$1$plaid)myArray.getDispatch()).get(myArray, Util.integer(index));
		return(String)((PlaidJavaObject)poString).getJavaObject();
	}

}
