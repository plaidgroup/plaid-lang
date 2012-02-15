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
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Collections;

//import plaid.collections.Collection;
import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;
import plaid.runtime.BigRational;
import plaid.runtime.utils.QualifiedIdentifier;

public final class PlaidJavaMethodMap extends PlaidObjectMap implements PlaidMethod {
	protected String name;
	protected Object instance;
	protected Class<?> instanceClass;
	
	protected static final PlaidStateMap javaObjectState = new PlaidStateMap(new PlaidPackageMap(QualifiedIdentifier.getQI("java.lang")), "Object", Object.class);
	
	public PlaidJavaMethodMap(String name, Object instance, Class<Object> instanceClass) {
		super();
		this.name = name;
		this.instance = instance;
		this.instanceClass = instanceClass;
	}
	
	@Override
	public PlaidObject invoke(PlaidObject args) throws PlaidException {
		try {
			Object[] params = extractArguments(args);
			Class<?>[] paramTypes = extractArgumentTypes(params);
			MethodMatch match = null;
			
			Object result = null;
			boolean invoked = false;
			
			for (Class<?> c : instance.getClass().getInterfaces()) {
				MethodMatch newMatch = this.getMethodHandle(this.name, c, paramTypes, match);
				// if this one is an exact match, call it right away
				if (newMatch != match && newMatch.compat.isExact()) {
					try {
						result = newMatch.method.invoke(instance, params);
						// if we make it this far, we succeeded in calling the method
						invoked = true;
						break;
					} catch (IllegalAccessException e) {
						// otherwise just try again
					}
				}
				// we found a better match than the one we had, so store it
				else {
					match = newMatch;
				}
			}
			
			// Workaround for Java's handling of reflection when accessing inner classes.
			// See http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4071957 for details.
			if (!invoked) {
				// Check if the class implements the Iterator or the Iterable interface
				// and if it does, try to use the interface to get the correct method handle.
				Class<?>[] interfaceClasses = {
					Iterator.class, Iterable.class
				};
				
				for (Class<?> interfaceClass : interfaceClasses) {
					MethodMatch newMatch = this.getMethodHandle(this.name, interfaceClass, paramTypes, match);
					if (newMatch != match && newMatch.compat.isExact()) {
						try {
							result = newMatch.method.invoke(instance, params);
							// if we make it here, we successfully called the method
							invoked = true;
						}
						catch (IllegalAccessException e) {
							throw new PlaidIllegalAccessException("Cannot call method : " + name);
						}
					} else {
						match = newMatch;
					}
				}
			}
			
			// walk back up the inheritance hierarchy, trying to call the method until 
			// we get to a public class, which either will succeed or not have the method
			if (!invoked) {
				Class<?> currClass = this.instanceClass;
				while (result == null) {
					MethodMatch newMatch = null;
					try {
						newMatch = this.getMethodHandle(this.name, currClass, paramTypes, null);
						if (newMatch == null) {
							// no potential methods found at this point in the hierarchy, so
							// we will not find any more higher up the hierarchy. Break out and
							// attempt to call the best match we have, if we have one.
							break;
						}
						else if (newMatch.compat.isExact()) {
							result = newMatch.method.invoke(instance, params);
							// if we make it here, we successfully called the method
							invoked = true;
							break;
						}
						else if (match == null || !match.isBetterThan(newMatch)) {
							// if the new match is at least equal to the current match
							// we prefer it as methods from higher up the inheritance
							// hierarchy are more likely to be accessible.
							match = newMatch;
							currClass = currClass.getSuperclass();
						} else {
							currClass = currClass.getSuperclass();
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
				
				// if we found a handle but required primitive widening / narrowing, call it now
				if (!invoked && match != null) {
					try {
						Object[] convertedParams = match.compat.convertParams(params);
						result = match.method.invoke(instance, convertedParams);
					} catch (IllegalArgumentException e) {
						throw new PlaidIllegalAccessException("Cannot call method : " + name + " - one of more arguments could not be safely narrowed");
					} catch (IllegalAccessException e) {
						throw new PlaidIllegalAccessException("Cannot call method : " + name);
					}
				}
				else if (!invoked && match == null) {
					throw new PlaidException("Cannot find method: " + name);
				}
			}
			
			if ( result == null ) {
				return PlaidRuntime.getRuntime().getClassLoader().unit();
			} else {
				PlaidObject plaidResult = getPlaidObjectRep(result);
				// if we didn't convert this to a pure Plaid object, then add tags to it
				// to allow for pattern matching
				if (plaidResult instanceof PlaidJavaObject) {
					plaidResult.addTag(new PlaidTagMap(result.getClass().getName(), javaObjectState.getPackage().toString(), null),null); //TODO: will we have this tag - do we need a super tag?
				}
				return plaidResult;
				
			}
		} catch (IllegalArgumentException e) {
			throw new PlaidInvalidArgumentException("Cannot call method : " + name);
		} catch (InvocationTargetException e) {
			if ( e.getCause() != null ) {
				throw new PlaidIllegalAccessException("Method '" + name + "' threw an exception: " + e.getCause(), e.getCause());
			} else {
				throw new PlaidIllegalAccessException("Cannot call method : " + name);
			}
		}
	}

	private Object[] extractArguments(PlaidObject args) {
		if ( args instanceof PlaidJavaObject ) {
			return new Object[] { ((PlaidJavaObject)args).getJavaObject() };
		} else if( args == Util.unit() )  {
			return new Object[0];
		} else {
			// check for pair
			return Util.convertParamsToArray(args);
		}
	}

	private Class[] extractArgumentTypes(Object[] args) {
		Class[] types = new Class[args.length];
		for(int i=0; i < args.length; i++) {
			types[i] = args[i].getClass();
		}
		return types;
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
		Class resultType = result.getClass();

		if (Util.isOfSupportedBasicType(result)) {
			return Util.buildBasic(result);
		}
		else if (resultType.equals(Boolean.class)) {
			if ((Boolean)result) {
				return Util.trueObject();
			}
			else {
				return Util.falseObject();
			}
		}
		else if (resultType.equals(String.class)) {
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

	class MethodMatch {
		Method method;
		ArgumentCompatibility compat;
		
		public MethodMatch(Method method, ArgumentCompatibility compat) {
			this.method = method;
			this.compat = compat;
		}
		
		public boolean isBetterThan(MethodMatch other) {
			if(other == null) {
				return true;
			}
			
			return compat.isBetterMatchThan(other.compat);
		}
	}
	
	private MethodMatch getMethodHandle(String methodName, Class<?> currClass, Class<?>[] paramTypes, MethodMatch previousMatch) {
		MethodMatch currentMatch = previousMatch;
		try {
			// iterate through all methods, looking for those that have the right number of parameters,
			// and parameter types that match paramTypes most closely
			for ( Method m : currClass.getMethods()) {
				if ( !m.getName().equals(methodName) ) {
					continue;
				}
				
				ArgumentCompatibility compat = ArgumentCompatibility.check(m.getParameterTypes(), paramTypes);
				if(compat == null) {
					continue;
				}
				
				MethodMatch potentialMatch = new MethodMatch(m, compat);
				
				// if this is the first match we've seen, then record it
				if (currentMatch == null) {
					currentMatch = potentialMatch;
				} else if(potentialMatch.isBetterThan(currentMatch)) {
					currentMatch = potentialMatch;
				}
			}
		} catch (SecurityException e) {
			throw new PlaidIllegalAccessException("Cannot find method : " + name);
		}

		return currentMatch;
	}
	
	@Override
	public String toString() {
		return "PlaidJavaMethod("+ name +")";
	}
}
