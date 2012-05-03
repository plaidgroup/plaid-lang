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
import plaid.runtime.PlaidState;

public class OptionTest extends BaseTest {
	@Test
	public void freezeSomeTest() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject some = cl.lookup("plaid.lang.Some", cl.unit());
		assertTrue( some instanceof PlaidState );
		PlaidState someState = (PlaidState) some;
		PlaidState initState = plaid.runtime.Util.newState();
		PlaidMethod fieldInit = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr204$plaid) {
				return plaid.runtime.Util.integer(5);	
			}	
		}
		);
		initState.addMember(plaid.runtime.Util.anonymousMemberDef("value", false, false),fieldInit);
		PlaidObject someObject = someState.initState(initState).instantiate();
		PlaidState freezeSome = someObject.freeze();
		PlaidObject someUnfrozen = freezeSome.instantiate();
		assertTrue ( someUnfrozen != null);
	}
}
