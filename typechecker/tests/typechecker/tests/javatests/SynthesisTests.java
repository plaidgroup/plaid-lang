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
	public void testAnnotatedLetBinding() {
		// TODO: stub
	}
	
	@Test
	public void testAssignment() {
		// TODO: stub
	}
	
	@Test
	public void testCase() {
		// TODO: stub
	}
	
	@Test
	public void testCompilationUnit() {
		// TODO: Not sure what this even means
	}
	
	@Test
	public void testFieldDecl() {
		// TODO: stub
	}
	
	@Test
	public void testID() {
		PlaidObject x = TestUtils.id("x");
		PlaidObject xPermType = TestUtils.permtype(TestUtils.unique(), TestUtils.getStructuralTypeFromAbbrev("plaid.lang.Integer"));
		PlaidObject typechecker = TestUtils.typechecker();
		PlaidObject context = TestUtils.getField("context", typechecker);
		TestUtils.addToContext(context, x, xPermType);
		PlaidObject resultPermType = Util.call(Util.lookup("visitID", typechecker), x);
		
		// TODO: Compare the results with the expected
	}
	
	@Test
	public void testIntLiteral() {
		PlaidObject hello = Util.integer(42);
		PlaidObject typechecker = TestUtils.typechecker();
		PlaidObject resultPermType = Util.call(Util.lookup("visitIntLiteral", typechecker), hello);
		
		// TODO: Compare the results with the expected
	}
	
	@Test
	public void testLambda() {
		// TODO: stub
	}
	
	@Test
	public void testLambdaApplication() {
		// TODO: stub
	}
	
	@Test
	public void testLNFApplication() {
		// TODO: stub
	}
	
	@Test
	public void testLNFChangeState() {
		// TODO: stub
	}
	
	@Test
	public void testLNFDereference() {
		// TODO: stub
	}
	
	@Test
	public void testLNFDestructiveDereference() {
		// TODO: stub
	}
	
	@Test
	public void testLNFMatch() {
		// TODO: stub
	}
	
	@Test
	public void testLNFMethodInvocation() {
		// TODO: stub
	}
	
	@Test
	public void testMethodDecl() {
		// TODO: stub
	}
	
	@Test
	public void testNewInstance() {
		// TODO: stub
	}
	
	@Test
	public void testQI() {
		// TODO: stub
	}
	
	@Test
	public void testStateDecl() {
		// TODO: stub
	}
	
	@Test
	public void testStringLiteral() {
		PlaidObject hello = Util.string("Hello!");
		PlaidObject typechecker = TestUtils.typechecker();
		PlaidObject resultPermType = Util.call(Util.lookup("visitStringLiteral", typechecker), hello);
		
		// TODO: Compare the results with the expected
	}
	
	@Test
	public void testThrows() {
		// TODO: we need a typing rule for this
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
	
	@Test
	public void testUnitLiteral() {
		PlaidObject unit = Util.unit();
		PlaidObject typechecker = TestUtils.typechecker();
		PlaidObject resultPermType = Util.call(Util.lookup("visitUnitLiteral", typechecker), unit);
		
		// TODO: Compare the results with the expected
	}
	
	@Test
	public void testWith() {
		// TODO: stub
	}
}
