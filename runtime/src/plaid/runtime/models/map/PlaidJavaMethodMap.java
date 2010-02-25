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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.Util;

public final class PlaidJavaMethodMap extends PlaidObjectMap implements PlaidMethod {
	protected String name;
	protected Object instance;
	protected Class<Object> instanceClass;
	
	public PlaidJavaMethodMap(String name, Object instance, Class<Object> instanceClass) {
		super();
		this.name = name;
		this.instance = instance;
		this.instanceClass = instanceClass;
	}
	
	@Override
	public PlaidObject invoke(PlaidObject args) throws PlaidException {
		try {
			Class<?>[] paramTypes = null;
			Object[] params = null;
			Method handle = null;
			if ( args instanceof PlaidJavaObject ) {
				paramTypes = new Class[1];
				//paramTypes[0] = (Class<Object>) Object.class;
				paramTypes[0] = ((PlaidJavaObject)args).getJavaObject().getClass();
				params = new Object[1];
				params[0] = ((PlaidJavaObject)args).getJavaObject();
			} else if( args == Util.unit() )  {
				paramTypes = new Class[0];
				params = new Object[0];
			} else {
				// check for pair
				params = Util.convertParamsToArray(args);
				paramTypes = new Class[params.length];
				for (int i = 0; i < params.length; i++) {
					paramTypes[i] = params[i].getClass();
				}
			} 
			
			try {
				//handle = instanceClass.getMethod(name, paramTypes);
				for ( Method m : instanceClass.getMethods()) {
					if ( m.getName().equals(name)) {
						Class<?>[] mpTypes = m.getParameterTypes();
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
								handle = m;
								break;
							}
						}
					}
				}
			} catch (SecurityException e) {
				throw new PlaidIllegalAccessException("Cannot find method : " + name);
			}
			
			Object result;
			result = handle.invoke(instance, params);
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
		}
	}

	@Override
	public String toString() {
		return "PlaidJavaMethod("+ name +")";
	}
}
