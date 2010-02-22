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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInstantiationException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;

public final class PlaidJavaStateMap extends PlaidStateMap implements PlaidJavaObject {
	protected Object value;
	protected Class<Object> valueClass;
	protected boolean reflected = false;
	
	public PlaidJavaStateMap(Object value, Class<Object> valueClass) {
		super();
		this.value   = value;
		this.valueClass = valueClass;
		this.members = new HashMap<String, PlaidObject>();
		createConstructor();
	}
	
	private void createConstructor() {
		this.members.put(PlaidJavaConstructorMap.NAME, new PlaidJavaConstructorMap(valueClass));
	}

	public PlaidJavaStateMap() {
		this.value = null;
		this.valueClass = null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void setJavaObject(Object value) {
		this.value = value;
		this.valueClass = (Class<Object>) value.getClass();
	}
	
	@Override
	public Object getJavaObject() throws PlaidException {
		return value;
	}
	
	@Override
	public Map<String, PlaidObject> getMembers() {
		if ( reflected == false && valueClass != null ) {
			for ( Field f : valueClass.getFields() ) {
				Object obj;
				try {
					obj = f.get(value);
					members.put(f.getName(), new PlaidJavaObjectMap(obj));
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Wrong argument.");
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot get field value");
				}				
			}
			
			for ( Method m : valueClass.getMethods() ) {
				members.put(m.getName(), new PlaidJavaMethodMap(m.getName(), value, valueClass));
			}
			reflected = true; //TODO: Tell Sven we did this
		}
		
		return Collections.unmodifiableMap(members);
	}
	
	@Override
	public PlaidState with(PlaidState... args) throws PlaidException {
		throw new PlaidIllegalAccessException("Cannot combine a JavaObject with an other object.");
	}

	@Override
	public PlaidObject instantiate(PlaidObject ...args) throws PlaidException {
		Object value;
		try {
			if ( args.length == 0 ) {
				value = valueClass.newInstance();
			} else if (args.length == 1 ) {
				Object params[] = Util.convertParamsToArray(args[1]);
				value = instantiate(params);
			} else {
				throw new PlaidInvalidArgumentException("Cannot call instanciate method with more thatn 1 parameter.");
			}
			return new PlaidJavaObjectMap(value);
		} catch (InstantiationException e) {
			throw new PlaidInstantiationException("Failed to create a instance of class : " + templateClass);
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalAccessException("Cannot access class :" + templateClass);
		}
	}	
	
	public String toString() {
		return "State(" + valueClass + ")";
	}

	protected PlaidObject instantiate(Object... args) {
		try {
			Class<?>[] paramTypes = null;
			Object[] params = null;
			Constructor<?> handle = null;

			params = args;
			paramTypes = new Class[params.length];
			for (int i = 0; i < params.length; i++) {
				paramTypes[i] = params[i].getClass();
			}
			
			try {
				//handle = instanceClass.getMethod(name, paramTypes);
				for ( Constructor<?> c : valueClass.getConstructors()) {
					if ( c.getName().equals(name)) {
						Class<?>[] mpTypes = c.getParameterTypes();
						if ( mpTypes.length == paramTypes.length ) {
							boolean match = true;
							for (int i = 0; i < mpTypes.length; i++) {
								Class<?> mpType = Util.convertPrimitiveTypes(mpTypes[i]);
								Class<?> paramType = Util.convertPrimitiveTypes(paramTypes[i]);
								if ( paramType.isAssignableFrom(mpType) == false ) {
									match = false;
								}
							}						
							if ( match ) {
								handle = c;
								break;
							}
						}
					}
				}
			} catch (SecurityException e) {
				throw new PlaidIllegalAccessException("Cannot find method : " + name);
			}
			
			Object result;
			result = handle.newInstance(params);
			if ( result == null ) {
				return  PlaidRuntime.getRuntime().getClassLoader().unit();
			} else {
				return new PlaidJavaObjectMap(result);
			}
		} catch (IllegalArgumentException e) {
			throw new PlaidInvalidArgumentException("Cannot call method : " + name);
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalAccessException("Cannot call method : " + name);
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalAccessException("Cannot call method : " + name);
		} catch (InstantiationException e) {
			throw new PlaidIllegalAccessException("Cannot instanciate : " + name);
		}
	}
	
}
