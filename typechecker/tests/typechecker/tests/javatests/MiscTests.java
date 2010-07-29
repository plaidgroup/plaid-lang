package typechecker.tests.javatests;

import org.junit.BeforeClass;
import org.junit.Test;

import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeState.RUNTIME_STATE;

public class MiscTests {
	@BeforeClass
	public static void beforeClass() {
		// we need to do this so the Runtime doesn't deadlock if there are hooks into it
		// (e.g. in debug mode)
		PlaidRuntime.getRuntime().setRuntimeState(RUNTIME_STATE.RUNNING);
	}
	
	@Test
	public void testMethodTypeDeclEquals() {
		// TODO: stub
	}
	
	@Test
	public void testFieldTypeDeclEquals() {
		// TODO: stub
	}
	
	@Test
	public void testPermissionEquals() {
		// TODO: stub
	}
	
	@Test
	public void testTypeEquals() {
		// TODO: stub
	}
	
	@Test
	public void testPermTypeEquals() {
		// TODO: stub
	}
}
