package plaid.fastruntime.test;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.CompilerUtil;

public class OperatorNamesTest {

	@Test
	public void test() {
		String actual = CompilerUtil.convertOpStringToMethodName("=");
		String expected = "eq$plaid";
		Assert.assertEquals(expected, actual);
		actual = CompilerUtil.convertOpStringToMethodName("||");
		expected = "pipepipe$plaid";
		Assert.assertEquals(expected, actual);
	}

}
