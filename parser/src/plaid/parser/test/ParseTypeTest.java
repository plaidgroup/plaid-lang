package plaid.parser.test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.PlaidCoreParser;

import plaid.parser.ast.*;

public class ParseTypeTest {
	@Test
	public void testImmutable() throws ParseException{
		PlaidCoreParser pp = parserFromString("immutable");
		Permission parsedAST = pp.Permission();
		Permission goalAST = new Permission(null, Permission.PermissionKind.IMMUTABLE, null);
		Assert.assertTrue(parsedAST.equivalent(goalAST));
	}
	
	@Test
	public void testUnique() throws ParseException{
		PlaidCoreParser pp = parserFromString("unique");
		Permission parsedAST = pp.Permission();
		Permission goalAST = new Permission(null, Permission.PermissionKind.UNIQUE, null);
		Assert.assertTrue(parsedAST.equivalent(goalAST));
	}
	
	@Test
	public void testNone() throws ParseException{
		PlaidCoreParser pp = parserFromString("none");
		Permission parsedAST = pp.Permission();
		Permission goalAST = new Permission(null, Permission.PermissionKind.NONE, null);
		Assert.assertTrue(parsedAST.equivalent(goalAST));
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
