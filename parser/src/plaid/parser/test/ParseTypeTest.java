package plaid.parser.test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.PlaidCoreParser;

import plaid.parser.ast.*;
import plaid.parser.ast.Permission.PermissionKind;
import static plaid.parser.test.astfactory.ASTFactory.*;

public class ParseTypeTest {
	private PlaidCoreParser parserFromString(String code) {
		try {
			PlaidCoreParser pcp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
			return pcp;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void testPermission(String permString, PermissionKind permKind) {
		try { 
			PlaidCoreParser pp = parserFromString(permString);
			Permission parsedAST = pp.Permission();
			Permission goalAST = new Permission(null, permKind, Expression.EMPTY);
			Assert.assertTrue(parsedAST.equivalent(goalAST));
		} catch (ParseException e) {
			Assert.fail("ParseException: " + e.getMessage());
		}
	}
	
	@Test
	public void testImmutable(){
		testPermission("immutable", PermissionKind.IMMUTABLE);
	}
	
	@Test
	public void testUnique(){
		testPermission("unique", PermissionKind.UNIQUE);
	}
	
	@Test
	public void testNone(){
		testPermission("none", PermissionKind.NONE);
	}
	
	@Test
	public void testShared(){
		testPermission("shared", PermissionKind.SHARED);
	}
	
	private void testNominalType(String code, QualifiedIdentifier qi) throws ParseException{
			testNominalType(code, Permission.EMPTY, qi);
	}
	
	private void testNominalType(String code, Permission p, 
			QualifiedIdentifier qi) throws ParseException{
		PlaidCoreParser pcp = parserFromString(code);
		Type parsedType = pcp.Type();
		Type goalType = new NominalObjectType(null, p, 
				qi,
				new ArrayList<MetaType>());
		Assert.assertTrue("Goal and parsed ASTs don't match.", parsedType.equivalent(goalType));
	}
	
	@Test
	public void testIdType() throws ParseException {
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, Collections.singletonList(new Identifier(null, "hello")));
		testNominalType("hello",qi);
	}
	
	@Test
	public void testQIType() throws ParseException {
		List<Identifier> ids = new ArrayList<Identifier>();
		ids.add(new Identifier(null, "hello"));
		ids.add(new Identifier(null, "world"));
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, ids);
		testNominalType("hello.world",qi);
	}
	
	@Test
	public void testQILongType() throws ParseException {
		List<Identifier> ids = new ArrayList<Identifier>();
		ids.add(new Identifier(null, "hello"));
		ids.add(new Identifier(null, "world"));
		ids.add(new Identifier(null, "mighty"));
		ids.add(new Identifier(null, "max"));
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, ids);
		testNominalType("hello.world.mighty.max",qi);
	}
	
	@Test
	public void testQILongImmutableType() throws ParseException {
		List<Identifier> ids = new ArrayList<Identifier>();
		ids.add(new Identifier(null, "hello"));
		ids.add(new Identifier(null, "world"));
		ids.add(new Identifier(null, "mighty"));
		ids.add(new Identifier(null, "max"));
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, ids);
		Permission p = new Permission(null, PermissionKind.IMMUTABLE, Expression.EMPTY);
		testNominalType("immutable hello.world.mighty.max",p,qi);
	}
	
	@Test
	public void testQILongUniqueType() throws ParseException {
		List<Identifier> ids = new ArrayList<Identifier>();
		ids.add(new Identifier(null, "hello"));
		ids.add(new Identifier(null, "world"));
		ids.add(new Identifier(null, "mighty"));
		ids.add(new Identifier(null, "max"));
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, ids);
		Permission p = new Permission(null, PermissionKind.UNIQUE, Expression.EMPTY);
		testNominalType("unique hello.world.mighty.max",p,qi);
	}
	
	
	private void testLambdaType(String code, List<ArgSpec> argsSpec, Type returnType) 
	throws ParseException{
		Type goalType = new LambdaType(null,new ArrayList<MetaType>(),argsSpec,
				new HashMap<Identifier,ArgSpec>(), returnType);
		testLambdaType(code, goalType);
	}
	
	private void testLambdaType(String code, Type goalType) throws ParseException {
		PlaidCoreParser pcp = parserFromString(code);
		Type parsedType = pcp.Type();
		Assert.assertTrue("Goal and parsed ASTs don't match.", parsedType.equivalent(goalType));
	}
	
	@Test
	public void testNoArgLambdaType() throws ParseException {
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, Collections.singletonList(new Identifier(null, "hello")));
		Type returnType = new NominalObjectType(null, Permission.EMPTY, 
				qi,
				new ArrayList<MetaType>());
		testLambdaType("()->hello", LambdaType(returnType));
	}
	
	@Test
	public void testOneArgLambdaType() throws ParseException {
		Type type = NominalObjectType(QualifiedIdentifier("hello"));
		ArgSpec argspec = ArgSpec(type);
		testLambdaType("(hello)->hello", Collections.singletonList(argspec), type);
	}
	
	@Test
	public void testOneArgSpecLambdaType() throws ParseException {
		Type type = NominalObjectType(QualifiedIdentifier("hello"));
		ArgSpec argspec = ArgSpec(type,type);
		testLambdaType("(hello>>hello)->hello", Collections.singletonList(argspec), type);
	}
	
	@Test
	public void testLambdaReturnType() throws ParseException {
		Type type = NominalObjectType(QualifiedIdentifier("hello"));
		ArgSpec argSpec = ArgSpec(type,type);
		List<ArgSpec> argsSpec = Collections.singletonList(argSpec);
		testLambdaType("(hello>>hello)->(hello>>hello)->hello", 
				LambdaType(argsSpec, LambdaType(argsSpec,type)));
	}
	
	
}
