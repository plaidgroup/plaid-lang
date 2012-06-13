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
import plaid.runtime.Util;

public class IntTest extends BaseTest {
	@Test
	public void CreateIntTest() {
		PlaidObject s = Util.integer(666);
		assertTrue ( s != null );
	}

	@Test
	public void TestPlus() {
		PlaidObject v1 = Util.integer(1);
		PlaidObject v2 = Util.integer(2);
		
		PlaidObject plus = Util.lookup("plus$plaid", v1);
		assertTrue( plus != null );
		
		PlaidObject result = Util.call(plus, v2);
		assertTrue( result != null );
		assertTrue( result instanceof PlaidJavaObject );
		
		PlaidJavaObject jresult = (PlaidJavaObject)result;
		assertTrue( jresult.getJavaObject().equals(new Integer(3)));
		
	}
}
