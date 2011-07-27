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

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test(expected=ParseException.class)
	public void noPackage() throws ParseException {
		String program = "val foo = 5;";
		ParserCore.parse(new StringBufferInputStream(program));
	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
