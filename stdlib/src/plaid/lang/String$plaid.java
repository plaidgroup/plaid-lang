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
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidScope;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.Import;

@RepresentsState(name="String", toplevel=true, javaobject=true)
public class String$plaid {
	public static PlaidScope packageScope = PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang", new ArrayList<Import>());
	
	@RepresentsState(name="Str") 
	public static PlaidObject foo = Util.newObject();
	
	static {		
		foo.addMember("plus$plaid", Util.protoMethod(new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = PlaidRuntime.getRuntime().getClassLoader().lambdaScope(packageScope, thisVar);
				String x = ((String)((PlaidJavaObject)thisVar).getJavaObject()) +
				(((PlaidJavaObject)args).getJavaObject().toString());
				return Util.string(x);
			}
		}));
		foo.addMember("substring$plaid", Util.protoMethod(new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = PlaidRuntime.getRuntime().getClassLoader().lambdaScope(packageScope, thisVar);
				String x;
				if (args instanceof PlaidJavaObject) {
					x = ((String)((PlaidJavaObject)thisVar).getJavaObject()).substring(((Integer)((PlaidJavaObject)args).getJavaObject()));
				} else { // must be a pair
					PlaidObject firstMethod = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", args);
					PlaidObject firstArg = plaid.runtime.Util.call(args, firstMethod);
					PlaidObject secondMethod = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", args);
					PlaidObject secondArg = plaid.runtime.Util.call(args, secondMethod);
					x = ((String)((PlaidJavaObject)thisVar).getJavaObject()).substring(((Integer)((PlaidJavaObject)firstArg).getJavaObject()),
																					   ((Integer)((PlaidJavaObject)secondArg).getJavaObject()));	
				}
				return Util.string(x);
			}
		}));
	}
	
	//TODO : should this String be a matchable tag?
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.String")
	public static final plaid.runtime.PlaidTag StringTag$plaid;
	static {
		final plaid.runtime.PlaidState vAr100$plaid;
		final plaid.runtime.PlaidObject vAr101$plaid;
		vAr101$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid.lang.Object", packageScope);
		vAr100$plaid = plaid.runtime.Util.toPlaidState(vAr101$plaid);
		StringTag$plaid = plaid.runtime.Util.tag("plaid.lang.String", vAr100$plaid);
	}
}