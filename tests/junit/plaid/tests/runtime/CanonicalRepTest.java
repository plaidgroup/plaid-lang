package plaid.tests.runtime;

import org.junit.Assert;
import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.reference.ObjectValueFactory;

public class CanonicalRepTest {
	@Test
	public void singleMethod() {
		String expected = "method:A0truetest";
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "test");
		String actual = ovf.value().getCanonicalRep();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void twoMethods() {
		String expected = ";method:A0truetest;method:B0truetest";
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "test");
		ovf.mthd("B", 0, "test");
		String actual = ovf.value().getCanonicalRep();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void methodAndField() {
		String expected = ";field:tBtruetest;method:A0truetest";
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "test");
		ovf.field(true, "B", "test");
		String actual = ovf.value().getCanonicalRep();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void dimensionWithMethodAndField() {
		String expected = "TAG[FOO]{;field:tBtruetest;method:A0truetest}";
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.mthd("A", 0, "test");
		ovf.field(true, "B", "test");
		String actual = ovf.value().getCanonicalRep();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void dimensionWithMethodAndFieldAndParent() {
		String expected = "TAG[FOO]{;field:tBtruetest;method:A0truetest}<:TAG[BAR]{}";
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.tag("BAR");
		ObjectValue parent = ovf.value();
		ovf = new ObjectValueFactory();
		ovf.tag("FOO");
		ovf.parent(parent);
		ovf.mthd("A", 0, "test");
		ovf.field(true, "B", "test");
		String actual = ovf.value().getCanonicalRep();
		Assert.assertEquals(expected, actual);
	}
}
