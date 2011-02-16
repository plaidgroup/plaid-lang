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
import plaid.runtime.annotations.RepresentsMethod;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.models.map.PlaidLocalScopeMap;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.Import;

@RepresentsState(name = "Double", inPackage = "plaid.lang", toplevel = true, javaobject = true, jsonRep = "{\"member_type\": \"state\",\"name\": \"Double\",\"members\": [{\"member_type\": \"method\",\"name\": \"+\",\"ret_type\": \"plaid.lang.Double\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"-\",\"ret_type\": \"plaid.lang.Double\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"*\",\"ret_type\": \"plaid.lang.Double\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"/\",\"ret_type\": \"plaid.lang.Double\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"%\",\"ret_type\": \"plaid.lang.Double\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"==\",\"ret_type\": \"plaid.lang.Boolean\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"!=\",\"ret_type\": \"plaid.lang.Boolean\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"<\",\"ret_type\": \"plaid.lang.Boolean\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \">\",\"ret_type\": \"plaid.lang.Boolean\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \"<=\",\"ret_type\": \"plaid.lang.Boolean\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],},{\"member_type\": \"method\",\"name\": \">=\",\"ret_type\": \"plaid.lang.Boolean\",\"arg_types\": [\"plaid.lang.Double\",\"plaid.lang.Double\"],}]}")
public class Double$plaid {
	public static PlaidScope globalScope = PlaidRuntime.getRuntime()
			.getClassLoader()
			.globalScope("plaid.lang", new ArrayList<Import>());

	@RepresentsState(name = "Double")
	public static PlaidObject foo = Util.newState();

	static {
		PlaidMemberDef plus = Util.memberDef("plus$plaid",
				"plaid.lang.Double", false, false);
		@RepresentsMethod(name = "plus$plaid", toplevel = false)
		PlaidObject plusProtoMethod = Util.protoMethod(
				"plaid.lang.Double.plus$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						double x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue();
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x += ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x += ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue();
						} else {
							throw new PlaidException("Cannot add '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						return Util.floatingDouble(x);
					}
				});
		foo.addMember(plus, plusProtoMethod);

