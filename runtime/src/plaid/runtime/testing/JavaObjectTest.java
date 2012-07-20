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

import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;

public class JavaObjectTest extends BaseTest {
	@Test
	public void GetObjectClass() {
		PlaidObject objClass = Util.lookup("java.lang.Object", Util.unit());
		assertTrue( objClass != null );
		assertTrue( objClass instanceof PlaidState );
	
		PlaidState objState = (PlaidState)objClass; 
		PlaidObject obj = objState.instantiate();
		assertTrue( obj != null );
	}
	
	@Test
	public void TestMulitpleParams() {
		PlaidObject ps = Util.string("Hello World");
		PlaidObject substr = Util.lookup("substring", ps);
		
		PlaidObject args = Util.convertArrayToParams(new Integer[] {6, 11} );
		PlaidObject result = Util.call(substr, args);
		Object obj = ((PlaidJavaObject)result).getJavaObject();
		assertTrue( obj instanceof String );
		assertTrue( obj.equals("World") );
	}
}
