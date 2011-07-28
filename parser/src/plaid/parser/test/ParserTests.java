/**
 * 
 */
package plaid.parser.test;


import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.ParserCore;
import plaid.parser.ast.*;
import plaid.parser.ast.Specifier.SpecifierKind;

/**
 * @author jssunshi
 *
 */
public class ParserTests {
	
	@Test(expected=ParseException.class)
	public void noPackage() throws ParseException {
		String program = "val foo = 5;";
		ParserCore.parse(new StringBufferInputStream(program));
	}
	
	@Test
	public void intField() throws ParseException {
		String program = "package plaid;\n val foo = 5;";
		CompilationUnit parsedCU = ParserCore.parse(new StringBufferInputStream(program));
		Decl field = 
			new ConcreteFieldDecl(null, new ArrayList<Modifier>(), 
					new Specifier(null,SpecifierKind.VAL),
					Type.EMPTY,
					new Identifier(null,"foo"), 
					new IntLiteral(null,5));
		QualifiedIdentifier packageName = 
			new QualifiedIdentifier(null,Collections.singletonList(new Identifier(null, "plaid")));
		CompilationUnit goalCU = new CompilationUnit(Collections.singletonList(field),
				new ArrayList<Import>(), packageName);
		Assert.assertNotNull("Parsed CU is null", parsedCU);
		boolean match = goalCU.equivalent(parsedCU);
		Assert.assertTrue("Parsed and goal CUs are not equivalent.", match);
	}
}
