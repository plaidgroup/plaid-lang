package plaid.tests.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static plaid.parser.test.astfactory.ASTFactory.AbstractFieldDecl;
import static plaid.parser.test.astfactory.ASTFactory.AbstractMethodDecl;
import static plaid.parser.test.astfactory.ASTFactory.AbstractStateDecl;
import static plaid.parser.test.astfactory.ASTFactory.AbstractStateValDecl;
import static plaid.parser.test.astfactory.ASTFactory.Application;
import static plaid.parser.test.astfactory.ASTFactory.Arg;
import static plaid.parser.test.astfactory.ASTFactory.ArgSpec;
import static plaid.parser.test.astfactory.ASTFactory.ArgumentExpr;
import static plaid.parser.test.astfactory.ASTFactory.Assignment;
import static plaid.parser.test.astfactory.ASTFactory.AtomicBlock;
import static plaid.parser.test.astfactory.ASTFactory.BlockExpr;
import static plaid.parser.test.astfactory.ASTFactory.Cast;
import static plaid.parser.test.astfactory.ASTFactory.CompilationUnit;
import static plaid.parser.test.astfactory.ASTFactory.ConcreteFieldDecl;
import static plaid.parser.test.astfactory.ASTFactory.ConcreteMethodDecl;
import static plaid.parser.test.astfactory.ASTFactory.ConcreteStateDecl;
import static plaid.parser.test.astfactory.ASTFactory.ConcreteStateValDecl;
import static plaid.parser.test.astfactory.ASTFactory.DeclList;
import static plaid.parser.test.astfactory.ASTFactory.DefaultCase;
import static plaid.parser.test.astfactory.ASTFactory.Dereference;
import static plaid.parser.test.astfactory.ASTFactory.DestructiveDereference;
import static plaid.parser.test.astfactory.ASTFactory.DoubleLiteral;
import static plaid.parser.test.astfactory.ASTFactory.Freeze;
import static plaid.parser.test.astfactory.ASTFactory.GroupArg;
import static plaid.parser.test.astfactory.ASTFactory.GroupDecl;
import static plaid.parser.test.astfactory.ASTFactory.IMMUTABLE;
import static plaid.parser.test.astfactory.ASTFactory.Identifier;
import static plaid.parser.test.astfactory.ASTFactory.Immutable;
import static plaid.parser.test.astfactory.ASTFactory.Import;
import static plaid.parser.test.astfactory.ASTFactory.InfixOperator;
import static plaid.parser.test.astfactory.ASTFactory.IntLiteral;
import static plaid.parser.test.astfactory.ASTFactory.Lambda;
import static plaid.parser.test.astfactory.ASTFactory.Match;
import static plaid.parser.test.astfactory.ASTFactory.MetaArgs;
import static plaid.parser.test.astfactory.ASTFactory.NewInstance;
import static plaid.parser.test.astfactory.ASTFactory.NominalObjectType;
import static plaid.parser.test.astfactory.ASTFactory.None;
import static plaid.parser.test.astfactory.ASTFactory.OVERRIDE;
import static plaid.parser.test.astfactory.ASTFactory.Annotation;
import static plaid.parser.test.astfactory.ASTFactory.Package;
import static plaid.parser.test.astfactory.ASTFactory.PatternCase;
import static plaid.parser.test.astfactory.ASTFactory.Protected;
import static plaid.parser.test.astfactory.ASTFactory.QualifiedIdentifier;
import static plaid.parser.test.astfactory.ASTFactory.REQUIRES;
import static plaid.parser.test.astfactory.ASTFactory.Replace;
import static plaid.parser.test.astfactory.ASTFactory.Shared;
import static plaid.parser.test.astfactory.ASTFactory.SplitBlock;
import static plaid.parser.test.astfactory.ASTFactory.StateChange;
import static plaid.parser.test.astfactory.ASTFactory.StateOpRemove;
import static plaid.parser.test.astfactory.ASTFactory.StateOpRename;
import static plaid.parser.test.astfactory.ASTFactory.StateRef;
import static plaid.parser.test.astfactory.ASTFactory.StringLiteral;
import static plaid.parser.test.astfactory.ASTFactory.TypeArg;
import static plaid.parser.test.astfactory.ASTFactory.UnaryOperator;
import static plaid.parser.test.astfactory.ASTFactory.Unique;
import static plaid.parser.test.astfactory.ASTFactory.UnpackInnerGroups;
import static plaid.parser.test.astfactory.ASTFactory.Val;
import static plaid.parser.test.astfactory.ASTFactory.Var;
import static plaid.parser.test.astfactory.ASTFactory.VarDecl;
import static plaid.parser.test.astfactory.ASTFactory.With;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.PlaidCoreParser;
import plaid.parser.ast.AbstractFieldDecl;
import plaid.parser.ast.AbstractMethodDecl;
import plaid.parser.ast.AbstractStateDecl;
import plaid.parser.ast.AbstractStateValDecl;
import plaid.parser.ast.Annotation;
import plaid.parser.ast.Application;
import plaid.parser.ast.Arg;
import plaid.parser.ast.ArgumentExpr;
import plaid.parser.ast.Assignment;
import plaid.parser.ast.AtomicBlock;
import plaid.parser.ast.BlockExpr;
import plaid.parser.ast.Case;
import plaid.parser.ast.Cast;
import plaid.parser.ast.CompilationUnit;
import plaid.parser.ast.ConcreteFieldDecl;
import plaid.parser.ast.ConcreteMethodDecl;
import plaid.parser.ast.ConcreteStateDecl;
import plaid.parser.ast.ConcreteStateValDecl;
import plaid.parser.ast.Decl;
import plaid.parser.ast.DefaultCase;
import plaid.parser.ast.Dereference;
import plaid.parser.ast.DestructiveDereference;
import plaid.parser.ast.DoubleLiteral;
import plaid.parser.ast.EmptyArgSpec;
import plaid.parser.ast.Expr;
import plaid.parser.ast.FieldDecl;
import plaid.parser.ast.Freeze;
import plaid.parser.ast.GroupDecl;
import plaid.parser.ast.GroupPermission;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.Import;
import plaid.parser.ast.InfixOperatorExpr;
import plaid.parser.ast.IntLiteral;
import plaid.parser.ast.Lambda;
import plaid.parser.ast.Match;
import plaid.parser.ast.MethodDecl;
import plaid.parser.ast.Modifier;
import plaid.parser.ast.NewInstance;
import plaid.parser.ast.PatternCase;
import plaid.parser.ast.QualifiedIdentifier;
import plaid.parser.ast.Replace;
import plaid.parser.ast.Specifier;
import plaid.parser.ast.SplitBlock;
import plaid.parser.ast.StateChange;
import plaid.parser.ast.StateExpr;
import plaid.parser.ast.StateOp;
import plaid.parser.ast.StateOpRemove;
import plaid.parser.ast.StateOpRename;
import plaid.parser.ast.StatePrim;
import plaid.parser.ast.StateRef;
import plaid.parser.ast.StateValDecl;
import plaid.parser.ast.StaticArg;
import plaid.parser.ast.Stmt;
import plaid.parser.ast.StringLiteral;
import plaid.parser.ast.Type;
import plaid.parser.ast.UnaryOperatorExpr;
import plaid.parser.ast.UnpackInnerGroups;
import plaid.parser.ast.VarDecl;
import plaid.parser.ast.With;

