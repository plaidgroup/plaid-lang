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
				if (newMatch != match && newMatch.isExact()) {
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
					if (newMatch != match && newMatch.isExact()) {
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
						else if (newMatch.isExact()) {
							result = newMatch.method.invoke(instance, params);
							// if we make it here, we successfully called the method
							invoked = true;
							break;
						}
						else if (isBetterMatch(newMatch, match)) {
							match = newMatch;
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
						Object[] convertedParams = match.convertParams(params);
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
		Method method = null;
		boolean requiredWidening = false;
		boolean requiredNarrowing = false;
		boolean[] paramWidened = null;
		boolean[] paramNarrowed = null;

		public MethodMatch(Method method, boolean requiredWidening, boolean requiredNarrowing, boolean[] paramWidened, boolean[] paramNarrowed) {
			this.method = method;
			this.requiredWidening = requiredWidening;
			this.requiredNarrowing = requiredNarrowing;
			this.paramWidened = paramWidened;
			this.paramNarrowed = paramNarrowed;
		}

		public boolean isExact() {
			return !requiredWidening && !requiredNarrowing;
		}

		public Object[] convertParams(Object[] params) {
			Object[] converted = new Object[params.length];
			for(int i=0; i < params.length; i++) {
				if(paramNarrowed[i]) {
					converted[i] = narrowTo(method.getParameterTypes()[i], params[i]);
				} else {
					converted[i] = params[i];
				}
			}

			return converted;
		}

		private Object narrowTo(Class<?> targetType, Object instance) {
			Class<?> targetObjectType = Util.convertPrimitiveTypes(targetType);
			if(instance instanceof BigInteger) {
				BigInteger bigInt = (BigInteger)instance;
				Object converted = null;
				if(targetObjectType == Byte.class && !outOfBounds(bigInt, Byte.MIN_VALUE, Byte.MAX_VALUE)) {
					converted = bigInt.byteValue();
				} else if (targetObjectType == Short.class && !outOfBounds(bigInt, Short.MIN_VALUE, Short.MAX_VALUE)) {
					converted = bigInt.shortValue();
				} else if (targetObjectType == Integer.class && !outOfBounds(bigInt, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
					converted = bigInt.intValue();
				} else if (targetObjectType == Long.class && !outOfBounds(bigInt, Long.MIN_VALUE, Long.MAX_VALUE)) {
					converted = bigInt.longValue();
				}
				
				if(converted != null) {
					return converted;
				}
			} else if(instance instanceof BigRational) {
				// XXX: big rational conversion to floats is very imprecise for very big / small numbers, as if
				// the number cannot be represented by a double it will be returned as 0, +inf, or -inf. Not sure
				// what could be done about this.
				BigRational bigRat = (BigRational)instance;
				if(targetObjectType == Float.class) {
					return bigRat.floatValue();
				} else if(targetObjectType == Double.class) {
					return bigRat.doubleValue();
				}
			}
			
			throw new IllegalArgumentException("Argument " + instance + " could not be narrowed into type " + targetType.getName());
		}

		private boolean outOfBounds(BigInteger x, long min, long max) {
			return x.compareTo(BigInteger.valueOf(min)) < 0 // x < min
			    || x.compareTo(BigInteger.valueOf(max)) > 0; // x > max
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

				Class<?>[] mpTypes = m.getParameterTypes();
				if ( mpTypes.length != paramTypes.length ) {
					continue;
				}

				boolean match = true;
				boolean wideningRequired = false;
				boolean narrowingRequired = false;
				boolean[] paramWidened = new boolean[mpTypes.length];
				boolean[] paramNarrowed = new boolean[mpTypes.length];

				for (int i = 0; i < mpTypes.length; i++) {
					Class<?> mpType = Util.convertPrimitiveTypes(mpTypes[i]);
					Class<?> paramType = paramTypes[i];
					
					Class<?> mpTypeAsPrimitive = Util.convertToPrimitive(mpTypes[i]);
					Class<?> paramTypeAsPrimitive = Util.convertToPrimitive(paramTypes[i]);
					
					// perform primitive widening / narrowing if necessary and sensible
					Class<?> widenedParamType = Util.widenPrimitiveType(paramTypeAsPrimitive, mpTypeAsPrimitive);
					Class<?> narrowedParamType = Util.narrowPrimitiveType(paramTypeAsPrimitive, mpTypeAsPrimitive);
					if (widenedParamType != null) {
						wideningRequired = true;
						paramWidened[i] = true;
						paramType = Util.convertPrimitiveTypes(widenedParamType);
					} else if(narrowedParamType != null) {
						narrowingRequired = true;
						paramNarrowed[i] = true;
						paramType = Util.convertPrimitiveTypes(narrowedParamType);
					} else {
						paramType = Util.convertPrimitiveTypes(paramTypes[i]);
					}
					
					if ( !mpType.isAssignableFrom(paramType) ) {
						match = false;
						break;
					}
				}

				MethodMatch potentialMatch = new MethodMatch(m, wideningRequired, narrowingRequired, paramWidened, paramNarrowed);
				
				// if this is the first match we've seen, then record it
				if (match && currentMatch == null) {
					currentMatch = potentialMatch;
				}
				else if (match && currentMatch != null) {
					boolean betterMatch = 
						 // case 1: this new match does not require any widening or narrowing,
						 // and the previous did so this is a better match
						 (potentialMatch.isExact() && !currentMatch.isExact())
						 // case 2: this new match does not require any narrowing and the
						 // previous match did. Narrowing is inherently unsafe, so matches
						 // which avoid it are better
					  || (currentMatch.requiredNarrowing && !potentialMatch.requiredNarrowing)
					  	 // case 3: Test to see which is a closer match on a parameter by parameter
					  	 // basis.
					  || isBetterMatch(potentialMatch, currentMatch);
					
					if(betterMatch) {
						// the previous match required conversion, this match does not
						// so it is a better match
						currentMatch = potentialMatch;
					}
				}
			}
		} catch (SecurityException e) {
			throw new PlaidIllegalAccessException("Cannot find method : " + name);
		}

		return currentMatch;
	}

	private boolean isBetterMatch(MethodMatch newMatch, MethodMatch oldMatch) {
		if(oldMatch == null) {
			return true;
		}

		Class<?>[] newMatchParams = newMatch.method.getParameterTypes();
		Class<?>[] oldMatchParams = oldMatch.method.getParameterTypes();
		int numParams = newMatchParams.length;

		int exactMatchDelta = 0;
		int wideningPreferredDelta = 0;
		int closerNarrowingDelta = 0;
		int closerWideningDelta = 0;
		for(int i=0; i < numParams; i++) {
			boolean newIsExact = !newMatch.paramWidened[i] && !newMatch.paramNarrowed[i];
			boolean oldIsExact = !oldMatch.paramWidened[i] && !oldMatch.paramNarrowed[i];

			if(newMatchParams[i] == oldMatchParams[i]) {
				continue;
			}
			
			if(newIsExact && !oldIsExact) {
				exactMatchDelta++;
			} else if(!newIsExact && oldIsExact) {
				exactMatchDelta--;
			} else if(newMatch.paramWidened[i] && oldMatch.paramNarrowed[i]) {
				// the new match widens this parameter while the old narrows it, we much
				// prefer widening to narrowing so this is a better choice
				wideningPreferredDelta++;
			} else if(newMatch.paramNarrowed[i] && oldMatch.paramWidened[i]) {
				// the new match narrows this parameter while the old widens it, we much
				// prefer widening to narrowing so this is a worse choice
				wideningPreferredDelta--;
			} else if(newMatch.paramWidened[i] && oldMatch.paramWidened[i]) {
				// both required widening, but if the new match requires less widening
				// i.e. byte -> short instead of byte -> int
				// then this is a better choice for this parameter
				closerWideningDelta += isNarrowerType(newMatchParams[i], oldMatchParams[i]) ? 1 : -1;
			} else if(newMatch.paramNarrowed[i] && oldMatch.paramNarrowed[i]) {
				// both required narrowing, but if the new match requires less narrowing
				// i.e. BigInteger -> long instead of BigInteger -> short
				// then this is a better choice for this parameter
				closerNarrowingDelta += isNarrowerType(oldMatchParams[i], newMatchParams[i]) ? 1 : -1;
			}
		}
		
		// the overall decision is made based on the new match having more exact matches,
		return exactMatchDelta > 0 
		// if not then more cases where widening is used instead of narrowing,
			|| wideningPreferredDelta > 0 
		// if not then more cases where the narrowing matches are better,
			|| closerNarrowingDelta > 0
		// if not then more cases where the widening matches are better
			|| closerWideningDelta > 0;
		// otherwise the methods are equivalent and we prefer the original match, as
		// an arbitrary choice
	}
	
	private boolean isNarrowerType(Class<?> a, Class<?> b) {
		a = Util.convertPrimitiveTypes(a);
		b = Util.convertPrimitiveTypes(b);
		if(b == Double.class) {
			return a == Float.class;
		} else if(b == Long.class) {
			return within(a, Byte.class, Short.class, Integer.class); 
		} else if(b == Integer.class) {
			return within(a, Byte.class, Short.class);
		} else if(b == Short.class) {
			return within(a, Byte.class);
		}
		
		return false;
	}
	
	private boolean within(Object a, Object... bs) {
		if(bs == null) {
			return false;
		}
		
		for(Object b : bs) {
			if(a == b) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "PlaidJavaMethod("+ name +")";
	}
}
