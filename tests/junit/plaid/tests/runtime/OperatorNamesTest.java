package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.NamingConventions;

public class OperatorNamesTest {

	@Test
	public void test() {
		String actual = NamingConventions.convertOpStringToMethodName("=");
		String expected = "eq$plaid";
		Assert.assertEquals(expected, actual);
		actual = NamingConventions.convertOpStringToMethodName("||");
		expected = "pipepipe$plaid";
		Assert.assertEquals(expected, actual);
	}

}
