package typechecker.tests.javatests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeState.RUNTIME_STATE;
import plaid.runtime.Util;

import typechecker.tests.utils.TestUtils;

public class SynthesisTests {
	@BeforeClass
	public static void beforeClass() {
		// we need to do this so the Runtime doesn't deadlock if there are hooks into it
		// (e.g. in debug mode)
		PlaidRuntime.getRuntime().setRuntimeState(RUNTIME_STATE.RUNNING);
	}
	
	@Test
	public void testUnannotatedLetBinding() {
		PlaidObject x = TestUtils.id("x");
		PlaidObject exp = TestUtils.intLiteral(1);
		PlaidObject body = x;
		PlaidObject let = TestUtils.let(x, exp, body);
		PlaidObject typechecker = TestUtils.typechecker();
		PlaidObject resultPermType = Util.call(Util.lookup("visitUnannotatedLetBinding", typechecker), let);

		// Compare the results to what is expected.
		PlaidObject perm = TestUtils.unique();
		// TODO: Adapt this once we have a real type for integers
		PlaidObject type = TestUtils.type(new PlaidObject[0], new PlaidObject[0]);
		PlaidObject expectedPermType = TestUtils.permtype(perm, type);
		
		// This equals() call actually calls method== defined in PermType.plaid
		assertTrue(resultPermType.equals(expectedPermType));
	}
}
