package typechecker.tests.javatests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeState.RUNTIME_STATE;
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
	public void testIntegerTypeAbbrev() {
		PlaidObject intType = TestUtils.getStructuralTypeFromAbbrev("plaid.lang.Integer");
		System.out.println("break");
	}
	
	@Test
	public void testFieldDeclConstruction() {
		PlaidObject[] fooAbbrevs = {TestUtils.id("String")};
		PlaidObject[] fooDecls = {};
		PlaidObject fooID = TestUtils.id("foo");
		
		PlaidObject fooField = TestUtils.fieldType(fooID, TestUtils.permtype(TestUtils.full(), TestUtils.type(fooAbbrevs, fooDecls)));
		assertTrue(TestUtils.full().equals(TestUtils.getField("perm", TestUtils.getField("permType", fooField))));
	}
	
	@Test
	public void testTypeConstructionNoAbbrevsNoDecls() {
		PlaidObject[] abbrevs = {};
		PlaidObject[] decls = {};
		
		PlaidObject structType = TestUtils.type(abbrevs, decls);
		
		Set<PlaidObject> typeAbbrevs = TestUtils.getTypeAbbrevs(structType);
		Set<PlaidObject> typeDecls = TestUtils.getTypeDecls(structType);
		assertTrue(typeAbbrevs.isEmpty());
		assertTrue(typeDecls.isEmpty());
	}
	
	@Test
	public void testTypeConstructionNoAbbrevs() {
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
		
		Set<PlaidObject> typeAbbrevs = TestUtils.getTypeAbbrevs(structType);
		assertTrue(typeAbbrevs.isEmpty());
		
		Set<PlaidObject> typeDecls = TestUtils.getTypeDecls(structType);
		assertTrue(typeDecls.contains(fooField));
		assertTrue(typeDecls.contains(barField));
	}
	
	@Test
	public void testTypeConstructionNoDecls() {
		PlaidObject intAbbrev = TestUtils.id("Integer");
		
		PlaidObject[] abbrevs = {
				intAbbrev
		};
		PlaidObject[] decls = {};
		
		PlaidObject structType = TestUtils.type(abbrevs, decls);
		Set<PlaidObject> typeAbbrevs = TestUtils.getTypeAbbrevs(structType);
		Set<PlaidObject> typeDecls = TestUtils.getTypeDecls(structType);
		assertTrue(typeAbbrevs.contains(intAbbrev));
		assertTrue(typeDecls.isEmpty());
	}
}