		PlaidMemberDef sub = Util.memberDef("sub$plaid", "plaid.lang.Double",
				false, false);
		@RepresentsMethod(name = "sub$plaid", toplevel = false)
		PlaidObject subProtoMethod = Util.protoMethod(
				"plaid.lang.Double.sub$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						double x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue();
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x -= ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x -= ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue();
						} else {
							throw new PlaidException("Cannot sub '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						return Util.floatingDouble(x);
					}
				});
		foo.addMember(sub, subProtoMethod);

		PlaidMemberDef mult = Util.memberDef("mult$plaid",
				"plaid.lang.Double", false, false);
		@RepresentsMethod(name = "mult$plaid", toplevel = false)
		PlaidObject multProtoMethod = Util.protoMethod(
				"plaid.lang.Double.mult$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						double x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue();
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x *= ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x *= ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue();
						} else {
							throw new PlaidException("Cannot multiply '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object with Double.");
						}
						return Util.floatingDouble(x);
					}
				});
		foo.addMember(mult, multProtoMethod);

		PlaidMemberDef div = Util.memberDef("div$plaid", "plaid.lang.Double",
				false, false);
		@RepresentsMethod(name = "div$plaid", toplevel = false)
		PlaidObject divProtoMethod = Util.protoMethod(
				"plaid.lang.Double.div$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						double x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue();
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x /= ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x /= ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue();
						} else {
							throw new PlaidException("Cannot divide Double through  '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object.");
						}
						return Util.floatingDouble(x);
					}
				});
		foo.addMember(div, divProtoMethod);

		PlaidMemberDef mod = Util.memberDef("mod$plaid", "plaid.lang.Double",
				false, false);
		@RepresentsMethod(name = "mod$plaid", toplevel = false)
		PlaidObject modProtoMethod = Util.protoMethod(
				"plaid.lang.Double.mod$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						double x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue();
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x %= ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x %= ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue();
						} else {
							throw new PlaidException("Cannot mod '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						return Util.floatingDouble(x);
					}
				});
		foo.addMember(mod, modProtoMethod);

		PlaidMemberDef eqeq = Util.memberDef("eqeq$plaid",
				"plaid.lang.Double", false, false);
		@RepresentsMethod(name = "eqeq$plaid", toplevel = false)
		PlaidObject eqeqProtoMethod = Util.protoMethod(
				"plaid.lang.Double.eqeq$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						boolean x;
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() == ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() == ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue(); 
						} else {
							throw new PlaidException("Cannot compare '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						if (x == true)
							return Util.trueObject();
						else
							return Util.falseObject();
					}
				});
		foo.addMember(eqeq, eqeqProtoMethod);

		PlaidMemberDef bangeq = Util.memberDef("bangeq$plaid",
				"plaid.lang.Double", false, false);
		@RepresentsMethod(name = "bangeq$plaid", toplevel = false)
		PlaidObject bangeqProtoMethod = Util.protoMethod(
				"plaid.lang.Double.bangeq$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						boolean x;
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() != ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() != ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue(); 
						} else {
							throw new PlaidException("Cannot comapre '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						if (x == true)
							return Util.trueObject();
						else
							return Util.falseObject();
					}
				});
		foo.addMember(bangeq, bangeqProtoMethod);

		PlaidMemberDef lt = Util.memberDef("lt$plaid", "plaid.lang.Double",
				false, false);
		@RepresentsMethod(name = "lt$plaid", toplevel = false)
		PlaidObject ltProtoMethod = Util.protoMethod(
				"plaid.lang.Double.lt$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						boolean x;
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() < ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() < ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue(); 
						} else {
							throw new PlaidException("Cannot compare '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						if (x == true)
							return Util.trueObject();
						else
							return Util.falseObject();
					}
				});
		foo.addMember(lt, ltProtoMethod);

		PlaidMemberDef gt = Util.memberDef("gt$plaid", "plaid.lang.Double",
				false, false);
		@RepresentsMethod(name = "gt$plaid", toplevel = false)
		PlaidObject gtProtoMethod = Util.protoMethod(
				"plaid.lang.Double.gt$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						boolean x;
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() > ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() > ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue(); 
						} else {
							throw new PlaidException("Cannot compare '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						if (x == true)
							return Util.trueObject();
						else
							return Util.falseObject();
					}
				});
		foo.addMember(gt, gtProtoMethod);

		PlaidMemberDef lteq = Util.memberDef("lteq$plaid",
				"plaid.lang.Double", false, false);
		@RepresentsMethod(name = "lteq$plaid", toplevel = false)
		PlaidObject lteqProtoMethod = Util.protoMethod(
				"plaid.lang.Double.lteq$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						boolean x;
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() <= ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() <= ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue(); 
						} else {
							throw new PlaidException("Cannot compare '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						if (x == true)
							return Util.trueObject();
						else
							return Util.falseObject();
					}
				});
		foo.addMember(lteq, lteqProtoMethod);

		PlaidMemberDef gteq = Util.memberDef("gteq$plaid",
				"plaid.lang.Double", false, false);
		@RepresentsMethod(name = "gteq$plaid", toplevel = false)
		PlaidObject gteqProtoMethod = Util.protoMethod(
				"plaid.lang.Double.gteq$plaid", new Delegate() {
					@Override
					public PlaidObject invoke(PlaidObject thisVar,
							PlaidObject args) throws PlaidException {
						@SuppressWarnings("unused")
						PlaidScope scope = new PlaidLocalScopeMap(globalScope);
						boolean x;
						if ( ((PlaidJavaObject) args).getJavaObject() instanceof Double ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() >= ((Double) ((PlaidJavaObject) args).getJavaObject()).doubleValue();
						} else if ( ((PlaidJavaObject) args).getJavaObject() instanceof Integer ) {
							x = ((Double) ((PlaidJavaObject) thisVar).getJavaObject()).doubleValue() >= ((Integer) ((PlaidJavaObject) args).getJavaObject()).intValue(); 
						} else {
							throw new PlaidException("Cannot compare '" + ((PlaidJavaObject) args).getJavaObject().getClass().getCanonicalName() +"' object to Double.");
						}
						if (x == true)
							return Util.trueObject();
						else
							return Util.falseObject();
					}
				});
		foo.addMember(gteq, gteqProtoMethod);
	}
}
