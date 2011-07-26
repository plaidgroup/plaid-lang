/**
 * 
 */
package plaid.parser.test;


import java.io.StringBufferInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import plaid.parser.ParseException;
import plaid.parser.ParserCore;

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
}
