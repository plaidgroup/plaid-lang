package plaid.runtime.models.map;

import java.math.BigInteger;

import plaid.runtime.BigRational;
import plaid.runtime.Util;

public class ArgumentCompatibility {
	Class<?>[] paramTypes;
	boolean requiredWidening = false;
	boolean requiredNarrowing = false;
	boolean[] paramWidened;
	boolean[] paramNarrowed;
	
	private ArgumentCompatibility(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
		this.paramWidened = new boolean[paramTypes.length];
		this.paramNarrowed = new boolean[paramTypes.length];
	}
	
	public boolean isExact() {
		return !requiredWidening && !requiredNarrowing;
	}
	
	public boolean isBetterMatchThan(ArgumentCompatibility otherMatch) {
		if(otherMatch == null) {
			return true;
		}
		
		// prefer exact matches to inexact matches. If both are exact, prefer
		// the old one.
		if(otherMatch.isExact()) {
			return false;
		} else if(this.isExact() && !otherMatch.isExact()) {
			return true;
		}
		
		// always prefer a match that does not use narrowing
		if(!this.requiredNarrowing && otherMatch.requiredNarrowing) {
			return true;
		} else if(this.requiredNarrowing && !otherMatch.requiredNarrowing) {
			return false;
		}

		Class<?>[] newMatchParams = this.paramTypes;
		Class<?>[] oldMatchParams = otherMatch.paramTypes;
		int numParams = newMatchParams.length;

		int exactMatchDelta = 0;
		int wideningPreferredDelta = 0;
		int closerNarrowingDelta = 0;
		int closerWideningDelta = 0;
		for(int i=0; i < numParams; i++) {
			boolean newIsExact = !this.paramWidened[i] && !this.paramNarrowed[i];
			boolean oldIsExact = !otherMatch.paramWidened[i] && !otherMatch.paramNarrowed[i];

			if(newMatchParams[i] == oldMatchParams[i]) {
				continue;
			}
			
			if(newIsExact && !oldIsExact) {
				exactMatchDelta++;
			} else if(!newIsExact && oldIsExact) {
				exactMatchDelta--;
			} else if(this.paramWidened[i] && otherMatch.paramNarrowed[i]) {
				// the new match widens this parameter while the old narrows it, we much
				// prefer widening to narrowing so this is a better choice
				wideningPreferredDelta++;
			} else if(this.paramNarrowed[i] && otherMatch.paramWidened[i]) {
				// the new match narrows this parameter while the old widens it, we much
				// prefer widening to narrowing so this is a worse choice
				wideningPreferredDelta--;
			} else if(this.paramWidened[i] && otherMatch.paramWidened[i]) {
				// both required widening, but if the new match requires less widening
				// i.e. byte -> short instead of byte -> int
				// then this is a better choice for this parameter
				closerWideningDelta += isNarrowerType(newMatchParams[i], oldMatchParams[i]) ? 1 : -1;
			} else if(this.paramNarrowed[i] && otherMatch.paramNarrowed[i]) {
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
	
	public Object[] convertParams(Object[] params) {
		Object[] converted = new Object[params.length];
		for(int i=0; i < params.length; i++) {
			if(paramNarrowed[i]) {
				converted[i] = narrowTo(paramTypes[i], params[i]);
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
	
	public static ArgumentCompatibility check(Class<?>[] paramTypes, Class<?>[] argTypes) {
		if(paramTypes.length != argTypes.length) {
			// the number of declared parameters and passed arguments are different, no match
			return null;
		}
		
		ArgumentCompatibility match = new ArgumentCompatibility(paramTypes);
		
		for (int i = 0; i < match.paramTypes.length; i++) {
			Class<?> paramType = Util.convertPrimitiveTypes(match.paramTypes[i]);
			Class<?> argType = argTypes[i];
			
			Class<?> paramPrimitiveType = Util.convertToPrimitive(match.paramTypes[i]);
			Class<?> argPrimitiveType = Util.convertToPrimitive(argType);
			
			// perform primitive widening / narrowing if necessary and sensible
			Class<?> widenedArgType = Util.widenPrimitiveType(argPrimitiveType, paramPrimitiveType);
			Class<?> narrowedArgType = Util.narrowPrimitiveType(argPrimitiveType, paramPrimitiveType);
			if (widenedArgType != null) {
				match.requiredWidening = true;
				match.paramWidened[i] = true;
				argType = Util.convertPrimitiveTypes(widenedArgType);
			} else if(narrowedArgType != null) {
				match.requiredNarrowing = true;
				match.paramNarrowed[i] = true;
				argType = Util.convertPrimitiveTypes(narrowedArgType);
			} else {
				argType = Util.convertPrimitiveTypes(argType);
			}
			
			if ( !paramType.isAssignableFrom(argType) ) {
				// declared parameter type and passed argument type are incompatible, no match
				return null;
			}
		}
		
		return match;
	}
}
