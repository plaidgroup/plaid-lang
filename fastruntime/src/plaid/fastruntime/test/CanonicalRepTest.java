package plaid.fastruntime.test;

import org.junit.Assert;
import org.junit.Test;

import plaid.fastruntime.reference.ObjectValueFactory;

public class CanonicalRepTest {
	@Test
	public void singleMethod() {
		String expected = "method:A0test";
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.mthd("A", 0, "test");
		String actual = ovf.value().getCanonicalRep();
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}
}
