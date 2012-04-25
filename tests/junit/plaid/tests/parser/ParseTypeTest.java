package plaid.tests.parser;

import static plaid.parser.test.astfactory.ASTFactory.ArgSpec;
import static plaid.parser.test.astfactory.ASTFactory.Immutable;
import static plaid.parser.test.astfactory.ASTFactory.LambdaType;
import static plaid.parser.test.astfactory.ASTFactory.NominalObjectType;
import static plaid.parser.test.astfactory.ASTFactory.OptionType;
import static plaid.parser.test.astfactory.ASTFactory.None;
import static plaid.parser.test.astfactory.ASTFactory.QualifiedIdentifier;
import static plaid.parser.test.astfactory.ASTFactory.Shared;
import static plaid.parser.test.astfactory.ASTFactory.Unique;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.PlaidCoreParser;
import plaid.parser.ast.ASTNode;
import plaid.parser.ast.Arg;
import plaid.parser.ast.ArgSpec;
import plaid.parser.ast.ConcreteType;
import plaid.parser.ast.Expr;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.LambdaStructure;
import plaid.parser.ast.Permission;
import plaid.parser.ast.QualifiedIdentifier;
import plaid.parser.ast.StaticType;
import plaid.parser.ast.Type;

public class ParseTypeTest {
	private PlaidCoreParser parserFromString(String code) {
		try {
			PlaidCoreParser pcp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
			return pcp;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void testPermission(String permString, Permission perm) {
		try { 
			PlaidCoreParser pp = parserFromString(permString);
			Permission parsedAST = pp.Permission();
			Permission goalAST = perm;
			Assert.assertTrue(parsedAST.equivalent(goalAST));
		} catch (ParseException e) {
			Assert.fail("ParseException: " + e.getMessage());
		}
	}
	
	@Test
	public void testImmutable(){
		testPermission("immutable", Immutable());
	}
	
	@Test
	public void testUnique(){
		testPermission("unique", Unique());
	}
	
	@Test
	public void testNone(){
		testPermission("none", None());
	}
	
	@Test
	public void testShared(){
		testPermission("shared", Shared(Expr.EMPTY));
	}
	
	private void testNominalType(String code, QualifiedIdentifier qi) throws ParseException{
			testNominalType(code, Permission.EMPTY, qi);
	}
	
	private void testNominalType(String code, Permission p, 
			QualifiedIdentifier qi) throws ParseException{
		PlaidCoreParser pcp = parserFromString(code);
		Type parsedType = pcp.Type();
		Type goalType = NominalObjectType(p, qi);
		Assert.assertTrue("Goal and parsed ASTs don't match.", parsedType.equivalent(goalType));
	}
	
	private void testOptionType(String code, QualifiedIdentifier qi) throws ParseException{
		testOptionType(code, Permission.EMPTY, qi);
	}
	
	private void testOptionType(String code, Permission p, 
			QualifiedIdentifier qi) throws ParseException{
		PlaidCoreParser pcp = parserFromString(code);
		Type parsedType = pcp.Type();
		Type goalType = OptionType(p, qi);
		Assert.assertTrue("Goal and parsed ASTs don't match.", parsedType.equivalent(goalType));
	}
	
	@Test
	public void testIdType() throws ParseException {
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, Collections.singletonList(new Identifier(null, "hello")));
		testNominalType("hello",qi);
	}

	@Test
	public void testIdOptionType() throws ParseException {
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, Collections.singletonList(new Identifier(null, "hello")));
		testOptionType("?hello",qi);
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
	public void testQIOptionType() throws ParseException {
		List<Identifier> ids = new ArrayList<Identifier>();
		ids.add(new Identifier(null, "hello"));
		ids.add(new Identifier(null, "world"));
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, ids);
		testOptionType("?hello.world",qi);
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
		Permission p = Immutable();
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
		Permission p = Unique();
		testNominalType("unique hello.world.mighty.max",p,qi);
	}
	
	@Test
	public void testQILongUniqueOptionType() throws ParseException {
		List<Identifier> ids = new ArrayList<Identifier>();
		ids.add(new Identifier(null, "hello"));
		ids.add(new Identifier(null, "world"));
		ids.add(new Identifier(null, "mighty"));
		ids.add(new Identifier(null, "max"));
		QualifiedIdentifier qi = 
			new QualifiedIdentifier(null, ids);
		Permission p = Unique();
		testOptionType("unique ?hello.world.mighty.max",p,qi);
	}
	
	
	private void testLambdaType(String code, List<ArgSpec> argsSpec, Type returnType) 
	throws ParseException{
		Type goalType = new ConcreteType(ASTNode.DEFAULT_TOKEN, 
					Permission.EMPTY,
					new LambdaStructure(null,new ArrayList<StaticType>(),argsSpec, returnType));
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
		Type returnType = NominalObjectType(qi);
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
