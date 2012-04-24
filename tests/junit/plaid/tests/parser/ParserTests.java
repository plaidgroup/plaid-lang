/**
 * 
 */
package plaid.tests.parser;


import static junit.framework.Assert.assertTrue;
import static plaid.parser.test.astfactory.ASTFactory.CompilationUnit;
import static plaid.parser.test.astfactory.ASTFactory.Val;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.Parser;
import plaid.parser.ast.Annotation;
import plaid.parser.ast.CompilationUnit;
import plaid.parser.ast.ConcreteFieldDecl;
import plaid.parser.ast.Decl;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.Import;
import plaid.parser.ast.IntLiteral;
import plaid.parser.ast.Modifier;
import plaid.parser.ast.QualifiedIdentifier;
import plaid.parser.ast.Type;

/**
 * @author jssunshi
 *
 */
public class ParserTests {
	
	@Test
	public void noPackage() throws ParseException {
		String program = "val foo = 5;";
		Parser parser = new Parser(new StringBufferInputStream(program), 4);
		assertTrue( !parser.hasCompilationUnit() );
	}
	
	@Test
	public void intField() throws ParseException {
		String program = "package plaid; val foo = 5;";
		Parser parser = new Parser(new StringBufferInputStream(program), 4);
		assertTrue( parser.hasCompilationUnit() );
		CompilationUnit parsedCU = parser.getCompilationUnit();
		Decl field = 
			new ConcreteFieldDecl(null, new ArrayList<Annotation>(), new ArrayList<Modifier>(), 
					Val(),
					Type.EMPTY,
					new Identifier(null,"foo"), 
					new IntLiteral(null,5));
		QualifiedIdentifier packageName = 
			new QualifiedIdentifier(null,Collections.singletonList(new Identifier(null, "plaid")));
		CompilationUnit goalCU = 
			CompilationUnit(
					packageName,
					new ArrayList<Import>(), 
					Collections.singletonList(field)
			);
		Assert.assertNotNull("Parsed CU is null", parsedCU);
		boolean match = goalCU.equivalent(parsedCU);
		Assert.assertTrue("Parsed and goal CUs are not equivalent.", match);
	}
}
