package plaid.tests.runtime;

import org.junit.Test;

import junit.framework.Assert;
import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.reference.ObjectValueFactory;

public class SpecializeTest {

	@Test
	public void specializeParentA() {
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ovf.mthd("A", 0, "test");
		ovf.mthd("B", 0, "test");
		ObjectValue parent = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent);
		ObjectValue firstValue = ovf.value();
		
		ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "test");
		ObjectValue specializer = ovf.value();
		
		ObjectValue actualValue = firstValue.specialize(specializer);
		
		ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ovf.mthd("B", 0, "test");
		ObjectValue parent2 = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent2);
		ovf.mthd("A", 0, "test");
		ObjectValue expectedValue = ovf.value();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void specializeParentAB() {
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ovf.mthd("A", 0, "test");
		ovf.mthd("B", 0, "test");
		ObjectValue parent = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent);
		ObjectValue firstValue = ovf.value();
		
		ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "max");
		ovf.mthd("B", 0, "max2");
		ObjectValue specializer = ovf.value();
		
		ObjectValue actualValue = firstValue.specialize(specializer);
		
		ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ObjectValue parent2 = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent2);
		ovf.mthd("A", 0, "max");
		ovf.mthd("B", 0, "max2");
		ObjectValue expectedValue = ovf.value();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
}
