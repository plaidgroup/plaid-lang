package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.NamingConventions;

public class JavaReservedIdentifierTest {
	@Test
	public void javaKeywords() {
		Assert.assertEquals(NamingConventions.getGeneratedIdentifier("this"), "this$plaid");
		Assert.assertEquals(NamingConventions.getGeneratedIdentifier("while"), "while$plaid");
		Assert.assertEquals(NamingConventions.getGeneratedIdentifier("int"), "int$plaid");
	}
	
	@Test
	public void notJavaKeywords() {
		Assert.assertEquals(NamingConventions.getGeneratedIdentifier("foo"), "foo");
		Assert.assertEquals(NamingConventions.getGeneratedIdentifier("bar"), "bar");
		Assert.assertEquals(NamingConventions.getGeneratedIdentifier("ajlskjdkfjalkdfjs"), "ajlskjdkfjalkdfjs");
	}
}
