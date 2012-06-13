/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.runtime.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import plaid.runtime.PlaidClassLoader;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.Util;

public class JavaTest extends BaseTest {
	@Test
	public void packageTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		
		PlaidObject system = cl.lookup("java.lang.System", cl.unit());
		assertTrue( system != null );
		
		PlaidObject out = cl.lookup("out", system);
		assertTrue( out != null );
		
		PlaidObject println = cl.lookup("println", out);
		assertTrue( println != null );
		
		PlaidObject msg = cl.packJavaObject("Hello World");
		assertTrue( msg != null );
		
		PlaidMethod m = Util.toPlaidMethod(println);
		PlaidObject result = m.invoke(msg);
		assertTrue( result != null );
	}
}
