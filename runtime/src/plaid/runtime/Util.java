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
package plaid.runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import plaid.runtime.models.map.PlaidStateMap;
import plaid.runtime.utils.Delegate;

public class Util {
	public static final String thisVar = "this";
	private static PlaidRuntime rt = PlaidRuntime.getRuntime();
    private static PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
	
	public static PlaidObject newObject() throws PlaidException {
		PlaidState object = (PlaidState)cl.lookup("plaid.lang.Object", unit());
		PlaidObject value = (PlaidObject)object.instantiate();
		return value;
	}

	public static PlaidState newState() throws PlaidException {
		return cl.state();
//		PlaidState ps = (PlaidState)cl.lookup("plaid.lang.State", unit());
//		PlaidState result = (PlaidState)ps.instantiate();
//		result.removeState(ps);
//		return result;
	}
	
//	public static PlaidState newState(String name) throws PlaidException {
//		PlaidState ps = (PlaidState)cl.lookup("plaid.lang.State", unit());
//		PlaidState result = (PlaidState)ps.instantiate();
//		result.removeState(ps);
//		return result;
//	}
	
	public static PlaidObject string(String s) throws PlaidException {
		PlaidState intState = (PlaidState)cl.lookup("plaid.lang.String", unit());
		PlaidJavaObject value = (PlaidJavaObject)intState.instantiate();
		value.setJavaObject(s);
		return value;
	}

	public static PlaidObject integer(Integer i) throws PlaidException {
		PlaidState intState = toPlaidState(cl.lookup("plaid.lang.Integer", unit()));
		PlaidJavaObject value = (PlaidJavaObject)intState.instantiate();
		value.setJavaObject(i);
		return value;
	}
	
	public static PlaidObject trueObject() throws PlaidException {
		PlaidState trueState = toPlaidState(cl.lookup("plaid.lang.True", unit()));
		PlaidObject value = (PlaidObject)trueState.instantiate();
		return value;
	}
	
	public static PlaidObject falseObject() throws PlaidException {
		PlaidState trueState = toPlaidState(cl.lookup("plaid.lang.False", unit()));
		PlaidObject value = (PlaidObject)trueState.instantiate();
		return value;
	}
	
	public static PlaidObject unit() throws PlaidException {
		return cl.unit();
	}
	
	public static PlaidObject call(PlaidObject func, PlaidObject args) {
		return rt.call(func, args);
	}
	
	public static PlaidMethod protoField(Delegate dlg) {
		return cl.protoField(dlg);
	}
	
	public static PlaidMethod protoMethod(Delegate dlg) {
		return cl.protoMethod(dlg);
	}
	
	public static PlaidObject lookup(String name, PlaidObject thisVar) {
		return cl.lookup(name, thisVar);
	}
	
	public static PlaidObject lookup(String name, PlaidScope scope) {
		return cl.lookup(name, scope);
	}
	
	public static PlaidTag tag(String tag, PlaidState caseOf) {
		return cl.tag(tag, caseOf);
	}
	
	public static Object[] convertParamsToArray(PlaidObject params) throws PlaidException {
		List<Object> objs = new ArrayList<Object>();
		if ( params.getStates().contains(lookup("plaid.lang.Pair", unit())) ) {
			PlaidObject elem = params;
			while ( elem != unit() ) {
				if ( elem.getStates().contains(lookup("plaid.lang.Pair", unit()))) {
					PlaidObject fst = elem.getMembers().get("fst");
					if ( fst instanceof PlaidJavaObject ) {
						objs.add(((PlaidJavaObject)fst).getJavaObject());
						elem = elem.getMembers().get("snd");
					} else {
						throw new PlaidInvalidArgumentException("Can ONLY convert PlaidJava Object");
					}
				}
			}
		} else if ( params instanceof PlaidJavaObject ){
			objs.add(((PlaidJavaObject)params).getJavaObject());
		} else {
			throw new PlaidInvalidArgumentException("Can ONLY convert PlaidJava Object");
		}

		return objs.toArray();
	}
	
	public static PlaidObject converArrayToParams(Object[] objs) {
		if ( objs.length == 0 ) {
			return unit();
		}
		PlaidState ps = toPlaidState(lookup("plaid.lang.Pair", unit()));
		PlaidObject result = ps.instantiate();

		result.addMember("fst", cl.packJavaObject(objs[0]));
		result.addMember("snd", converArrayToParams(Arrays.copyOfRange(objs, 1, objs.length)));
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Class convertPrimitiveTypes(Class type) {
		if ( type.isPrimitive() ) {
			if ( type.getName().equals("int") ) {
				return Integer.class;
			} else if ( type.getName().equals("boolean") ) {
				return Boolean.class;
			} else if ( type.getName().equals("byte") ) {
				return Byte.class;
			} else if ( type.getName().equals("float")) {
				return Float.class;
			} else if ( type.getName().equals("char")) {
				return Character.class;
			} else if ( type.getName().endsWith("double")) {
				return Double.class;
			} else if ( type.getName().endsWith("short")) {
				return Short.class;
			} else if ( type.getName().endsWith("void")) {
				return Void.class;
			} else if ( type.getName().endsWith("long")) {
				return Long.class;
			}
		}
		return type;
	}

	public static boolean isKeyword(String w) {
		for ( String kw : PlaidConstants.JAVA_KEYWORDS ) {
			if ( kw.equals(w) ) {
				return true;
			} 
		}
		return false;
	}

	public static PlaidJavaObject toPlaidJavaObject(PlaidObject obj) throws PlaidCastException {
		if ( obj instanceof PlaidJavaObject ) {
			return (PlaidJavaObject)obj;
		} else {
			throw new PlaidCastException("Failed to cast '"+obj+"'to PlaidJavaObject.");
		}
	}
	
	public static PlaidMethod toPlaidMethod(PlaidObject obj) throws PlaidCastException {
		if ( obj instanceof PlaidMethod ) {
			return (PlaidMethod)obj;
		} else {
			if ( obj instanceof PlaidScope )  {
				throw new PlaidMethodNotFoundException("Method '" + obj +"' not found.");
			} else {
				throw new PlaidCastException("Failed to cast '"+obj+"'to PlaidMethod.");
			}
		}
	}
	
	public static PlaidState toPlaidState(PlaidObject obj) throws PlaidCastException {
		if ( obj instanceof PlaidState ) {
			return (PlaidState)obj;
		} else {
			throw new PlaidCastException("Failed to cast '"+obj+"'to PlaidState.");
		}
	}
	
	public static String getQualifiedIdString(PlaidState state) {
		//TODO: update PlaidState interface to include getQI
		return ((PlaidStateMap)state).getQI().toString();
	}
}
