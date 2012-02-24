package plaid.fastruntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import plaid.fastruntime.PlaidJavaObject.JavaPrimitive;
import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.InterfaceGenerator;
import plaid.fastruntime.dcg.JavaDispatchGenerator;
import plaid.fastruntime.dcg.StorageGenerator;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;
import plaid.lang.False;
import plaid.lang.Float64;
import plaid.lang.Integer32;
import plaid.lang.String;
import plaid.lang.True;
import plaid.lang.Unit;

public class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
	static {
//		JAVA_GEN.preloadPlaidState(java.io.PrintStream.class, new DPrintStream$plaid());
//		JAVA_GEN.preloadPlaidState(java.lang.String.class, String.theState$plaid);
//		JAVA_GEN.preloadPlaidState(java.lang.Integer.class, Integer32.theState$plaid);
	}
	public static final InterfaceGenerator INTERFACE_GEN = new InterfaceGenerator();
	public static final StorageGenerator STORAGE_GEN = new StorageGenerator();
	public static final DispatchGenerator DISPATCH_GEN = new DispatchGenerator();
	
	public static PlaidObject unit() {
		return Unit.UNIT_VALUE;
	}
	public static PlaidObject string(java.lang.String javaString) {
		return String.plaidString(javaString);
	}
	public static PlaidObject bool(Boolean javaBool) {
		if (javaBool) {
			return True.TRUE_VALUE;
		} else {
			return False.FALSE_VALUE;
		}
	}
	
	public static PlaidObject integer(java.lang.Integer javaInt) {
		return Integer32.plaidInteger(javaInt);
	}
	
	public static PlaidObject float64(Double javaDouble) {
		return Float64.plaidFloat64(javaDouble);
	}
	
	public static PlaidObject javaToPlaid(Object javaObject) {
		if (javaObject == null) 
			return unit();
		if (javaObject instanceof Boolean)
			return bool((Boolean)javaObject);
		else if (javaObject instanceof Double)
			return float64((Double) javaObject);
		else if (javaObject instanceof java.lang.Integer)
			return integer((java.lang.Integer) javaObject);
		else if (javaObject instanceof java.lang.String)
			return string((java.lang.String) javaObject);
		else
			return JAVA_GEN.createPlaidJavaObject(javaObject);
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject... params) {
		Class<?> receiverClass = receiver.getClass();
		
		List<Method> candidates = new ArrayList<Method>();
		Method candidate = null;
		
		
		//check which methods with this name and number of arguments are applicable
		for (Method m : receiverClass.getMethods()) {
			if (m.getName().equals(mName)){
				Class<?>[] mArgClasses = m.getParameterTypes();
				if ( mArgClasses.length == params.length) {
					boolean match = true;
					for (int i = 0; i < mArgClasses.length && match; i++) {
						Class<?> argClass = mArgClasses[i];
						if (argClass.isPrimitive()) { //convert to appropriate boxed type
							if (!params[i].canBePrimitive(PlaidJavaObject.JavaPrimitive.fromClass(argClass))) match = false;
						} else {
							if (!argClass.isInstance(params[i].getJavaObject())) match = false;
						}
					}
					if (match) candidates.add(m);
				}
			}
		}
		
		int numCandidates = candidates.size();
		if (numCandidates == 0)
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName());
		else if (numCandidates == 1)
			candidate = candidates.get(0);
		else { // need to resolve which one is most specific or error if one does not exist
			
			//keep track of most specific methods at this point
			List<Method> mostSpecific = new ArrayList<Method>();
			Method seed = candidates.get(0); 
			mostSpecific.add(seed);
			
			
			for (int i = 1; i < numCandidates; i++) {
				Method toCheck = candidates.get(i);
				//check if this method is more specific than all of those in the current array
				//if it is, clear the array and add this one
				//if it is less specific than any of the members, then discard it
				//otherwise, add it
				Class<?>[] toCheckParams = toCheck.getParameterTypes();
				boolean done = false;
				
				for (int j = 0; j < mostSpecific.size() && !done; j++ ) { //check again each element in the most specific list
					Method checkAgainst = mostSpecific.get(j);
					Class<?>[] checkAgainstParams = checkAgainst.getParameterTypes();
					
					// 0 = undecided, 1 = toCheck potentially more specific, 2 = checkAgainst potentially more specific, -1 = ambiguous
					ClassCompare toCheckStatus = ClassCompare.SAME; 
					for (int k = 0; k < checkAgainstParams.length && toCheckStatus != ClassCompare.INCOMP; k++) {
						ClassCompare relation = compare(toCheckParams[k],checkAgainstParams[k]);
						
						switch (relation) {
						case INCOMP: toCheckStatus = ClassCompare.INCOMP; break;
						case SUB:
							switch (toCheckStatus) {
							case SUB: break;
							case SUP: toCheckStatus = ClassCompare.INCOMP; break;
							case SAME: toCheckStatus = relation; break;
							}; break;
						case SUP:
							switch (toCheckStatus) {
							case SUB: toCheckStatus = ClassCompare.INCOMP; break;
							case SUP: break;
							case SAME: toCheckStatus = relation; break;
							}; break;
						case SAME:
							toCheckStatus = relation; break;
						}
					}
					
					switch (toCheckStatus) {
					case SUP:
					case SAME: //less specific or the same as existing method, discard
						done = true; 
						break;
					case INCOMP: //need to find a method more specific than this one
						done = true;
						mostSpecific.add(toCheck);
						break;
					case SUB: break; //more specific - wait to see if more specific than all in mostSpecific
					}
				}
				
				if (!done) { //toCheck more specific than all so clear the array and add it
					mostSpecific.clear();
					mostSpecific.add(toCheck);
				}
			}
			
			if (mostSpecific.size() == 1) {
				candidate = mostSpecific.get(0);
			} else {
				throw new PlaidIllegalOperationException("Java method " + mName + " ambiguous arguments provided for static overloading");
			}
			
			
		}
		
		Object[] javaParams = new Object[params.length];
		Class<?>[] methodArgs = candidate.getParameterTypes();
		for (int i = 0; i < params.length; i++) {
			if (methodArgs[i].isPrimitive())
				javaParams[i] = params[i].asPrimitive(PlaidJavaObject.JavaPrimitive.fromClass(methodArgs[i]));
			else
				javaParams[i] = params[i].getJavaObject();
		}
		
		
		try {
			return candidate.invoke(receiver, javaParams);
		} catch (IllegalArgumentException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		}
		
		
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject arg) {
		return staticOverloadingCall(receiver, mName, new PlaidJavaObject[] { arg });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject arg1, PlaidJavaObject arg2) {
		return staticOverloadingCall(receiver, mName, new PlaidJavaObject[] { arg1, arg2 });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject arg1, PlaidJavaObject arg2, PlaidJavaObject arg3) {
		return staticOverloadingCall(receiver, mName, new PlaidJavaObject[] { arg1, arg2, arg3 });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject arg1, PlaidJavaObject arg2, PlaidJavaObject arg3, PlaidJavaObject arg4) {
		return staticOverloadingCall(receiver, mName, new PlaidJavaObject[] { arg1, arg2, arg3, arg4 });
	}

	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject arg1, PlaidJavaObject arg2, PlaidJavaObject arg3, PlaidJavaObject arg4, PlaidJavaObject arg5) {
		return staticOverloadingCall(receiver, mName, new PlaidJavaObject[] { arg1, arg2, arg3, arg4, arg5 });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, PlaidJavaObject arg1, PlaidJavaObject arg2, PlaidJavaObject arg3, PlaidJavaObject arg4, PlaidJavaObject arg5, PlaidJavaObject arg6) {
		return staticOverloadingCall(receiver, mName, new PlaidJavaObject[] { arg1, arg2, arg3, arg4, arg5, arg6 });
	}
	
	private enum ClassCompare {
		SAME,
		SUB,
		SUP,
		INCOMP;
		
		public static ClassCompare neg(ClassCompare c) {
			switch (c) {
			case SAME:
			case INCOMP: return c;
			case SUB: return SUP;
			case SUP : return SUB;
			}
			throw new PlaidInternalException("forgotten case for neg(ClassCompare)");
		}
	}
	
	//For normal reference types, follows the standard Java subtyping
	//For primitives and boxed primitive types:
	//  A SUB B if and only if B SUP A
	//  A SUB B and B SUB C if and only if A SUB C
	//  boolean SUB Boolean
	//  byte SUB Byte SUB short SUB Short SUB int SUB Integer SUB long SUB Long SUB float SUB Float SUB double SUB Double
	//  char SUB Character SUB short
	private static ClassCompare compare(Class<?> first, Class<?> second) {
		if (first.isPrimitive() && second.isPrimitive()) {
			return primitiveCompare(JavaPrimitive.fromClass(first), JavaPrimitive.fromClass(second));
		} else if (first.isPrimitive()) {
			return comparePrimitiveToReference(first, second);
		} else if (second.isPrimitive()) {
			return ClassCompare.neg(comparePrimitiveToReference(first, second));
		} else {
			if (first.isAssignableFrom(second)) { // is 'second SUB first'?
				if (second.isAssignableFrom(first)) // is 'first SUB second'?
					return ClassCompare.SAME;
				else return ClassCompare.SUP;
			} else if (second.isAssignableFrom(first)) // is 'first SUB second'?
				return ClassCompare.SUB;
			else
				return ClassCompare.INCOMP;
		}
	}
	
	// For Plaid purposes, we consider boxed primitives to less specific than their unboxed counterparts
	// primitives are incomparable to other classes
	private static ClassCompare comparePrimitiveToReference(Class<?> primitive, Class<?> obj) {
		switch (JavaPrimitive.fromClass(primitive)) {
		case BOOLEAN:
			if (java.lang.Boolean.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case CHAR:
			if (java.lang.Character.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case BYTE:
			if (java.lang.Byte.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case SHORT:	
			if (java.lang.Short.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case INT:
			if (java.lang.Integer.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case LONG:
			if (java.lang.Long.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case FLOAT:
			if (java.lang.Float.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		case DOUBLE:
			if (java.lang.Double.class.isAssignableFrom(obj)) return ClassCompare.SUP;
			else return ClassCompare.INCOMP;
		default:
			throw new PlaidInternalException("no match between primitives - forgotten case?");
		}
	}
	
	
	// following the Java Specification for Conversions (ch 5)
	// here we are dealing with method invocation conversions (5.3)
	// these allow only identity and widening conversions (5.1.1, 5.1.2)
	// thus we have the following relationships
	//   - A SAME A
	//	 - boolean INCOMP { byte, char, short, int, long, float, double }
	//	 - byte SUB { short, int, long, float, double}
	//	 - short SUB { int, long, float, double}
	//	 - char SUB { int, long, float, double}
	//	 - int SUB  { long, float, double }
	//	 - long SUB { float, double }
	//	 - float SUB double 
	private static ClassCompare primitiveCompare(JavaPrimitive p1, JavaPrimitive p2) {
		switch (p1) {
		case BOOLEAN:
			switch (p2) {
			case BOOLEAN: return ClassCompare.SAME;
			case BYTE:
			case SHORT:
			case CHAR:
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE: return ClassCompare.INCOMP;
			}
		case BYTE:
			switch (p2) {
			case BOOLEAN: 
			case CHAR:return ClassCompare.INCOMP;
			case BYTE: return ClassCompare.SAME;
			case SHORT:	
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE: return ClassCompare.SUB;
			}
		case SHORT:
			switch (p2) {
			case BOOLEAN: 
			case CHAR:return ClassCompare.INCOMP;
			case BYTE: return ClassCompare.SUP;
			case SHORT:	return ClassCompare.SAME;
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE: return ClassCompare.SUB;
			}
		case CHAR:
			switch (p2) {
			case BOOLEAN: 
			case BYTE: 
			case SHORT:	return ClassCompare.INCOMP;
			case CHAR:return ClassCompare.SAME;
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE: return ClassCompare.SUB;
			}
		case INT:
			switch (p2) {
			case BOOLEAN: return ClassCompare.INCOMP;
			case BYTE: 
			case SHORT:	
			case CHAR: return ClassCompare.SUP;
			case INT: return ClassCompare.SAME;
			case LONG:
			case FLOAT:
			case DOUBLE: return ClassCompare.SUB;
			}
		case LONG:
			switch (p2) {
			case BOOLEAN: return ClassCompare.INCOMP;
			case BYTE: 
			case SHORT:	
			case CHAR: 
			case INT: return ClassCompare.SUP;
			case LONG: return ClassCompare.SAME;
			case FLOAT:
			case DOUBLE: return ClassCompare.SUB;
			}
		case FLOAT:
			switch (p2) {
			case BOOLEAN: return ClassCompare.INCOMP;
			case BYTE: 
			case SHORT:	
			case CHAR: 
			case INT: 
			case LONG: return ClassCompare.SUP;
			case FLOAT: return ClassCompare.SAME;
			case DOUBLE: return ClassCompare.SUB;
			}
		case DOUBLE:
			switch (p2) {
			case BOOLEAN: return ClassCompare.INCOMP;
			case BYTE: 
			case SHORT:	
			case CHAR: 
			case INT: 
			case LONG: 
			case FLOAT: return ClassCompare.SUP;
			case DOUBLE: return ClassCompare.SAME;
			}
		default: throw new PlaidInternalException("no match between primitives - forgotten case?");
		}
	}
	
}
