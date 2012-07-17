package plaid.tests.runtime;

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
		
		
		ObjectValue actualValue = firstValue.remove("A");
		
		ovf = new ObjectValueFactory();
		ovf.mthd("B", 0, "test");
		ObjectValue expectedValue = ovf.value();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void removeAFromParent() {
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ovf.mthd("A", 0, "test");
		ovf.mthd("B", 0, "test");
		ObjectValue parent = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent);
		ObjectValue firstValue = ovf.value();
		
		ObjectValue actualValue = firstValue.remove("A");
		
		ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ovf.mthd("B", 0, "test");
		ObjectValue parent2 = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent2);
		ObjectValue expectedValue = ovf.value();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
}
