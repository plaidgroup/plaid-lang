package plaid.tests.runtime.personmatches;

import junit.framework.Assert;

import org.junit.Test;

public class MatchTest {
	@Test
	public void matchesSelf() {
		Assert.assertTrue(Jim.theState$plaid.getDispatch().matches(Jim.theState$plaid.getTopTag()));
	}
	
	@Test
	public void noMatchOther() {
		Assert.assertFalse(Jim.theState$plaid.getDispatch().matches(Jane.theState$plaid.getTopTag()));
	}
}
