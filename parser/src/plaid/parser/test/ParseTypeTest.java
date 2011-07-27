package plaid.parser.test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.PlaidCoreParser;

import plaid.parser.ast.*;
import plaid.parser.ast.Permission.PermissionKind;

public class ParseTypeTest {
	private void testPermission(String permString, PermissionKind permKind) throws ParseException {
		PlaidCoreParser pp = parserFromString(permString);
		Permission parsedAST = pp.Permission();
		Permission goalAST = new Permission(null, permKind, null);
		Assert.assertTrue(parsedAST.equivalent(goalAST));
	}
	
	@Test
	public void testImmutable() throws ParseException{
		testPermission("immutable", PermissionKind.IMMUTABLE);
	}
	
	@Test
	public void testUnique() throws ParseException{
		testPermission("unique", PermissionKind.UNIQUE);
	}
	
	@Test
	public void testNone() throws ParseException{
		testPermission("none", PermissionKind.NONE);
	}
	
	@Test
	public void testShared() throws ParseException{
		testPermission("shared", PermissionKind.SHARED);
	}

	
	private PlaidCoreParser parserFromString(String code) {
		try {
			PlaidCoreParser pcp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
			return pcp;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
