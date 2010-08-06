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
 
package plaid.runtime.models.map;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plaid.runtime.PlaidClassLoader;
import plaid.runtime.PlaidClassNotFoundException;
import plaid.runtime.PlaidConstants;
import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidPackage;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidScope;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.annotations.RepresentsField;
import plaid.runtime.annotations.RepresentsMethod;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.Import;
import plaid.runtime.utils.Lambda;
import plaid.runtime.utils.QualifiedIdentifier;

public final class PlaidClassLoaderMap implements PlaidClassLoader {
	protected PlaidRuntimeMap runtime;
	protected HashMap<String, PlaidObject> singletons = new HashMap<String, PlaidObject>();
	public final PlaidObject unit;
	// TODO: Change to singleton
	
	public PlaidClassLoaderMap(PlaidRuntimeMap runtime) {
		this.runtime = runtime;
		unit = loadClass("plaid.lang.Unit");
		((PlaidObjectMap)unit).setReadOnly(false);
		unit.addState(unit);
		((PlaidObjectMap)unit).setReadOnly(true);
	}
	
	@Override
	public PlaidObject lookup(String name, PlaidObject pthis) throws PlaidException {

		if (pthis == unit()) {
			return loadClass(name);
		}
		else if (pthis instanceof PlaidLookupMap) {
			PlaidLookupMap ppm = (PlaidLookupMap)pthis;
			return lookup(ppm.append(name));
		}
		else {
			// if the name is our special Plaid constructor for Java objects, 
			// look in the prototype of the current object
			if (name.equals(PlaidJavaConstructorMap.NAME)) {
				if (pthis instanceof PlaidJavaStateMap) {
					PlaidJavaStateMap pjsm = (PlaidJavaStateMap)pthis;
					Map<PlaidMemberDef, PlaidObject> members = pjsm.prototype.getMembers();
					for (PlaidMemberDef m : members.keySet()) {
						if (m.getMemberName().equals(name)) {
							return members.get(m);
						}
					}
				}
				else {
					throw new PlaidRuntimeException("Non-Java object has a Java constructor.");
				}
			}
			
			// check members 
			Map<PlaidMemberDef,PlaidObject> members = pthis.getMembers();
			for (PlaidMemberDef m : members.keySet()) {
				if (name.equals(m.getMemberName())) {
					return members.get(m);
				}
			}
			
			for (PlaidObject os : pthis.getStates()) {
				PlaidStateMap s = (PlaidStateMap)os;
				if (s.getName().equals(name)) {
					return s;
				}
			}
			
			// check packages recursively
			for (PlaidObject os : pthis.getStates()) {
				PlaidStateMap s = (PlaidStateMap)os;
				PlaidPackage pkg = s.getPackage();					
				try {
					return lookup(name, pkg);
				} catch (PlaidException ex) { /* continue with next state */ }
			}
			
			throw new PlaidException("Member '" + name + "' not found.");
		}
	}

	@Override
	public PlaidObject lookup(String name, PlaidScope currentScope) throws PlaidException {
		return currentScope.lookup(name);
	}

	protected PlaidObject lookup(PlaidLookupMap lookup) throws PlaidClassNotFoundException {
		QualifiedIdentifier lookupAtTopLevel = lookup.getToLookup();
		QualifiedIdentifier lookupInPackage = lookup.getThePackage().append(lookupAtTopLevel);
		
		// check if we have already looked up this QID in the package scope
		if ( singletons.containsKey(lookupInPackage)) {
			return singletons.get(lookupInPackage);
		}
		//check if we have already looked up this QID in the top level scope
		if ( singletons.containsKey(lookupAtTopLevel.getQI())) {
			return singletons.get(lookupAtTopLevel.getQI());
		}
		
		//lookup the QID in the package scope
		PlaidObject value = loadClass(lookupInPackage.toString());
		if (value != null) return value; //found an actual declaration
		
		//lookup QID in the top level scope
		PlaidObject topLevelValue = loadClass(lookupAtTopLevel.toString());
		if (topLevelValue != null) return topLevelValue; //found an actual declaration
		
		//otherwise, we need to return this lookup context
		return lookup;
	}
	
	public PlaidObject loadClass(String name) throws PlaidClassNotFoundException {
		if ( singletons.containsKey(name) )
			return singletons.get(name);

		// check if we can find the file 
		ClassLoader cl = this.getClass().getClassLoader();
	
		String names[] = {name + PlaidConstants.ID_SUFFIX, name};
		for ( String current : names) {
			try {
				@SuppressWarnings("unchecked")
				Class<Object> obj = (Class<Object>) cl.loadClass(current);
				return createPlaidObjectFromClass(new QualifiedIdentifier(current), obj);
			} catch (ClassNotFoundException e) {
				// If there is no classfile then we need to keep searching
			}
		}
		return null;
	}

	public PlaidJavaObject packJavaObject(Object o) throws PlaidException {
		return new PlaidJavaObjectMap(o);
	}
	
	@Override
	public PlaidMethod lambda(Lambda fn) throws PlaidException {
		return new PlaidFunctionMap(fn);
	}

