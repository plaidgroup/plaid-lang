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
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;

public class UnitTest extends BaseTest {
	@Test
	public void createUnitTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject unit = cl.lookup("plaid.lang.Unit", cl.unit());
		assertTrue( unit != null );
	}
	
	@Test
	public void unitUniqueTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject unit1 = cl.lookup("plaid.lang.Unit", cl.unit());
		assertTrue( unit1 != null );
		PlaidObject unit2 = cl.lookup("plaid.lang.Unit", cl.unit());
		assertTrue( unit2 != null );
		
		assertTrue( unit1 == unit2 );
	}
}
