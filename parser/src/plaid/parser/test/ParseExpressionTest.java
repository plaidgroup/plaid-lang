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
import plaid.parser.ast.Assignment;
import plaid.parser.ast.BlockExpr;
import plaid.parser.ast.Cast;
import plaid.parser.ast.Decl;
import plaid.parser.ast.Dereference;
import plaid.parser.ast.DestructiveDereference;
import plaid.parser.ast.DoubleLiteral;
import plaid.parser.ast.Expression;
import plaid.parser.ast.Freeze;
import plaid.parser.ast.GroupDecl;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.IntLiteral;
import plaid.parser.ast.MethodCall;
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
	 **                      InfixExpr                        **
	 ************************************************************/
	@Test
	public void parseCast() throws ParseException, UnsupportedEncodingException {
		String code = "x as Foo";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.InfixExpr();
		assertTrue(e instanceof Cast );
		// TODO: add check for equality
	}
	
	/************************************************************
	 **                      UnaryExp                          **
	 ************************************************************/
	@Test
	public void parseUnaryMinus() throws ParseException, UnsupportedEncodingException {
		String code = "-x";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.UnaryExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.-()"));		
	}
	
	@Test
	public void parseUnaryPlus() throws ParseException, UnsupportedEncodingException {
		String code = "+x";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.UnaryExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.+()"));		
	}
	
	@Test
	public void parseUnaryNot() throws ParseException, UnsupportedEncodingException {
		String code = "!x";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.UnaryExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.!()"));		
	}

	@Test
	public void parseUnaryTilde() throws ParseException, UnsupportedEncodingException {
		String code = "~x";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.UnaryExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.~()"));		
	}

	/************************************************************
	 **            MultiplicativeExpression                     **
	 ************************************************************/
	@Test
	public void parseMultiply() throws ParseException, UnsupportedEncodingException {
		String code = "x * y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.MultiplicativeExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.*(y)"));		
	}
	
	@Test
	public void parseDivide() throws ParseException, UnsupportedEncodingException {
		String code = "x / y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.MultiplicativeExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x./(y)"));		
	}
	
	@Test
	public void parseModulo() throws ParseException, UnsupportedEncodingException {
		String code = "x % y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.MultiplicativeExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.%(y)"));		
	}	

	
	/************************************************************
	 **                AdditiveExpression                      **
	 ************************************************************/
	@Test
	public void parseAdd() throws ParseException, UnsupportedEncodingException {
		String code = "x + y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.AdditiveExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.+(y)"));		
	}	

	@Test
	public void parseSubstract() throws ParseException, UnsupportedEncodingException {
		String code = "x - y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.AdditiveExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.-(y)"));		
	}
	
	/************************************************************
	 **                   ShiftExpression                      **
	 ************************************************************/
	@Test
	public void parseLeftShift() throws ParseException, UnsupportedEncodingException {
		String code = "x << y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ShiftExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.<<(y)"));		
	}
	
	@Test
	public void parseRightShift() throws ParseException, UnsupportedEncodingException {
		String code = "x >> y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ShiftExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.>>(y)"));		
	}
	
	@Test
	public void parseRightRightShift() throws ParseException, UnsupportedEncodingException {
		String code = "x >>> y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ShiftExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.>>>(y)"));		
	}
	
	/************************************************************
	 **                RelationalExpression                    **
	 ************************************************************/
	@Test
	public void parseSmaller() throws ParseException, UnsupportedEncodingException {
		String code = "x < y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.RelationalExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.<(y)"));		
	}
	
	@Test
	public void parseSmallerEquals() throws ParseException, UnsupportedEncodingException {
		String code = "x <= y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.RelationalExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.<=(y)"));		
	}

	@Test
	public void parseBigger() throws ParseException, UnsupportedEncodingException {
		String code = "x > y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.RelationalExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.>(y)"));		
	}
	
	@Test
	public void parseBiggerEquals() throws ParseException, UnsupportedEncodingException {
		String code = "x >= y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.RelationalExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.>=(y)"));		
	}
	
	/************************************************************
	 **                EqualityExpression                     **
	 ************************************************************/
	@Test
	public void parseEqualsEquals() throws ParseException, UnsupportedEncodingException {
		String code = "x == y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.EqualityExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.==(y)"));		
	}
	
	@Test
	public void parseNotEquals() throws ParseException, UnsupportedEncodingException {
		String code = "x != y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.EqualityExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.!=(y)"));		
	}
	
	/************************************************************
	 **                AndExpression                     **
	 ************************************************************/
	@Test
	public void parseAnd() throws ParseException, UnsupportedEncodingException {
		String code = "x & y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.AndExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.&(y)"));		
	}
	
	/************************************************************
	 **                ExclusiveOrxpression                    **
	 ************************************************************/
	@Test
	public void parseExclusiveOr() throws ParseException, UnsupportedEncodingException {
		String code = "x ^ y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ExclusiveOrExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.^(y)"));		
	}
	
	/************************************************************
	 **                InclusiveOrExpression                    **
	 ************************************************************/
	@Test
	public void parseInclusiveOr() throws ParseException, UnsupportedEncodingException {
		String code = "x | y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.InclusiveOrExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.|(y)"));		
	}
	
	/************************************************************
	 **                 ConditionalAndExpression               **
	 ************************************************************/
	@Test
	public void parseConditionalAnd() throws ParseException, UnsupportedEncodingException {
		String code = "x && y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ConditionalAndExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.&&(y)"));		
	}
	
	/************************************************************
	 **                 ConditionalOrExpression               **
	 ************************************************************/
	@Test
	public void parseConditionalOr() throws ParseException, UnsupportedEncodingException {
		String code = "x || y";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ConditionalOrExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.||(y)"));		
	}
	
	/************************************************************
	 **                 ConditionalExpression               **
	 ************************************************************/
	@Test
	public void parseConditional() throws ParseException, UnsupportedEncodingException {
		String code = "x?y:z";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.ConditionalExpression();
		assertTrue(e instanceof MethodCall );
		assertTrue(e.toString().equals("x.?(y).?(z)"));		
	}
	
	/************************************************************
	 **                        Exp1                            **
	 ************************************************************/
	@Test
	public void parseAssignmentVar() throws ParseException, UnsupportedEncodingException {
		String code = "x = 1";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.Expr1();
		assertTrue(e instanceof Assignment );
		assertTrue(e.toString().equals("x=1"));		
	}
	
	@Test
	public void parseAssignmentField() throws ParseException, UnsupportedEncodingException {
		String code = "this.x = 1";
		PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expression e = pp.Expr1();
		assertTrue(e instanceof Assignment );
		assertTrue(e.toString().equals("this.x=1"));		
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
