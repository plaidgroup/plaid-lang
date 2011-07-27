package plaid.parser.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.PlaidCoreParser;
import plaid.parser.ast.Application;
import plaid.parser.ast.ArgumentExpression;
import plaid.parser.ast.BlockExpr;
import plaid.parser.ast.Decl;
import plaid.parser.ast.Dereference;
import plaid.parser.ast.DestructiveDereference;
import plaid.parser.ast.DoubleLiteral;
import plaid.parser.ast.Expression;
import plaid.parser.ast.Freeze;
import plaid.parser.ast.GroupDecl;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.IntLiteral;
import plaid.parser.ast.Modifier;
import plaid.parser.ast.NewInstance;
import plaid.parser.ast.StateOp;
import plaid.parser.ast.StateOpRemove;
import plaid.parser.ast.StateOpRename;
import plaid.parser.ast.StatePrim;
import plaid.parser.ast.StringLiteral;

public class ParseExpressionTest {

	/************************************************************
	 **                      SimpleExpr1                       **
	 ************************************************************/
	 
 	@Test
	public void parseIntLiteral() throws ParseException, UnsupportedEncodingException {
		String code = "1234";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.Literal();
		assertTrue(e instanceof IntLiteral );
		assertTrue( code.equals(e.toString()));
	}

	@Test
	public void parseDoubleLiteral() throws ParseException, UnsupportedEncodingException {
		String code = "12.34";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.Literal();
		assertTrue(e instanceof DoubleLiteral );
	}

	@Test
	public void parseStringLiteral() throws ParseException, UnsupportedEncodingException {
		String code = "\"12.34\"";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.Literal();
		assertTrue(e instanceof StringLiteral );
		assertTrue( code.equals(e.toString()));
	}

	@Test
	public void parseStringLiteralFail() throws ParseException, UnsupportedEncodingException {
		String code = "12.34";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.Literal();
		assertFalse(e instanceof StringLiteral );
	}
	
	@Test
	public void parseIdentifier() throws ParseException, UnsupportedEncodingException {
		String code = "x";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Identifier );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseThis() throws ParseException, UnsupportedEncodingException {
		String code = "this";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Identifier );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseArgumentExpressionEmpty() throws ParseException, UnsupportedEncodingException {
		String code = "()";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof ArgumentExpression );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseArgumentExpressionOne() throws ParseException, UnsupportedEncodingException {
		String code = "(1)";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof ArgumentExpression );
		assertTrue( code.equals(e.toString()));
	}

	@Test
	public void parseArgumentExpressionOneTwo() throws ParseException, UnsupportedEncodingException {
		String code = "(1,2)";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof ArgumentExpression );
		assertTrue( code.equals(e.toString()));
	}
		
	@Test
	public void parseDerefThisDotField() throws ParseException, UnsupportedEncodingException {
		String code = "this.f";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Dereference );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseDerefThisDotXDotField() throws ParseException, UnsupportedEncodingException {
		String code = "this.x.f";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Dereference );
		assertTrue( code.equals(e.toString()));
	}

	@Test
	public void parseDestructiveDerefThisDotField() throws ParseException, UnsupportedEncodingException {
		String code = "this!f";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof DestructiveDereference );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseApplication() throws ParseException, UnsupportedEncodingException {
		String code = "m()";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseApplicationWithArgumentList() throws ParseException, UnsupportedEncodingException {
		String code = "m(1,2,3)";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseApplicationWithMetaArgs() throws ParseException, UnsupportedEncodingException {
		String code = "m<a,b,c>()";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseApplicationWithMetaArgsIncludingThis() throws ParseException, UnsupportedEncodingException {
		String code = "this.m<a,this.c>(1,2,3)";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( code.equals(e.toString()));
	}
	
	/************************************************************
	 **                      SimpleExpr2                       **
	 ************************************************************/
	@Test
	public void parseSingleBlockAsApplication() throws ParseException, UnsupportedEncodingException {
		String code = "foo{}";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr2();
		assertTrue(e instanceof Application );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseMultipleBlocksAsApplication() throws ParseException, UnsupportedEncodingException {
		String code = "foo{}{}{}";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr2();
		assertTrue(e instanceof Application );
		assertTrue( code.equals(e.toString()));
	}

	/************************************************************
	 **                      SimpleExpr                        **
	 ************************************************************/
	@Test
	public void parseEmptyBlock() throws ParseException, UnsupportedEncodingException {
		String code = "{}";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr();
		assertTrue(e instanceof BlockExpr );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseNew() throws ParseException, UnsupportedEncodingException {
		String code = "new foo;";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.SimpleExpr();
		assertTrue(e instanceof NewInstance );
		assertTrue( code.equals(e.toString()+";"));
	}
	
	/************************************************************
	 **                      StateOP                           **
	 ************************************************************/
	@Test
	public void parseStateRemoveOp() throws ParseException, UnsupportedEncodingException {
		String code = "remove foo;";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		StateOp e = pp.StateOp();
		assertTrue(e instanceof StateOpRemove );
		assertTrue( code.equals(e.toString()));
	}
	
	@Test
	public void parseStateRenameOp() throws ParseException, UnsupportedEncodingException {
		String code = "rename foo as bar;";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		StateOp e = pp.StateOp();
		assertTrue(e instanceof StateOpRename );
		assertTrue( code.equals(e.toString()));
	}

	/************************************************************
	 **                      GroupDecl                         **
	 ************************************************************/
	@Test
	public void parseGroupDecl() throws ParseException, UnsupportedEncodingException {
		String code = "group foo = new group;";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.GroupDecl(new ArrayList<Modifier>());
		assertTrue(e instanceof GroupDecl );
		assertTrue( code.equals(e.toString()));
	}
	
	/************************************************************
	 **                      StatePrim                         **
	 ************************************************************/
	@Test
	public void parseFreeze() throws ParseException, UnsupportedEncodingException {
		String code = "freeze x;";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		StatePrim e = pp.StatePrim();
		assertTrue(e instanceof Freeze );
		assertTrue( code.equals(e.toString()));
	}
}
