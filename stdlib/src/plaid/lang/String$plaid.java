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
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidScope;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.models.map.PlaidJavaObjectMap;
import plaid.runtime.models.map.PlaidLocalScopeMap;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.Import;

@RepresentsState(name="String", inPackage = "plaid.lang", toplevel=true, javaobject=true, jsonRep = "{\"member_type\": \"state\",\"name\": \"String\",\"members\": [{\"member_type\": \"method\",\"name\": \"+\",\"ret_type\": \"plaid.lang.String\",\"arg_types\": [\"plaid.lang.String\",\"plaid.lang.String\"]},{\"member_type\": \"method\",\"name\": \"substring\",\"ret_type\": \"plaid.lang.String\",\"arg_types\": [\"plaid.lang.Integer\"]},{\"member_type\": \"method\",\"name\": \"substring2\",\"ret_type\": \"plaid.lang.String\",\"arg_types\": [\"plaid.lang.Integer\",\"plaid.lang.Integer\"]}]}")
public class String$plaid {
	public static PlaidScope globalScope = PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang", new ArrayList<Import>());
	
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.String")
 	public static final plaid.runtime.PlaidTag String$Tag$plaid;
 	static {
 		final plaid.runtime.PlaidTag vAr23$plaid;
 		vAr23$plaid = null;
 		String$Tag$plaid = plaid.runtime.Util.tag("String", "plaid.lang", vAr23$plaid);
 		
 	}
	
	@RepresentsState(name="String") 
	public static PlaidObject foo = Util.newObject();
	
	static {		
		PlaidMemberDef plus = Util.memberDef("plus$plaid", String$Tag$plaid, false, false);
		foo.addMember(plus, Util.protoMethod("plaid.lang.String.plus$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				if ( args instanceof PlaidJavaObjectMap && ((PlaidJavaObjectMap)args).getJavaObject() != null ) {
					String x = ((String)((PlaidJavaObject)thisVar).getJavaObject()) +
					(((PlaidJavaObject)args).getJavaObject().toString());
					return Util.string(x);
				} else {
					//throw new PlaidInvalidArgumentException("Cannot concatenate a String with : " + args);
					PlaidMemberDef mdef = args.getMember("toString");
					PlaidObject result = Util.call(mdef.getValue(), Util.unit());
					if ( result instanceof PlaidJavaObject ) {
						return Util.string(((String)((PlaidJavaObject)thisVar).getJavaObject()) + ((PlaidJavaObject)result).getJavaObject().toString() );
					} else  {						
						throw new PlaidInvalidArgumentException("Cannot concatenate a String with : " + args);
					}
				}
			}
		}));
		PlaidMemberDef eqeq = Util.memberDef("eqeq$plaid", String$Tag$plaid, false, false);
		foo.removeMember("eqeq$plaid");
		foo.addMember(eqeq, Util.protoMethod("plaid.lang.String.eqeq$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				if ( args instanceof PlaidJavaObjectMap && ((PlaidJavaObjectMap)args).getJavaObject() != null ) {
					boolean eq = ((String)((PlaidJavaObject)thisVar).getJavaObject()).equals
					(((PlaidJavaObject)args).getJavaObject().toString());
					return Util.boolObject(eq);
				} else {
					throw new PlaidInvalidArgumentException("Cannot compare a String with : " + args);
				}
			}
		}));
		PlaidMemberDef substring = Util.memberDef("substring$plaid", String$Tag$plaid, false, false);
//		foo.addMember(substring, Util.protoMethod("plaid.lang.String.substring$plaid", new Delegate() {
//			@Override
//			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
//				@SuppressWarnings("unused")
//				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
//				String x;
//				if (args instanceof PlaidJavaObject) {
//					String javaStr = (String)((PlaidJavaObject)thisVar).getJavaObject();
//					Integer index = (Integer)((PlaidJavaObject)args).getJavaObject();
//					x = javaStr.substring(index);
//				} else { // must be a pair
//					throw new RuntimeException("Invalid argument to substring.");
//				}
//				return Util.string(x);
//			}
//		}));
//		PlaidMemberDef substring2 = Util.memberDef("substring2$plaid", String$Tag$plaid, false, false);
//		foo.addMember(substring2, Util.protoMethod("plaid.lang.String.substring2$plaid", new Delegate() {
//			@Override
//			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
//				@SuppressWarnings("unused")
//				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
//				if (args instanceof PlaidJavaObject) {
//					throw new RuntimeException("Invalid argument to substring2.");
//				}
//				String x;
//				PlaidObject firstMethod = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", args);
//				PlaidObject secondArg = plaid.runtime.Util.call(args, firstMethod);
//				PlaidObject secondMethod = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", args);
//				PlaidObject firstArg = plaid.runtime.Util.call(args, secondMethod);
//				x = ((String)((PlaidJavaObject)thisVar).getJavaObject()).substring(((Integer)((PlaidJavaObject)firstArg).getJavaObject()),
//																				   ((Integer)((PlaidJavaObject)secondArg).getJavaObject()));
//				return Util.string(x);
//			}
//		}));
	}
	static {
		foo.addTopTag(String$Tag$plaid);	
	}
}