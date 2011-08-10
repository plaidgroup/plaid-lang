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

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;

public class ObjectTest extends BaseTest {
	@Test
	public void NewObjectTest() {
		PlaidObject o = Util.newObject();
		assertTrue( o != null );
	}
	
	@Test
	public void ObjectStateChangeTest() {
		PlaidObject o = Util.newObject();
		assertTrue( o != null );
		assertTrue( o.getMembers().containsKey("getFirst") == false );
		
		PlaidObject pc = Util.lookup("plaid.lang.Pair", Util.unit());
		assertTrue( pc != null );
		PlaidState pcs = Util.toPlaidState(pc);
		//PlaidObject po = pcs.instantiate();
		//assertTrue( po != null );
		
		o.changeState(pcs,true);
		assertTrue( o.getMembers().containsKey("first") == true );
	}
}
