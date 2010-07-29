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
import java.lang.reflect.Modifier;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.Util;
import plaid.runtime.utils.QualifiedIdentifier;

public final class PlaidJavaMethodMap extends PlaidObjectMap implements PlaidMethod {
	protected String name;
	protected Object instance;
	protected Class<?> instanceClass;
	
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
			
			Object result = null;
			Method tempHandle;
			boolean invoked = false;
			
			// check all of the implemented interfaces
			for (Class<?> c : instance.getClass().getInterfaces()) {
				tempHandle = this.getMethodHandle(this.name, c, paramTypes);
				// if we didn't find a method, go to the next interface
				if (tempHandle == null) {
					continue;
				}
				// if this one doesn't require widening, call it right away
				else if (!Util.requiresPrimitiveWidening(tempHandle, paramTypes)) {	
					try {
						result = tempHandle.invoke(instance, params);
						// if we make it this far, we succeeded in calling the method
						invoked = true;
						break;
					} catch (IllegalAccessException e) {
						// otherwise just try again
					}
				}
				// if we haven't found a potential method yet, record this one 
				else if (handle == null) {
					handle = tempHandle;
				}
				// if this one requires widening ignore it, we already have a handle so ignore it
				else if (Util.requiresPrimitiveWidening(tempHandle, paramTypes)) {
					continue;
				}
				
			}
			
			// walk back up the inheritance hierarchy, trying to call the method until 
			// we get to a public class, which either will succeed or not have the method
			if (!invoked) {
				Class<?> currClass = this.instanceClass;
				while (result == null) {
					try {
						tempHandle = this.getMethodHandle(this.name, currClass, paramTypes);
						// if we have a null handle, we've reached a point where we can 
						// no longer succeed in calling the method, since none of the superclasses 
						// will have the method we're looking for
						if (tempHandle == null) {
							throw new PlaidIllegalAccessException("Cannot call method : " + name);
						}
						else if (!Util.requiresPrimitiveWidening(tempHandle, paramTypes)) {
							result = tempHandle.invoke(instance, params);
							// if we make it here, we successfully called the method
							invoked = true;
							break;
						}
						else if (handle == null) {
							handle = tempHandle;
						}
						else if (Util.requiresPrimitiveWidening(tempHandle, paramTypes)) {
							continue;
						}
					} catch (IllegalAccessException e) {
						// if this class is public and we can't call it then something is wrong
						if (Modifier.isPublic(currClass.getModifiers())) {
							throw new PlaidIllegalAccessException("Cannot call method : " + name);
						}
						currClass = currClass.getSuperclass();
						continue;
					}
				}
				
				// if we found a handle but required primitive widening, call it now
				if (!invoked && handle != null) {
					try {
						result = handle.invoke(instance, params);
					} catch (IllegalAccessException e) {
						throw new PlaidIllegalAccessException("Cannot call method : " + name);
					}
				}
				else if (!invoked && handle == null) {
					throw new PlaidException("Cannot find method: " + name);
				}
			}
			
			if ( result == null ) {
				return  PlaidRuntime.getRuntime().getClassLoader().unit();
			} else {
				PlaidObject plaidResult = getPlaidObjectRep(result);
				// if we didn't convert this to a pure Plaid object, then add tags to it
				// to allow for pattern matching
				if (plaidResult instanceof PlaidJavaObject) {
					plaidResult.addTag(new PlaidTagMap(result.getClass().getName(), 
							new PlaidStateMap(new PlaidPackageMap(
									new QualifiedIdentifier("java.lang")), "Object", Object.class))); //TODO: will we have this tag?
				}
				return plaidResult;
				
			}
		} catch (IllegalArgumentException e) {
			throw new PlaidInvalidArgumentException("Cannot call method : " + name);
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalAccessException("Cannot call method : " + name);
		}
	}
	
	/*
	 * This function is called when we invoke a Java method and it returns 
	 * a value that must be converted to a corresponding pure Plaid value.
	 * 
	 * This includes Booleans and Integers (and implicitly the corresponding
	 * built-in types boolean and int), and Strings.
	 * 
	 * TODO: Eventually this should be done for a wider suite of types that
	 * exist on both sides.
	 */
	private PlaidObject getPlaidObjectRep(Object result) {
		// if it's a boolean, we have to convert it to our special Plaid boolean
		if (result.getClass().equals(Boolean.class)) {
			if ((Boolean)result) {
				return Util.trueObject();
			}
			else {
				return Util.falseObject();
			}
		}
		else if (result.getClass().equals(Integer.class)) {
			int val = ((Integer)result).intValue();
			return Util.integer(val);
		}
		else if (result.getClass().equals(String.class)) {
			return Util.string((String)result);
		}
		else if (result instanceof PlaidObject) {
			return (PlaidObject)result;
		}
		// otherwise just return the object wrapped in a PlaidJavaObject
		else {
			return new PlaidJavaObjectMap(result);
		}
	}
	
	private Method getMethodHandle(String methodName, Class<?> currClass, Class<?>[] paramTypes) {
		Method handle = null;
		try {
			//handle = instanceClass.getMethod(name, paramTypes);
			for ( Method m : currClass.getMethods()) {
				if ( m.getName().equals(methodName)) {
					Class<?>[] mpTypes = m.getParameterTypes();
					if ( mpTypes.length == paramTypes.length ) {
						boolean match = true;
						boolean widened = false;
						for (int i = 0; i < mpTypes.length; i++) {
							Class<?> mpType = Util.convertPrimitiveTypes(mpTypes[i]);
							
							// perform primitive widening if necessary
							Class<?> paramType = Util.widenPrimitiveType(
									Util.convertToPrimitive(paramTypes[i]), 
									Util.convertToPrimitive(mpTypes[i])
								);
							if (paramType != null) {
								widened = true;
								paramType = Util.convertPrimitiveTypes(paramType);
							}
							else {
								paramType = Util.convertPrimitiveTypes(paramTypes[i]);
							}
							
							if ( !mpType.isAssignableFrom(paramType) ) {
								match = false;
								break;
							}
						}
						// if this is the first match we've seen, then record it
						if (match && handle == null) {
							handle = m;
						}
						// if we find a match and we didn't have to widen at all, this 
						// is probably the method the user intended to call
						else if (match && handle != null && !widened) {
							handle = m;
							break;
						}
					}
				}
			}
		} catch (SecurityException e) {
			throw new PlaidIllegalAccessException("Cannot find method : " + name);
		}
		return handle;
	}
	
	@Override
	public String toString() {
		return "PlaidJavaMethod("+ name +")";
	}
}
