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
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidScope;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.models.map.PlaidLocalScopeMap;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.Import;

@RepresentsState(name="Integer", toplevel=true, javaobject=true)
public class Integer$plaid {
	public static PlaidScope globalScope = PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang", new ArrayList<Import>());
	
	@RepresentsState(name="Integer") 
	public static PlaidObject foo = Util.newState();
	
	static {
		PlaidMemberDef plus = Util.memberDef("plus$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(plus, Util.protoMethod("plaid.lang.Integer.plus$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				int x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() +
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				return Util.integer(x);
			}
		}));
		
		PlaidMemberDef sub = Util.memberDef("sub$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(sub, Util.protoMethod("plaid.lang.Integer.sub$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				int x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() -
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				return Util.integer(x);
			}
		}));
		
		PlaidMemberDef mult = Util.memberDef("mult$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(mult, Util.protoMethod("plaid.lang.Integer.mult$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				int x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() *
				((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				return Util.integer(x);
			}
		}));
		
		PlaidMemberDef div = Util.memberDef("div$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(div, Util.protoMethod("plaid.lang.Integer.div$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				int x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() /
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				return Util.integer(x);
			}
		}));
		
		PlaidMemberDef mod = Util.memberDef("mod$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(mod, Util.protoMethod("plaid.lang.Integer.mod$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				int x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() %
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				return Util.integer(x);
			}
		}));
		
		PlaidMemberDef eqeq = Util.memberDef("eqeq$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(eqeq, Util.protoMethod("plaid.lang.Integer.eqeq$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				boolean x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() ==
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				if (x == true)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		}));
		
		PlaidMemberDef bangeq = Util.memberDef("bangeq$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(bangeq, Util.protoMethod("plaid.lang.Integer.bangeq$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				boolean x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() !=
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				if (x == true)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		}));
		
		PlaidMemberDef lt = Util.memberDef("lt$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(lt, Util.protoMethod("plaid.lang.Integer.lt$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				boolean x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()) <
					((Integer)((PlaidJavaObject)args).getJavaObject());
				if (x == true)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		}));
		
		PlaidMemberDef gt = Util.memberDef("gt$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(gt, Util.protoMethod("plaid.lang.Integer.gt$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				boolean x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() >
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				if (x == true)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		}));
		
		PlaidMemberDef lteq = Util.memberDef("lteq$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(lteq, Util.protoMethod("plaid.lang.Integer.lteq$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				boolean x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() <=
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				if (x == true)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		}));

		PlaidMemberDef gteq = Util.memberDef("gteq$plaid", "plaid.lang.Integer", false, false);
		foo.addMember(gteq, Util.protoMethod("plaid.lang.Integer.gteq$plaid", new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)  throws PlaidException {
				@SuppressWarnings("unused")
				PlaidScope scope = new PlaidLocalScopeMap(globalScope);
				boolean x = ((Integer)((PlaidJavaObject)thisVar).getJavaObject()).intValue() >=
					((Integer)((PlaidJavaObject)args).getJavaObject()).intValue();
				if (x == true)
					return Util.trueObject();
				else
					return Util.falseObject();
			}
		}));	
	}
}
