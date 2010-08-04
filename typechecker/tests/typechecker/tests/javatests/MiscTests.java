package typechecker.tests.javatests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeState.RUNTIME_STATE;
import typechecker.tests.utils.TestUtils;

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
		// Generate two sets of permissions so that equals() doesn't rely on references
		PlaidObject[] perms1 = {
			TestUtils.full(),
			TestUtils.immutable(),
			TestUtils.pure(),
			TestUtils.shared(),
			TestUtils.unique()
		};
		
		PlaidObject[] perms2 = {
			TestUtils.full(),
			TestUtils.immutable(),
			TestUtils.pure(),
			TestUtils.shared(),
			TestUtils.unique()
		};
		
		for (int i = 0; i < perms1.length; ++i) {
			for (int j = 0; j < perms2.length; ++j) {
				boolean result = perms1[i].equals(perms2[j]);
				boolean expected = (i == j);
				
				// equals() should return true iff i == j
				assertTrue(result == expected);
			}
		}
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
