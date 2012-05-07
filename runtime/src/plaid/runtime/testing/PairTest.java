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
import plaid.runtime.PlaidException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;
import plaid.runtime.models.map.PlaidMemberDefMap;
import plaid.runtime.utils.Delegate;

public class PairTest extends BaseTest {
	@Test
	public void createPairStateTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject p = cl.lookup("plaid.lang.Pair", cl.unit());
		assertTrue( p != null );
		assertTrue( p instanceof PlaidState );
	}

	@Test
	public void createPairStateFromPackageTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();

		
		PlaidObject p = cl.lookup("plaid.lang.Pair", cl.unit());
		assertTrue( p != null );
		assertTrue( p instanceof PlaidState );
		
		PlaidObject ps = Util.toPlaidState(p);
		assertTrue( ps != null );
		
		// there should be only instance of a state
		assertTrue( ps == cl.lookup("plaid.lang.Pair",	cl.unit()));
		
	}
	
	@Test 
	public void createPairPairTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject p = cl.lookup("plaid.lang.Pair", cl.unit());
		assertTrue( p != null );
		assertTrue( p instanceof PlaidState );
		
		PlaidState ps = Util.toPlaidState(p);
		assertTrue( ps != null );
		
		PlaidState init = Util.newState();
		PlaidMemberDef fst = new PlaidMemberDefMap("fst");
		init.addMember(fst, Util.protoField(new Delegate() {
			
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)
					throws PlaidException {
				return Util.unit();
			}
		})/*, true*/);
		PlaidMemberDef snd = new PlaidMemberDefMap("snd");
		init.addMember(snd, Util.protoField(new Delegate() {
			
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)
					throws PlaidException {
				return Util.unit();
			}
		})/*, true*/);
		PlaidState pairInit = ps.with(init);
		
		PlaidObject pair = pairInit.instantiate();
		assertTrue( pair != null );
		
		PlaidObject getFirst = cl.lookup("first", pair);
		assertTrue(getFirst != null );
		assertTrue( getFirst instanceof PlaidMethod );
		
		PlaidObject v1 = Util.call(getFirst, cl.unit());
		assertTrue( v1 != null );
		assertTrue( v1 == cl.unit());
	}
	
	@Test
	public void TestArrayToPairs() {
		Integer[] values = {1,2,3,4,5};
		PlaidObject pairs = Util.convertArrayToParams(values);
		assertTrue( pairs != null );
		
		Object[] objs = Util.convertParamsToArray(pairs);
		assertTrue( objs != null );
	}
}
