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

public class StateTest extends BaseTest {
	@Test
	public void WithTest() {
		PlaidObject intState = Util.lookup("plaid.lang.Integer", Util.unit());
		assertTrue ( intState != null );
		PlaidObject pairState = Util.lookup("plaid.lang.Pair", Util.unit());
		assertTrue ( pairState != null );
		assertTrue ( pairState instanceof PlaidState );
		PlaidState pairStateState = Util.toPlaidState(pairState);
		
		PlaidState intPlaidState = (PlaidState)intState;
		PlaidObject mixState = intPlaidState.with(pairStateState);
		assertTrue( mixState != null );
		
		PlaidState mixPlaidState = Util.toPlaidState(mixState);
		PlaidObject mixInst = mixPlaidState.instantiate();
		assertTrue ( mixInst != null );
		
		PlaidObject plus = Util.lookup("plus$plaid", mixInst);
		assertTrue ( plus != null );
		
		PlaidObject getFirst = Util.lookup("first", mixInst);
		assertTrue ( getFirst != null );
	}
}