public class ParseExpressionTest {

	/************************************************************
	 **                      SimpleExpr1                       **
	 ************************************************************/	 
 	@Test
	public void parseQualifiedIdentifier() throws ParseException, UnsupportedEncodingException {
		final String code = "foobar.baz";
		final QualifiedIdentifier goal = QualifiedIdentifier("foobar", "baz");
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final QualifiedIdentifier qi = pp.QualifiedIdentifier();
		assertTrue( qi instanceof QualifiedIdentifier );
		assertTrue( goal.equivalent(qi) );
	}

	
	/************************************************************
	 **                      SimpleExpr1                       **
	 ************************************************************/	 
 	@Test
	public void parseIntLiteral() throws ParseException, UnsupportedEncodingException {
		final String code = "1234";
		final IntLiteral goal = IntLiteral(1234);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Literal();
		assertTrue( e instanceof IntLiteral );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseDoubleLiteral() throws ParseException, UnsupportedEncodingException {
		final String code = "12.34";
		final DoubleLiteral goal = DoubleLiteral(12.34);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Literal();
		assertTrue( e instanceof DoubleLiteral );
		assertTrue( goal.equivalent(goal) );
	}

	@Test
	public void parseStringLiteral() throws ParseException, UnsupportedEncodingException {
		final String code = "\"12.34\"";
		final StringLiteral goal = StringLiteral("12.34");
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Literal();
		assertTrue( e instanceof StringLiteral );
		assertTrue( goal.equivalent(goal) );
	}

	@Test
	public void parseStringLiteralFail() throws ParseException, UnsupportedEncodingException {
		final String code = "12.34";
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Literal();
		assertFalse(e instanceof StringLiteral );
	}
	
	@Test
	public void parseIdentifier() throws ParseException, UnsupportedEncodingException {
		final String code = "x";
		final Identifier goal = Identifier(code);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue( e instanceof Identifier );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseThis() throws ParseException, UnsupportedEncodingException {
		final String code = "this";
		final Identifier goal = Identifier("this");
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue( e instanceof Identifier );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseUserOperator() throws ParseException, UnsupportedEncodingException {
		final String code = "-->";
		final Identifier goal = Identifier("-->");
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.IdOrOperator();
		assertTrue(e instanceof Identifier );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseArgumentExprEmpty() throws ParseException, UnsupportedEncodingException {
		final String code = "()";
		final ArgumentExpr goal = ArgumentExpr();
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ArgumentExpr();
		assertTrue( e instanceof ArgumentExpr );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseArgumentExprOne() throws ParseException, UnsupportedEncodingException {
		final String code = "(1)";
		final ArgumentExpr goal = ArgumentExpr(IntLiteral(1));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ArgumentExpr();
		assertTrue( e instanceof ArgumentExpr );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseArgumentExprOneTwo() throws ParseException, UnsupportedEncodingException {
		final String code = "(1,2)";
		final ArgumentExpr goal = 
			ArgumentExpr(
				IntLiteral(1), 
				IntLiteral(2)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ArgumentExpr();
		assertTrue(e instanceof ArgumentExpr );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseDerefThisDotField() throws ParseException, UnsupportedEncodingException {
		final String code = "this.f";
		final Dereference goal = 
			Dereference(
				Identifier("this"),
				Identifier("f")
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Dereference );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseDerefThisDotXDotField() throws ParseException, UnsupportedEncodingException {
		final String code = "this.x.f";
		final Dereference goal =
			Dereference(
				Dereference(
					Identifier("this"),											
					Identifier("x")
				),
				Identifier("f")
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Dereference );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseDestructiveDerefThisDotField() throws ParseException, UnsupportedEncodingException {
		final String code = "this!f";
		final Dereference goal =
			DestructiveDereference(
				Identifier("this"),
				Identifier("f")
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof DestructiveDereference );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseApplication() throws ParseException, UnsupportedEncodingException {
		final String code = "m()";
		final Application goal = 
			Application(
				Identifier("m"),
				ArgumentExpr()
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseApplicationWithArgumentList() throws ParseException, UnsupportedEncodingException {
		final String code = "m(1,2,3)";
		final Application goal = 
			Application(
			 	Identifier("m"),
			 	ArgumentExpr(
		 			IntLiteral(1),
		 			IntLiteral(2),
		 			IntLiteral(3)
			 	)
			 );
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseApplicationWithMetaArgs() throws ParseException, UnsupportedEncodingException {
		final String code = "m<a,b,c>()";
		final Application goal =
			Application(
				Identifier("m"),
				MetaArgs(
					Identifier("a"),
					Identifier("b"),
					Identifier("c")
				),
				ArgumentExpr()
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseApplicationWithMetaArgsIncludingThis() throws ParseException, UnsupportedEncodingException {
		final String code = "this.m<a,this.c>(1,2,3)";
		final Application goal =
			Application(
				Dereference(
					Identifier("this"),
					Identifier("m")
				),
				MetaArgs(
					Identifier("a"),
					Dereference(
						Identifier("this"),
						Identifier("c")
					)							
				),
				ArgumentExpr(
					IntLiteral(1),
					IntLiteral(2),
					IntLiteral(3)
			)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseDerefOp() throws ParseException, UnsupportedEncodingException {
		final String code = "this.-->";
		final Dereference goal =
				Dereference(
					Identifier("this"),											
					Identifier("-->")
				);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Dereference );
		assertTrue( goal.equivalent(e) );
	}
	@Test
	public void parseDerefOpCall() throws ParseException, UnsupportedEncodingException {
		final String code = "this.-->(x)";
		final Application goal = Application(
				Dereference(
					Identifier("this"),											
					Identifier("-->")
				),
				ArgumentExpr(Identifier("x"))
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Expr e = pp.SimpleExpr1();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                      SimpleExpr2                       **
	 ************************************************************/
	@Test
	public void parseSingleBlockAsApplication() throws ParseException, UnsupportedEncodingException {
		final String code = "foo{}";
		final Application goal =
			Application(
				Identifier("foo"),
				BlockExpr()
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr2();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseMultipleBlocksAsApplication() throws ParseException, UnsupportedEncodingException {
		final String code = "foo{}{}{}";
		final Application goal =
			Application(
				Application(
					Application(
						Identifier("foo"),
						BlockExpr()
					),
					BlockExpr()
				),
				BlockExpr()
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr2();
		assertTrue(e instanceof Application );
		assertTrue( goal.equivalent(e) );
	}

	/************************************************************
	 **                      SimpleExpr                        **
	 ************************************************************/
	@Test
	public void parseEmptyBlock() throws ParseException, UnsupportedEncodingException {
		final String code = "{}";
		final BlockExpr goal = BlockExpr();
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr();
		assertTrue(e instanceof BlockExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseSingleExprBlock() throws ParseException, UnsupportedEncodingException {
		final String code = "{1+1}";
		final BlockExpr goal = 
			BlockExpr(
				InfixOperator(
					IntLiteral(1),
					Identifier("+"),
					IntLiteral(1)
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr();
		assertTrue(e instanceof BlockExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseMultipleStmtsBlock() throws ParseException, UnsupportedEncodingException {
		final String code = "{1+1;2+2}";
		final BlockExpr goal = 
			BlockExpr(
				InfixOperator(
					IntLiteral(1),
					Identifier("+"),
					IntLiteral(1)
				),
				InfixOperator(
					IntLiteral(2),
					Identifier("+"),
					IntLiteral(2)
				)					
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr();
		assertTrue(e instanceof BlockExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseNew() throws ParseException, UnsupportedEncodingException {
		final String code = "new foo;";
		final NewInstance goal = 
			NewInstance(
				StateRef(Identifier("foo"))
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.SimpleExpr();
		assertTrue(e instanceof NewInstance );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                      InfixExpr                        **
	 ************************************************************/
	@Test
	public void parseCast() throws ParseException, UnsupportedEncodingException {
		final String code = "x as Foo";
		final Cast goal = 
			Cast(
				Identifier("x"), 
				NominalObjectType(
					QualifiedIdentifier("Foo")
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.InfixExpr();
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                      UnaryExp                          **
	 ************************************************************/
	@Test
	public void parseUnaryMinus() throws ParseException, UnsupportedEncodingException {
		final String code = "-x";
		final UnaryOperatorExpr goal = UnaryOperator(Identifier("-"), Identifier("x"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UnaryExpr();
		assertTrue(e instanceof UnaryOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}
	
	@Test
	public void parseUnaryPlus() throws ParseException, UnsupportedEncodingException {
		final String code = "+x";
		final UnaryOperatorExpr goal = UnaryOperator(Identifier("+"), Identifier("x"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UnaryExpr();
		assertTrue(e instanceof UnaryOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}
	
	@Test
	public void parseUnaryNot() throws ParseException, UnsupportedEncodingException {
		final String code = "!x";
		final UnaryOperatorExpr goal = UnaryOperator(Identifier("!"), Identifier("x"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UnaryExpr();
		assertTrue(e instanceof UnaryOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}

	@Test
	public void parseUnaryTilde() throws ParseException, UnsupportedEncodingException {
		final String code = "~x";
		final UnaryOperatorExpr goal = UnaryOperator(Identifier("~"), Identifier("x"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UnaryExpr();
		assertTrue(e instanceof UnaryOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}

	/************************************************************
	 **            MultiplicativeExpr                     **
	 ************************************************************/
	@Test
	public void parseMultiply() throws ParseException, UnsupportedEncodingException {
		final String code = "x * y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("*"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.MultiplicativeExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}
	
	@Test
	public void parseDivide() throws ParseException, UnsupportedEncodingException {
		final String code = "x / y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("/"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.MultiplicativeExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}
	
	@Test
	public void parseModulo() throws ParseException, UnsupportedEncodingException {
		final String code = "x % y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("%"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.MultiplicativeExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}	
	
	/************************************************************
	 **                AdditiveExpr                      **
	 ************************************************************/
	@Test
	public void parseAdd() throws ParseException, UnsupportedEncodingException {
		final String code = "x + y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("+"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.AdditiveExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}	

	@Test
	public void parseSubstract() throws ParseException, UnsupportedEncodingException {
		final String code = "x - y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("-"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.AdditiveExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}
	
	/************************************************************
	 **                   ShiftExpr                      **
	 ************************************************************/
	@Test
	public void parseLeftShift() throws ParseException, UnsupportedEncodingException {
		final String code = "x << y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("<<"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ShiftExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}
	
	@Test
	public void parseRightShift() throws ParseException, UnsupportedEncodingException {
		final String code = "x >> y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier(">>"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ShiftExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );		
	}
	
	@Test
	public void parseRightRightShift() throws ParseException, UnsupportedEncodingException {
		final String code = "x >>> y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier(">>>"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ShiftExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}
	
	/************************************************************
	 **                RelationalExpr                    **
	 ************************************************************/
	@Test
	public void parseSmaller() throws ParseException, UnsupportedEncodingException {
		final String code = "x < y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("<"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.RelationalExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}
	
	@Test
	public void parseSmallerEquals() throws ParseException, UnsupportedEncodingException {
		final String code = "x <= y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("<="), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.RelationalExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}

	@Test
	public void parseBigger() throws ParseException, UnsupportedEncodingException {
		final String code = "x > y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier(">"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.RelationalExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseBiggerEquals() throws ParseException, UnsupportedEncodingException {
		final String code = "x >= y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier(">="), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.RelationalExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                EqualityExpr                     **
	 ************************************************************/
	@Test
	public void parseEqualsEquals() throws ParseException, UnsupportedEncodingException {
		final String code = "x == y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("=="), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.EqualityExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}
	
	@Test
	public void parseNotEquals() throws ParseException, UnsupportedEncodingException {
		final String code = "x != y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("!="), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.EqualityExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                AndExpr                     **
	 ************************************************************/
	@Test
	public void parseAnd() throws ParseException, UnsupportedEncodingException {
		final String code = "x & y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("&"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.AndExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                ExclusiveOrxpression                    **
	 ************************************************************/
	@Test
	public void parseExclusiveOr() throws ParseException, UnsupportedEncodingException {
		final String code = "x ^ y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("^"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ExclusiveOrExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                InclusiveOrExpr                    **
	 ************************************************************/
	@Test
	public void parseInclusiveOr() throws ParseException, UnsupportedEncodingException {
		final String code = "x | y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("|"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.InclusiveOrExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                 ConditionalAndExpr               **
	 ************************************************************/
	@Test
	public void parseConditionalAnd() throws ParseException, UnsupportedEncodingException {
		final String code = "x && y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("&&"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ConditionalAndExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );	
	}
	
	/************************************************************
	 **                 ConditionalOrExpr               **
	 ************************************************************/
	@Test
	public void parseConditionalOr() throws ParseException, UnsupportedEncodingException {
		final String code = "x || y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("||"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.ConditionalOrExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
//	/************************************************************
//	 **                 ConditionalExpr               **
//	 ************************************************************/
//	@Test
//	public void parseConditional() throws ParseException, UnsupportedEncodingException {
//		final String code = "x?y:z";
//		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
//		final Expr e = pp.ConditionalExpr();
//		assertTrue(e instanceof MethodCall );;
//	}
	
	/************************************************************
	 **                 UserOperatorExpr               **
	 ************************************************************/
	@Test
	public void parseUserOperatorInfix() throws ParseException, UnsupportedEncodingException {
		final String code = "x --> y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("-->"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UserInfixOperatorExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                 MethodInfixExpr               **
	 ************************************************************/
	@Test
	public void parseMethodCallInfix() throws ParseException, UnsupportedEncodingException {
		final String code = "x XOR y";
		final InfixOperatorExpr goal = InfixOperator(Identifier("x"), Identifier("XOR"), Identifier("y"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UserInfixOperatorExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                 Precendence test               **
	 ************************************************************/
	@Test
	public void operatorPrecedence() throws ParseException, UnsupportedEncodingException {
		final String code = "x --> y && z || w == u XOR v";
		final InfixOperatorExpr andGoal = InfixOperator(Identifier("y"), Identifier("&&"), Identifier("z"));
		final InfixOperatorExpr eqeqGoal = InfixOperator(Identifier("w"), Identifier("=="), Identifier("u"));
		final InfixOperatorExpr orGoal = InfixOperator(andGoal, Identifier("||"), eqeqGoal);
		final InfixOperatorExpr impGoal = InfixOperator(Identifier("x"), Identifier("-->"), orGoal);
		final InfixOperatorExpr goal = InfixOperator(impGoal, Identifier("XOR"), Identifier("v"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.UserInfixOperatorExpr();
		assertTrue(e instanceof InfixOperatorExpr );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                        Exp1                            **
	 ************************************************************/
	@Test
	public void parseAssignmentVar() throws ParseException, UnsupportedEncodingException {
		final String code = "x = 1";
		final Assignment goal = Assignment(Expr.EMPTY, Identifier("x"), IntLiteral(1));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof Assignment );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseAssignmentField() throws ParseException, UnsupportedEncodingException {
		final String code = "this.x = 1";
		final Assignment goal = Assignment(Identifier("this"), Identifier("x"), IntLiteral(1));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof Assignment );
		assertTrue( goal.equivalent(e) );			
	}

	@Test
	public void parseStateChange() throws ParseException, UnsupportedEncodingException {
		final String code = "x <- Foo;";
		final StateChange goal = StateChange(Identifier("x"), StateRef(Identifier("Foo")));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof StateChange );
		assertTrue( goal.equivalent(e) );		
		
	}
	
	@Test
	public void parseReplace() throws ParseException, UnsupportedEncodingException {
		final String code = "x <<- Foo;";
		final Replace goal = Replace(Identifier("x"), StateRef(Identifier("Foo")));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof Replace );
		assertTrue( goal.equivalent(e) );
		
	}
	
	@Test
	public void parseMatch() throws ParseException, UnsupportedEncodingException {
		final String code = "match (x) { case Foo{} default{} }";
		final Match goal = 
			Match(
				Identifier("x"),
				PatternCase(QualifiedIdentifier("Foo"), BlockExpr()),
				DefaultCase(BlockExpr())
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof Match );
		assertTrue( goal.equivalent(e) );		
		
	}

	@Test
	public void parseAtomicBlock() throws ParseException, UnsupportedEncodingException {
		final String code = "atomic<a>{}";
		final AtomicBlock goal = AtomicBlock(BlockExpr(), Identifier("a"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof AtomicBlock );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseSplitBlock() throws ParseException, UnsupportedEncodingException {
		final String code = "split<a>{}";
		final SplitBlock goal = SplitBlock(BlockExpr(), Identifier("a"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof SplitBlock );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseUnpackInnerGroups() throws ParseException, UnsupportedEncodingException {
		final String code = "unpackInnerGroups{}";
		final UnpackInnerGroups goal = UnpackInnerGroups(BlockExpr());
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr1();
		assertTrue(e instanceof UnpackInnerGroups );
		assertTrue( goal.equivalent(e) );
	}
	
	
	/************************************************************
	 **                      LocalVarDecl                       **
	 ************************************************************/	
	@Test
	public void parseLocalVarDel() throws ParseException, UnsupportedEncodingException {
		final String code = "var x = 1";
		final VarDecl goal = VarDecl(Var(), Type.EMPTY, Identifier("x"), IntLiteral(1));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Stmt e = pp.LocalVarDecl();
		assertTrue(e instanceof Stmt );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                       Expr                             **
	 ************************************************************/
	@Test
	public void parseExprLambda() throws ParseException, UnsupportedEncodingException {
		final String code = "fn () => {}";
		@SuppressWarnings("unchecked")
		final Lambda goal = 
			Lambda(
				Collections.EMPTY_LIST, 
				Collections.EMPTY_LIST, 
				Collections.EMPTY_LIST,
				Type.EMPTY,
				BlockExpr()
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Expr();
		assertTrue(e instanceof Lambda );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                       Function                         **
	 ************************************************************/
	@Test
	public void parseLambda() throws ParseException, UnsupportedEncodingException {
		final String code = "fn () => {}";
		@SuppressWarnings("unchecked")
		final Lambda goal = 
			Lambda(
				Collections.EMPTY_LIST, 
				Collections.EMPTY_LIST, 
				Collections.EMPTY_LIST,
				Type.EMPTY,
				BlockExpr()
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Function();
		assertTrue(e instanceof Lambda );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseLambdaIdentity() throws ParseException, UnsupportedEncodingException {
		final String code = "fn (x) => x";
		@SuppressWarnings("unchecked")
		final Lambda goal = 
			Lambda(
				Collections.EMPTY_LIST, 
				Arrays.asList(
					Arg(EmptyArgSpec.EMPTY, Identifier("x"))
				),
				Collections.EMPTY_LIST, 
				Type.EMPTY,
				Identifier("x")
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Function();
		assertTrue(e instanceof Lambda );
		assertTrue( goal.equivalent(e) );
	}
	
	@Test
	public void parseLambdaEnvironment() throws ParseException, UnsupportedEncodingException {
		final String code = "fn (x)[Integer y, Integer z] : Integer => x + y + z";
		final Lambda goal = 
			Lambda(
				Collections.EMPTY_LIST, 
				Arrays.asList(
					Arg(EmptyArgSpec.EMPTY, Identifier("x"))
				),
				Arrays.asList(
					Arg(ArgSpec(NominalObjectType(QualifiedIdentifier("Integer"))), Identifier("y")),
					Arg(ArgSpec(NominalObjectType(QualifiedIdentifier("Integer"))), Identifier("z"))
				), 
				NominalObjectType(QualifiedIdentifier("Integer")),
				InfixOperator(
					InfixOperator(
						Identifier("x"), 
						Identifier("+"), 
						Identifier("y")
					),
					Identifier("+"),
					Identifier("z")
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Function();
		assertTrue(e instanceof Lambda );
		assertTrue( goal.equivalent(e) );
	}

	@Test
	public void parseLambdaMetaArgsEnvironment() throws ParseException, UnsupportedEncodingException {
		final String code = "fn <group protected A>(x)[shared<A> Integer y, Integer z] => x + y + z";
		final Lambda goal = 
			Lambda(
				Arrays.asList(
					GroupArg(Protected(), Identifier("A"))
				), 
				Arrays.asList(
					Arg(EmptyArgSpec.EMPTY, Identifier("x"))
				),
				Arrays.asList(
					Arg(ArgSpec(NominalObjectType(Shared(Identifier("A")), QualifiedIdentifier("Integer"))), Identifier("y")),
					Arg(ArgSpec(NominalObjectType(QualifiedIdentifier("Integer"))), Identifier("z"))
				), 
				Type.EMPTY,
				InfixOperator(
					InfixOperator(
						Identifier("x"), 
						Identifier("+"), 
						Identifier("y")
					),
					Identifier("+"),
					Identifier("z")
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Expr e = pp.Function();
		assertTrue(e instanceof Lambda );
		assertTrue( goal.equivalent(e) );
	}
	
	/************************************************************
	 **                       CaseList                         **
	 ************************************************************/
	@Test
	public void parseSingleCase() throws ParseException, UnsupportedEncodingException {
		final String code = "case Foo{}";
		final Case goal = PatternCase(QualifiedIdentifier("Foo"), BlockExpr());
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final List<Case> e = pp.CaseList();
		assertTrue(e.get(0) instanceof PatternCase );
		assertTrue(e.get(0).equivalent(goal));
	}
	
	@Test
	public void parseMultipleCases() throws ParseException, UnsupportedEncodingException {
		final String code = "case Foo{} case Bar{} case Baz{}";
		final List<Case> goal = 
			Arrays.asList(
				PatternCase(QualifiedIdentifier("Foo"), BlockExpr()),
				PatternCase(QualifiedIdentifier("Bar"), BlockExpr()),
				PatternCase(QualifiedIdentifier("Baz"), BlockExpr())
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		List<Case> e = pp.CaseList();
		assertTrue(e.get(0) instanceof Case );
		assertTrue(e.get(1) instanceof Case );
		assertTrue(e.get(2) instanceof Case );
		assertTrue(e.get(0).equivalent(goal.get(0)));
		assertTrue(e.get(1).equivalent(goal.get(1)));
		assertTrue(e.get(2).equivalent(goal.get(2)));
	}

	@Test
	public void parseDefaultCase() throws ParseException, UnsupportedEncodingException {
		final String code = "default {}";
		final Case goal = DefaultCase(BlockExpr());
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final List<Case> e = pp.CaseList();
		assertTrue(e.get(0) instanceof DefaultCase );
		assertTrue(e.get(0).equivalent(goal));
	}
	
	/************************************************************
	 **                      StateOP                           **
	 ************************************************************/
	@Test
	public void parseStateRemoveOp() throws ParseException, UnsupportedEncodingException {
		final String code = "remove foo;";
		final StateOpRemove goal = StateOpRemove(Identifier("foo"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StateOp e = pp.StateOp();
		assertTrue(e instanceof StateOpRemove );
		assertTrue(e.equivalent(goal));
	}
	
	
	@Test
	public void parseStateRenameOp() throws ParseException, UnsupportedEncodingException {
		final String code = "rename foo as bar;";
		final StateOpRename goal = StateOpRename(Identifier("foo"), Identifier("bar"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StateOp e = pp.StateOp();
		assertTrue(e instanceof StateOpRename );
		assertTrue(e.equivalent(goal));
	}
	
	/************************************************************
	 **                      StatePrim                         **
	 ************************************************************/
	@Test
	public void parseFreeze() throws ParseException, UnsupportedEncodingException {
		final String code = "freeze x;";
		final Freeze goal = Freeze(Identifier("x"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StatePrim e = pp.StatePrim();
		assertTrue(e instanceof Freeze );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseStatePrimState() throws ParseException, UnsupportedEncodingException {
		final String code = "Foo";
		final StateRef goal = StateRef(Identifier("Foo"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StatePrim e = pp.StatePrim();
		assertTrue(e instanceof StatePrim );
		assertTrue(e.equivalent(goal));
	}	

	@Test
	public void parseStatePrimStateOps() throws ParseException, UnsupportedEncodingException {
		final String code = "Foo{remove Bar;rename Baz as Fogo;}";
		final StateRef goal = 
			StateRef(
				Identifier("Foo"),
				StateOpRemove(Identifier("Bar")),
				StateOpRename(Identifier("Baz"), Identifier("Fogo"))
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StatePrim e = pp.StatePrim();
		assertTrue(e instanceof StatePrim );
		assertTrue(e.equivalent(goal));
	}	
	
	/************************************************************
	 **                      State                             **
	 ************************************************************/	
	@Test
	public void parseWith() throws ParseException, UnsupportedEncodingException {
		final String code = "x with y";
		final With goal = With(StateRef(Identifier("x")), StateRef(Identifier("y")));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StateExpr e = pp.State();
		assertTrue(e instanceof With );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseWithWith() throws ParseException, UnsupportedEncodingException {
		final String code = "x with y with z";
		final With goal = 
		    With(				
				With(
					StateRef(Identifier("x")),
					StateRef(Identifier("y"))
			    ),
			    StateRef(Identifier("z")) 
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final StateExpr e = pp.State();
		assertTrue(e instanceof With );
		assertTrue(e.equivalent(goal));
	}
	
	/************************************************************
	 **                      GroupDecl                         **
	 ************************************************************/
	@Test
	public void parseGroupDecl() throws ParseException, UnsupportedEncodingException {
		final String code = "group foo = new group;";
		final Decl goal = GroupDecl(Identifier("foo"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.GroupDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof GroupDecl );
		assertTrue(e.equivalent(goal));
	}
	
	/************************************************************
	 **                      FieldDecl                         **
	 ************************************************************/
	@Test
	public void parseAbstractFieldSpecifier() throws ParseException, UnsupportedEncodingException {
		final String code = "val f;";
		final FieldDecl goal = AbstractFieldDecl(Val(), Type.EMPTY, Identifier("f"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractFieldDecl );
		assertTrue(e.equivalent(goal));
	}
	
	
	@Test
	public void parseAbstractFieldSpecifierType() throws ParseException, UnsupportedEncodingException {
		final String code = "val Foo f;";
		final FieldDecl goal = AbstractFieldDecl(Val(), NominalObjectType(QualifiedIdentifier("Foo")), Identifier("f"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractFieldDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractFieldType() throws ParseException, UnsupportedEncodingException {
		final String code = "Foo f;";
		final FieldDecl goal = AbstractFieldDecl(Specifier.EMPTY, NominalObjectType(QualifiedIdentifier("Foo")), Identifier("f"));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractFieldDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseConcretetFieldSpecifierType() throws ParseException, UnsupportedEncodingException {
		final String code = "val Foo f=0;";
		final FieldDecl goal = ConcreteFieldDecl(Val(), NominalObjectType(QualifiedIdentifier("Foo")), Identifier("f"), IntLiteral(0));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof ConcreteFieldDecl );
		assertTrue(e.equivalent(goal));
	}

	@Test
	public void parseConcretetFieldSpecifier() throws ParseException, UnsupportedEncodingException {
		final String code = "val f=0;";
		final FieldDecl goal = ConcreteFieldDecl(Val(), Type.EMPTY, Identifier("f"), IntLiteral(0));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof ConcreteFieldDecl );
		assertTrue(e.equivalent(goal));
	}

	@Test
	public void parseConcretetFieldType() throws ParseException, UnsupportedEncodingException {
		final String code = "Foo f=0;";
		final FieldDecl goal = ConcreteFieldDecl(Specifier.EMPTY, NominalObjectType(QualifiedIdentifier("Foo")), Identifier("f"), IntLiteral(0));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof ConcreteFieldDecl );
		assertTrue(e.equivalent(goal));
	}

	@Test
	public void parseConcretetField() throws ParseException, UnsupportedEncodingException {
		final String code = "f=0;";
		final FieldDecl goal = ConcreteFieldDecl(Specifier.EMPTY, Type.EMPTY, Identifier("f"), IntLiteral(0));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.FieldDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof ConcreteFieldDecl );
		assertTrue(e.equivalent(goal));
	}
	
	/************************************************************
	 **                      MethodDecl                        **
	 ************************************************************/
	@Test
	public void parseAbstractMethodFullDynamic() throws ParseException, UnsupportedEncodingException {
		final String code = "method foo();";
		final MethodDecl goal = 
			AbstractMethodDecl(
					Type.EMPTY, 
					Identifier("foo"), 
					StaticArg.EMPTY, 
					Arg.EMPTY, 
					Arg.EMPTY
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractMethodReturnType() throws ParseException, UnsupportedEncodingException {
		final String code = "method immutable Boolean foo();";
		final MethodDecl goal = 
			AbstractMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arg.EMPTY
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractMethodThis() throws ParseException, UnsupportedEncodingException {
		final String code = "method immutable Boolean foo() [immutable Boolean >> immutable Boolean this];";
		final MethodDecl goal = 
			AbstractMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arrays.asList(
						Arg(
								ArgSpec(
									NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")),
									NominalObjectType(Immutable(), QualifiedIdentifier("Boolean"))
								), 
								Identifier("this"))
						)
				
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractMethodReturnTypeArgs() throws ParseException, UnsupportedEncodingException {
		final String code = "method immutable Boolean foo(immutable Boolean>>immutable Boolean x);";
		final MethodDecl goal = 
			AbstractMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arrays.asList(
					Arg(
						ArgSpec(
							NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")),
							NominalObjectType(Immutable(), QualifiedIdentifier("Boolean"))
						), 
						Identifier("x"))
				), 
				Arg.EMPTY
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue( code.equals(e.toString()));
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractMethodEnvironment() throws ParseException, UnsupportedEncodingException {
		final String code = "method foo()[unique Bar >> none Baz x];";
		final MethodDecl goal = 
			AbstractMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arrays.asList(
					Arg(
						ArgSpec(
							NominalObjectType(Unique(), QualifiedIdentifier("Bar")),
							NominalObjectType(None(), QualifiedIdentifier("Baz"))
						), 
						Identifier("x")
					)
				)
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractMethodEnvironmentThis() throws ParseException, UnsupportedEncodingException {
		final String code = "method foo()[unique Bar >> none Baz this];";
		final MethodDecl goal = 
			AbstractMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arrays.asList(
					Arg(
						ArgSpec(
							NominalObjectType(Unique(), QualifiedIdentifier("Bar")),
							NominalObjectType(None(), QualifiedIdentifier("Baz"))
						), 
						Identifier("this")
					)
				)
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseConcreteMethodFullDynamic() throws ParseException, UnsupportedEncodingException {
		final String code = "method foo() {1}";
		final MethodDecl goal = 
			ConcreteMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arg.EMPTY,
				BlockExpr(
					IntLiteral(1)
				)
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof ConcreteMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseConcreteMethodReturnTypeArgs() throws ParseException, UnsupportedEncodingException {
		final String code = "method immutable Boolean foo<group protected A>(immutable Boolean>>immutable Boolean x)[Boolean global] {1+1}";
		final MethodDecl goal = 
			ConcreteMethodDecl(
				NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")), 
				Identifier("foo"), 
				Arrays.asList(
					GroupArg(Protected(), Identifier("A"))
				), 
				Arrays.asList(
					Arg(
						ArgSpec(
							NominalObjectType(Immutable(), QualifiedIdentifier("Boolean")),
							NominalObjectType(Immutable(), QualifiedIdentifier("Boolean"))
						), 
						Identifier("x")
					)
				), 
				Arrays.asList(
					Arg(
						ArgSpec(
							NominalObjectType(QualifiedIdentifier("Boolean"))
						), 
						Identifier("global")
					)
				),
				BlockExpr(
					InfixOperator(
						IntLiteral(1),
						Identifier("+"),
						IntLiteral(1)
					)
				)
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
		assertTrue(e instanceof ConcreteMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseWhileLoop() throws ParseException, UnsupportedEncodingException {
		final StringBuilder codeBuilder = new StringBuilder();
		codeBuilder.append("method test(isEmpty) { \n");
			codeBuilder.append("match(isEmpty) { \n");
				codeBuilder.append("case True {\n");
					codeBuilder.append("var switch = true;\n");
					codeBuilder.append("while {switch} { switch = false; };\n");
				codeBuilder.append("}\n");
				codeBuilder.append("case False {\n");
					codeBuilder.append("5\n");
				codeBuilder.append("}\n");
			codeBuilder.append("};\n");
		codeBuilder.append("}");
		String code = codeBuilder.toString();
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		Decl e = pp.MethodDecl(new ArrayList<Annotation>(), new ArrayList<Modifier>());
	}
	
	/************************************************************
	 **                        Decl                            **
	 ************************************************************/
	@Test
	public void parseModifierlDecl() throws ParseException, UnsupportedEncodingException {
		final String code = "requires override method foo();";
		final MethodDecl goal = 
			AbstractMethodDecl(
				Arrays.asList(
					REQUIRES(),
					OVERRIDE()
				),
				Type.EMPTY, 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arg.EMPTY
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAnnotationModifierlDecl() throws ParseException, UnsupportedEncodingException {
		final String code = "@sequential @noop requires override method foo();";
		final MethodDecl goal = 
			AbstractMethodDecl(
				Arrays.asList(
				    Annotation("sequential"),
					Annotation("noop")
				),
				Arrays.asList(
					REQUIRES(),
					OVERRIDE()
				),
				Type.EMPTY, 
				Identifier("foo"), 
				StaticArg.EMPTY, 
				Arg.EMPTY, 
				Arg.EMPTY
			); 
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractMethodDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractStateValEmpty() throws ParseException, UnsupportedEncodingException {
		final String code = "stateval Foo;";
		final StateValDecl goal = AbstractStateValDecl(Identifier("Foo"), StaticArg.EMPTY);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractStateValDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseImmutableAbstractStateValEmpty() throws ParseException, UnsupportedEncodingException {
		final String code = "immutable stateval Foo;";
		final StateValDecl goal = AbstractStateValDecl(Identifier("Foo"), IMMUTABLE(), StaticArg.EMPTY);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractStateValDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseConcreteStateVal() throws ParseException, UnsupportedEncodingException {
		final String code = "stateval Foo = Bar";
		final StateValDecl goal = ConcreteStateValDecl(Identifier("Foo"), StaticArg.EMPTY, StateRef(Identifier("Bar")));
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof ConcreteStateValDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractStateValEmptyMetaArgs() throws ParseException, UnsupportedEncodingException {
		final String code = "stateval Foo<group A, type T>;";
		final StateValDecl goal = 
				AbstractStateValDecl(
					Identifier("Foo"),
					Arrays.asList(
						GroupArg(GroupPermission.EMPTY, Identifier("A")),
						TypeArg(Identifier("T"), QualifiedIdentifier.EMPTY)
					)
				);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractStateValDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractStateEmpty() throws ParseException, UnsupportedEncodingException {
		final String code = "state Foo;";
		final Decl goal = 
			AbstractStateDecl(
				Identifier("Foo"), 
				StaticArg.EMPTY, 
				QualifiedIdentifier.EMPTY, 
				Collections.EMPTY_LIST
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractStateDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractStateCaseOf() throws ParseException, UnsupportedEncodingException {
		final String code = "state Foo case of Bar.Baz;";
		final Decl goal = 
			AbstractStateDecl(
				Identifier("Foo"), 
				StaticArg.EMPTY, 
				QualifiedIdentifier("Bar", "Baz"), 
				Collections.EMPTY_LIST
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractStateDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseAbstractStateCaseOfMetaArgs() throws ParseException, UnsupportedEncodingException {
		final String code = "state Foo<group A, group B> case of Bar.Baz<A,B>;";
		final Decl goal = 
			AbstractStateDecl(
				Identifier("Foo"), 
				Arrays.asList(
					GroupArg(GroupPermission.EMPTY, Identifier("A")),
					GroupArg(GroupPermission.EMPTY, Identifier("B"))
				), 
				QualifiedIdentifier("Bar", "Baz"), 
				Arrays.asList(
					(Expr)Identifier("A"),
					(Expr)Identifier("B")
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof AbstractStateDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseConcreteStateWith() throws ParseException, UnsupportedEncodingException {
		final String code = "state Foo = Bar with Baz;";
		final Decl goal = 
			ConcreteStateDecl(
				Identifier("Foo"), 
				StaticArg.EMPTY, 
				QualifiedIdentifier.EMPTY, 
				Collections.EMPTY_LIST,
				With(
					StateRef(Identifier("Bar")),
					StateRef(Identifier("Baz"))
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof ConcreteStateDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseImmutableConcreteStateWith() throws ParseException, UnsupportedEncodingException {
		final String code = "immutable state Foo = Bar with Baz;";
		final Decl goal = 
			ConcreteStateDecl(
				IMMUTABLE(),
				Identifier("Foo"), 
				StaticArg.EMPTY, 
				QualifiedIdentifier.EMPTY, 
				Collections.EMPTY_LIST,
				With(
					StateRef(Identifier("Bar")),
					StateRef(Identifier("Baz"))
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof ConcreteStateDecl );
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parseConcreteStateDecls() throws ParseException, UnsupportedEncodingException {
		final String code = "state Foo = { method foo(); val x;}";
		final Decl goal = 
			ConcreteStateDecl(
				Identifier("Foo"), 
				StaticArg.EMPTY, 
				QualifiedIdentifier.EMPTY, 
				Collections.EMPTY_LIST,
				DeclList(
					AbstractMethodDecl(
						Type.EMPTY, 
						Identifier("foo"), 
						StaticArg.EMPTY, 
						Arg.EMPTY, 
						Arg.EMPTY
					),
					 AbstractFieldDecl(Val(), Type.EMPTY, Identifier("x"))
				)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final Decl e = pp.Decl();
		assertTrue(e instanceof ConcreteStateDecl );
		assertTrue(e.equivalent(goal));
	}

	/************************************************************
	 **                      Imports                           **
	 ************************************************************/
	@Test
	public void parseSingleImport() throws ParseException, UnsupportedEncodingException {
		final String code = "import foo.bar.baz;";
		final Import goal = Import(QualifiedIdentifier("foo","bar","baz"), false);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final List<Import> e = pp.Imports();
		assertTrue(e.get(0) instanceof Import);
		assertTrue(e.get(0).equivalent(goal));
	}
	
	@Test
	public void parseSingleImportStar() throws ParseException, UnsupportedEncodingException {
		final String code = "import foo.bar.baz.*;";
		final Import goal = Import(QualifiedIdentifier("foo","bar","baz"), true);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final List<Import> e = pp.Imports();
		assertTrue(e.get(0) instanceof Import);
		assertTrue(e.get(0).equivalent(goal));
	}
	
	@Test
	public void parseMultipleImport() throws ParseException, UnsupportedEncodingException {
		final String code = "import foo.bar.baz; import bob.karl;";
		final List<Import> goal =
			Arrays.asList(
				Import(QualifiedIdentifier("foo","bar","baz"), false),
				Import(QualifiedIdentifier("bob","karl"), false)
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final List<Import> e = pp.Imports();
		assertTrue(e.get(0) instanceof Import);
		assertTrue(e.get(1) instanceof Import);
		assertTrue(e.get(0).equivalent(goal.get(0)));
		assertTrue(e.get(1).equivalent(goal.get(1)));
	}
	
	/************************************************************
	 **                      package                           **
	 ************************************************************/
	@Test
	public void parseEmptyPackage() throws ParseException, UnsupportedEncodingException {
		final String code = "package foo.bar.baz;";
		final CompilationUnit goal = 
			CompilationUnit( 
				Package("foo","bar","baz"),
				Collections.EMPTY_LIST,
				Collections.EMPTY_LIST
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final CompilationUnit e = pp.CompilationUnit();
		assertTrue(e instanceof CompilationUnit);
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parsePackageImports() throws ParseException, UnsupportedEncodingException {
		final String code = "package foo.bar.baz; import foo.bar;";
		final CompilationUnit goal = 
			CompilationUnit( 
				Package("foo","bar","baz"),
				Arrays.asList(
					Import(QualifiedIdentifier("foo","bar"), false)
				),
				Collections.EMPTY_LIST
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final CompilationUnit e = pp.CompilationUnit();
		assertTrue(e instanceof CompilationUnit);
		assertTrue(e.equivalent(goal));
	}
	
	@Test
	public void parsePackageImportsDelcs() throws ParseException, UnsupportedEncodingException {
		final String code = "package foo.bar.baz; import foo.bar; val x = 1; method foo(){} state Karl { val bob = \"ML\"; }";
		final CompilationUnit goal = 
			CompilationUnit( 
				Package("foo","bar","baz"),
				Arrays.asList(
					Import(QualifiedIdentifier("foo","bar"), false)
				),
				Arrays.asList(
					ConcreteFieldDecl(
						Val(), 
						Type.EMPTY, 
						Identifier("x"), 
						IntLiteral(1)
					),
					ConcreteMethodDecl(
						Type.EMPTY, 
						Identifier("foo"), 
						StaticArg.EMPTY, 
						Arg.EMPTY, 
						Arg.EMPTY,
						BlockExpr()
					),
					ConcreteStateDecl(
						Identifier("Karl"), 
						StaticArg.EMPTY, 
						QualifiedIdentifier.EMPTY, 
						Collections.EMPTY_LIST,
						DeclList(
							ConcreteFieldDecl(
								Val(), 
								Type.EMPTY, 
								Identifier("bob"), 
								StringLiteral("ML")
							)
						)
					)
				)				
			);
		final PlaidCoreParser pp = new PlaidCoreParser(new ByteArrayInputStream(code.getBytes("UTF-8")));
		final CompilationUnit e = pp.CompilationUnit();
		assertTrue(e instanceof CompilationUnit);
		assertTrue(e.equivalent(goal));
	}
}

