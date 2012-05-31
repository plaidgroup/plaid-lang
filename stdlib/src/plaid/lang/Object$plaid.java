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

package plaid.lang;

import java.util.ArrayList;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidScope;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsMethod;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.models.map.PlaidLocalScopeMap;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.Import;

@RepresentsState(name="Object", inPackage = "plaid.lang", toplevel=true, jsonRep="{\"member_type\": \"state\",\"name\": \"Object\",\"members\": []}")
public class Object$plaid {
	public static PlaidScope globalScope = PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang", new ArrayList<Import>());

	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Object")
 	public static final plaid.runtime.PlaidTag Object$Tag$plaid;
 	static {
 		final plaid.runtime.PlaidTag vAr23$plaid;
 		vAr23$plaid = null;
 		Object$Tag$plaid = plaid.runtime.Util.tag("Object", "plaid.lang", vAr23$plaid);
 		
 	}
	
	@RepresentsState(name="Object") 
	public static PlaidObject obj = Util.newState();
	
	static {
		PlaidMemberDef eqeq = Util.memberDef("eqeq$plaid", Object$Tag$plaid, false, true);
		@RepresentsMethod(name = "eqeq$plaid", toplevel = false)
		PlaidObject eqeqProtoMethod = Util.protoMethod("plaid.lang.Object.eqeq$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				if (thisVar == args)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		});
		obj.addMember(eqeq, eqeqProtoMethod);		
	}
	static {
		obj.addTopTag(Object$Tag$plaid);	
	}
}