	@Override
	public PlaidMethod protoMethod(String fullyQualName, Delegate fieldInit) throws PlaidException {
		return new PlaidProtoMethodMap(fullyQualName, fieldInit);
	}

	@Override
	public PlaidMethod protoField(Delegate method) throws PlaidException {
		return new PlaidProtoFieldMap(method);
	}
	
	@Override
	public PlaidObject unit() throws PlaidException {
		return unit;
	}
	
	@Override 
	public PlaidState state() {
		return new PlaidStateMap();
	}
	
	@Override 
	public PlaidTag tag(String tagName, PlaidState caseOf) {
		return new PlaidTagMap(tagName, caseOf);
	}


	/***************************************************************************
	 * 
	 *  INTERNAL methods 
	 * 
	 ***************************************************************************/
	
	protected PlaidObject createPlaidObjectFromClass(QualifiedIdentifier qi, Class<Object> objClass) throws PlaidException {
		if (  objClass.getAnnotation(RepresentsState.class) != null ) {
			RepresentsState psa = objClass.getAnnotation(RepresentsState.class);
			return createTopLevelPlaidStateFromClass(qi, psa, objClass);
		} else if ( objClass.getAnnotation(RepresentsField.class) != null ) {
			RepresentsField pfa = objClass.getAnnotation(RepresentsField.class);
			return createTopLevelPlaidFieldFromClass(qi, pfa, objClass);
		} else if ( objClass.getAnnotation(RepresentsMethod.class) != null ) {
			RepresentsMethod pma = objClass.getAnnotation(RepresentsMethod.class); 
			return createTopLevelPlaidMethodFromClass(qi, pma, objClass);
		} else {
			// must be a normal Java class -> generate a JavaObject
			PlaidJavaStateMap javaState = new PlaidJavaStateMap(objClass, objClass);
			javaState.setReadOnly(true);
			singletons.put(qi.getQI(), javaState);
			return javaState;
		}
	}

	protected PlaidObject createTopLevelPlaidStateFromClass(QualifiedIdentifier qi, 
															RepresentsState psa, 
															Class<Object> objClass) throws PlaidException {

		PlaidPackageMap pkg = new PlaidPackageMap(qi.getPrefix());
		String name = (qi.getSuffix().endsWith(PlaidConstants.ID_SUFFIX))?qi.getSuffix().substring(0, qi.getSuffix().length()-PlaidConstants.ID_SUFFIX.length()):qi.getSuffix();
		PlaidStateMap psm = new PlaidStateMap(pkg, name, objClass);

		// states are always singletons
		singletons.put(qi.getPrefix().append(psa.name()).toString(), psm);
		// states are always immutable 
		//psm.setReadOnly(true);
		return psm;
	}

	protected PlaidObject createTopLevelPlaidMethodFromClass(QualifiedIdentifier qi,
															 RepresentsMethod pma, 
															 Class<Object> objClass) throws PlaidException {
		
		for ( Field f : objClass.getFields() ) {			
			RepresentsMethod ma = f.getAnnotation(RepresentsMethod.class);
			if ( ma != null && ma.name().endsWith(pma.name()) && f.getType().isAssignableFrom(PlaidMethod.class) ) {
				try {					
					PlaidMethod v = (PlaidMethod) f.get(objClass);
					singletons.put(qi.getQI(), v);
					if ( v instanceof PlaidFunctionMap ) {
						((PlaidFunctionMap)v).setName(qi.getQI());
					}
					return v;
				} catch (IllegalArgumentException e) {
					throw new PlaidIllegalAccessException("Cannot find " + qi.getQI());
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot find " + qi.getQI());
				}
			}
		}
		throw new PlaidIllegalAccessException("Cannot find " + qi.getQI());
	}

	protected PlaidObject createTopLevelPlaidFieldFromClass(QualifiedIdentifier qi,
															RepresentsField pfa, 
															Class<Object> objClass) throws PlaidException {
		
		for ( Field f : objClass.getFields() ) {
			if ( f.getName().equals(qi.getSuffix()) & f.getType().isAssignableFrom(PlaidMethod.class) ) {
				
				try {					
					PlaidObject v = (PlaidObject) f.get(objClass);
					singletons.put(qi.getQI(), v);
					return v;
				} catch (IllegalArgumentException e) {
					throw new PlaidIllegalAccessException("Cannot find " + qi.getQI());
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot find " + qi.getQI());
				}
			}
		}
		throw new PlaidIllegalAccessException("Cannot find " + qi.getQI());
	}

	@Override
	public PlaidGlobalScopeMap globalScope(String qi, List<Import> imports) {
		return PlaidGlobalScopeMap.create(qi, imports);
	}

	@Override
	public PlaidLocalScopeMap localScope(PlaidScope parentScope) {
		return new PlaidLocalScopeMap(parentScope);
	}
	
	@Override
	public PlaidMemberDef memberDef(String memberName, boolean anonymous, String definedIn, boolean mutable, boolean overrides) {
		return new PlaidMemberDefMap(memberName, anonymous, definedIn, mutable, overrides);
	}
}
