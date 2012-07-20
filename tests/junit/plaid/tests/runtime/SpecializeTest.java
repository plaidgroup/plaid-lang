package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.Util;

public class SpecializeTest {

	@Test
	public void specializeParentA() {
		ObjectValue ovf = Util.getEmptyObjectValue();
		ovf = ovf.addMethod("A", 0, "test");
		ovf = ovf.addMethod("B", 0, "test");
		ovf = ovf.addTag("BAR");
		ObjectValue parent = ovf;
		
		ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("FOO");
		ovf = ovf.addParent(parent);
		ObjectValue firstValue = ovf;
		
		ovf = firstValue;
		ovf = ovf.remove("A");
		ovf = ovf.addMethod("A", 0, "test");
		
		ObjectValue actualValue = ovf;
		
		ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("BAR");
		ovf = ovf.addMethod("B", 0, "test");
		ObjectValue parent2 = ovf;
		ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("FOO");
		ovf = ovf.addParent(parent2);
		ovf = ovf.addMethod("A", 0, "test");
		ObjectValue expectedValue = ovf;
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void specializeParentAB() {
		ObjectValue ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("BAR");
		ovf = ovf.addMethod("A", 0, "test");
		ovf = ovf.addMethod("B", 0, "test");
		ObjectValue parent = ovf;

		ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("FOO");
		ovf = ovf.addParent(parent);
		ObjectValue firstValue = ovf;
		
		ovf = firstValue;
		ovf = ovf.remove("A");
		ovf = ovf.remove("B");
		ovf = ovf.addMethod("A", 0, "max");
		ovf = ovf.addMethod("B", 0, "max2");
		
		ObjectValue actualValue = ovf;
		
		ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("BAR");
		ObjectValue parent2 = ovf;
		ovf = Util.getEmptyObjectValue();
		ovf = ovf.addTag("FOO");
		ovf = ovf.addParent(parent2);
		ovf = ovf.addMethod("A", 0, "max");
		ovf = ovf.addMethod("B", 0, "max2");
		ObjectValue expectedValue = ovf;
		
		Assert.assertEquals(expectedValue, actualValue);
	}
}
