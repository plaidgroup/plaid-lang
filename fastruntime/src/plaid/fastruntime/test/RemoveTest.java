package plaid.fastruntime.test;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.reference.ObjectValueFactory;

public class RemoveTest {
	
	@Test
	public void removeAFromAPlusB() {
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "test");
		ovf.mthd("B", 0, "test");
		ObjectValue firstValue = ovf.value();
		System.out.println("First value: " + firstValue.getCanonicalRep());
		
		
		ObjectValue actualValue = firstValue.remove("A");
		System.out.println("Actual value: " + actualValue.getCanonicalRep());
		
		ovf = new ObjectValueFactory();
		ovf.mthd("B", 0, "test");
		ObjectValue expectedValue = ovf.value();
		System.out.println("Expected value: " + expectedValue.getCanonicalRep());
		
		Assert.assertEquals(expectedValue, actualValue);
	}
}
