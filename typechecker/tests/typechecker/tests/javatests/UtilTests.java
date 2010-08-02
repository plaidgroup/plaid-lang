package typechecker.tests.javatests;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeState.RUNTIME_STATE;
import plaid.runtime.Util;
import typechecker.tests.utils.TestUtils;

/**
 * Tests to make sure the test utils work correctly.
 * 
 * @author mhahnenberg
 *
 */
public class UtilTests {
	@BeforeClass
	public static void beforeClass() {
		// we need to do this so the Runtime doesn't deadlock if there are hooks into it
		// (e.g. in debug mode)
		PlaidRuntime.getRuntime().setRuntimeState(RUNTIME_STATE.RUNNING);
	}
	
	@Test
	public void testMethodDeclConstruction() {
		// TODO
	}
	
	@Test
	public void testFieldDeclConstruction() {
		// TODO
	}
	
	@Test
	public void testTypeConstructionNoAbbrevsNoDecls() {
		// TODO: These hang for some reason
		PlaidObject[] abbrevs = {};
		PlaidObject[] decls = {};
		
		PlaidObject structType = TestUtils.type(abbrevs, decls);
		
	}
	
	@Test
	public void testTypeConstructionNoAbbrevs() {
		// TODO: these hang for some reason
		PlaidObject[] fooAbbrevs = {TestUtils.id("String")};
		PlaidObject[] fooDecls = {};
		
		PlaidObject[] barAbbrevs = {TestUtils.id("Integer")};
		PlaidObject[] barDecls = {};
		
		PlaidObject fooID = TestUtils.id("foo");
		PlaidObject fooField = TestUtils.fieldType(fooID, TestUtils.permtype(TestUtils.full(), TestUtils.type(fooAbbrevs, fooDecls)));
		PlaidObject barID = TestUtils.id("bar");
		PlaidObject barField = TestUtils.fieldType(barID, TestUtils.permtype(TestUtils.full(), TestUtils.type(barAbbrevs, barDecls)));
		
		PlaidObject[] abbrevs = {};
		PlaidObject[] decls = {
				fooField,
				barField
		};
		
		PlaidObject structType = TestUtils.type(abbrevs, decls);
		
		Set<PlaidObject> typeAbbrevs = (Set<PlaidObject>)Util.toPlaidJavaObject(TestUtils.getField("typeAbbrevs", structType)).getJavaObject();
		assertTrue(typeAbbrevs.isEmpty());
		
		Set<PlaidObject> typeDecls = (Set<PlaidObject>)Util.toPlaidJavaObject(TestUtils.getField("typeDecls", structType)).getJavaObject();
		assertTrue(typeDecls.contains(fooField));
		assertTrue(typeDecls.contains(barField));
	}
	
	@Test
	public void testTypeConstructionNoDecls() {
		PlaidObject[] abbrevs = {
				
		};
		PlaidObject[] decls = {};
		
		PlaidObject structType = TestUtils.type(abbrevs, decls);
		
	}
}
