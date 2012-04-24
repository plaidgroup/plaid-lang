package plaid.fastruntime;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import plaid.fastruntime.PlaidJavaObject.JavaPrimitive;
import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.InterfaceGenerator;
import plaid.fastruntime.dcg.JavaDispatchGenerator;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;
import plaid.fastruntime.reference.SimplePlaidState;
import plaid.lang.False;
import plaid.lang.Float64;
import plaid.lang.Integer32;
import plaid.lang.String;
import plaid.lang.True;
import plaid.lang.Unit;

public final class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
	static {
//		JAVA_GEN.preloadPlaidState(java.io.PrintStream.class, new DPrintStream$plaid());
//		JAVA_GEN.preloadPlaidState(java.lang.String.class, String.theState$plaid);
//		JAVA_GEN.preloadPlaidState(java.lang.Integer.class, Integer32.theState$plaid);
	}
	public static final InterfaceGenerator INTERFACE_GEN = new InterfaceGenerator();
	public static final DispatchGenerator DISPATCH_GEN = new DispatchGenerator();
	
	public static final PlaidState makeState(ObjectValue ov) {
		PlaidDispatch dispatch = DISPATCH_GEN.createStateInstance(ov);
		return SimplePlaidState.makeStaticallyDefinedState(dispatch);
	}
	
	public static final PlaidState makeDynState(ObjectValue ov, 
			Map<java.lang.String, PlaidLambda> memberDefinitions) {
		PlaidDispatch dispatch = DISPATCH_GEN.createStateInstance(ov);
		return SimplePlaidState.makeDynamicallyDefinedState(dispatch, memberDefinitions);
	}
	
	public static PlaidObject unit() {
		return Unit.UNIT_VALUE;
	}
	public static PlaidObject string(java.lang.String javaString) {
		return String.plaidString(javaString);
	}
	public static final PlaidObject bool(boolean javaBool) {
		if (javaBool) {
			return True.TRUE_VALUE;
		} else {
			return False.FALSE_VALUE;
		}
	}
	
	public static final PlaidObject integer(int javaInt) {
		return Integer32.plaidInteger(javaInt);
	}
	
	public static final PlaidObject float64(double javaDouble) {
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
	
	public static boolean paramMatch(Class<?>[] declaredParams, PlaidJavaObject[] runtimeArgs) {
		if ( declaredParams.length == runtimeArgs.length) {
			for (int i = 0; i < declaredParams.length; i++) {
				Class<?> paramClass = declaredParams[i];
				if (paramClass.isPrimitive()) { //convert to appropriate boxed type
					if (!runtimeArgs[i].canBePrimitive(PlaidJavaObject.JavaPrimitive.fromClass(paramClass))) return false;
				} else {
					if (!paramClass.isInstance(runtimeArgs[i].getJavaObject())) return false;
				}
			}
			return true;
		} else
			return false;
	}
	
	public static ClassCompare compareParameters(Class<?>[] compare, Class<?>[] against) {
		ClassCompare compareStatus = ClassCompare.SAME; 
		for (int k = 0; k < against.length && compareStatus != ClassCompare.INCOMP; k++) {
			ClassCompare relation = compare(compare[k],against[k]);
			
			switch (relation) {
			case INCOMP: compareStatus = ClassCompare.INCOMP; break;
			case SUB:
				switch (compareStatus) {
				case SUB: break;
				case SUP: compareStatus = ClassCompare.INCOMP; break;
				case SAME: compareStatus = relation; break;
				}; break;
			case SUP:
				switch (compareStatus) {
				case SUB: compareStatus = ClassCompare.INCOMP; break;
				case SUP: break;
				case SAME: compareStatus = relation; break;
				}; break;
			case SAME://don't change anything
				break;
			}
		
		}
		return compareStatus;
	}
	
	public static Object[] getArgumentArray(Class<?>[] paramTypes, PlaidJavaObject[] runtimeArgs) {
		Object[] javaParams = new Object[runtimeArgs.length];
		for (int i = 0; i < runtimeArgs.length; i++) {
			if (paramTypes[i].isPrimitive())
				javaParams[i] = runtimeArgs[i].asPrimitive(PlaidJavaObject.JavaPrimitive.fromClass(paramTypes[i]));
			else
				javaParams[i] = runtimeArgs[i].getJavaObject();
		}
		return javaParams;
	}
	
	public static Signature constructorSig(Constructor<?> c, int index) {
		return new Signature(c.getParameterTypes(), index);
	}
	
	public static Signature methodSig(Method m, int index) {
		return new Signature(m.getParameterTypes(), index);
	}
	
	public static Object overloadedConstructor(Class<?> newClass, PlaidJavaObject... params ) {
		List<Constructor<?>> candidates = new ArrayList<Constructor<?>>();
		List<Signature> sigs = new ArrayList<Signature>();
		
		//check which constructors with this number of arguments are applicable
		int count = 0;
		for (Constructor<?> c : newClass.getConstructors()) {
			if (paramMatch(c.getParameterTypes(),params)){
				candidates.add(c);
				sigs.add(constructorSig(c,count));
				count++;
			}
		}
		
		int mostSpecific = Signature.mostSpecificIndex(sigs);
		if (mostSpecific == -1) 
			throw new PlaidIllegalOperationException("Java constructor not available for provided arguments in class " + newClass.getName());
	
		Constructor<?> theConstructor = candidates.get(mostSpecific);
		
		
		Object[] javaParams = getArgumentArray(theConstructor.getParameterTypes(), params);
		
		try {
			return theConstructor.newInstance(javaParams);
		} catch (IllegalArgumentException e) {
			throw new PlaidIllegalOperationException("Java constructor not available for provided arguments in class " + newClass.getName() ,e.getCause());
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalOperationException("Java constructor not available for provided arguments in class " + newClass.getName() ,e.getCause());
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalOperationException("Java constructor not available for provided arguments in class " + newClass.getName() ,e.getCause());
		} catch (InstantiationException e) {
			throw new PlaidIllegalOperationException("Java constructor not available for provided arguments in class " + newClass.getName() ,e.getCause());
		}
		
		
	}
	
	public static Object overloadedInstanceMethod(Object receiver, java.lang.String mName, PlaidJavaObject... params) {
		Class<?> receiverClass = receiver.getClass();
		
		List<Method> candidates = new ArrayList<Method>();
		List<Signature> sigs = new ArrayList<Signature>();
		
		
		//check which non-static methods with this name and number of arguments are applicable
		int count = 0;
		for (Method m : receiverClass.getMethods()) {
			if (m.getName().equals(mName) && paramMatch(m.getParameterTypes(),params)){
				candidates.add(m);
				sigs.add(methodSig(m,count));
				count++;
			}
		}
		
		int mostSpecific = Signature.mostSpecificIndex(sigs);
		if (mostSpecific == -1) 
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName());
		
		Method theMethod = candidates.get(mostSpecific);
		
		Object[] javaParams = getArgumentArray(theMethod.getParameterTypes(), params);
		
		try {
			return theMethod.invoke(receiver, javaParams);
		} catch (IllegalArgumentException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		}
		
		
	}
	
	public static Object overloadedStaticMethod(Class<?> theClass, java.lang.String mName, PlaidJavaObject... params) {
		
		List<Method> candidates = new ArrayList<Method>();
		List<Signature> sigs = new ArrayList<Signature>();
		
		
		//check which non-static methods with this name and number of arguments are applicable
		int count = 0;
		for (Method m : theClass.getMethods()) {
			if (m.getName().equals(mName) && paramMatch(m.getParameterTypes(),params) && Modifier.isStatic(m.getModifiers())){
				candidates.add(m);
				sigs.add(methodSig(m,count));
				count++;
			}
		}
		
		int mostSpecific = Signature.mostSpecificIndex(sigs);
		if (mostSpecific == -1) 
			throw new PlaidIllegalOperationException("Java static method " + mName + " not available for provided arguments in class " + theClass.getName());
		
		Method theMethod = candidates.get(mostSpecific);
		
		Object[] javaParams = getArgumentArray(theMethod.getParameterTypes(), params);
		
		try {
			return theMethod.invoke(null, javaParams);
		} catch (IllegalArgumentException e) {
			throw new PlaidIllegalOperationException("Java static method " + mName + " not available for provided arguments in class " + theClass.getName() ,e.getCause());
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalOperationException("Java static method " + mName + " not available for provided arguments in class " + theClass.getName() ,e.getCause());
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalOperationException("Java static method " + mName + " not available for provided arguments in class " + theClass.getName() ,e.getCause());
		}
		
		
	}
	
	
	private static class Signature {
		
		private Class<?>[] parameters;
		private int index;
		
		public Signature(Class<?>[] parameters, int index) {
			this.parameters = parameters;
			this.index = index;	
		}
		
		public ClassCompare compare(Signature other) {
			return compareParameters(other.parameters, this.parameters);
		}
		
		public ClassCompare compareToList(List<Signature> others) {
			for (int j = 0; j < others.size(); j++ ) { //check again each element in the most specific list
				Signature checkAgainst = others.get(j);
				
				// 0 = undecided, 1 = toCheck potentially more specific, 2 = checkAgainst potentially more specific, -1 = ambiguous
				ClassCompare toCheckStatus = compareParameters(this.parameters, checkAgainst.parameters);
				
				switch (toCheckStatus) {
				case SUP:
				case SAME: //less specific or the same as existing method, discard
					return ClassCompare.SUP;
				case INCOMP: //need to find a method more specific than this one
					return ClassCompare.INCOMP;
				case SUB: break; //more specific - wait to see if more specific than all in mostSpecific
				}
			}
			return ClassCompare.SUB; //more specific than all in the list
		}
		
		public static int mostSpecificIndex(List<Signature> sigs) {
			int numSigs = sigs.size();
			if (numSigs == 0) return -1;
			else if (numSigs== 1) return 0;
			else {
				List<Signature> currentSpecificSigs = new ArrayList<Signature>();
				currentSpecificSigs.add(sigs.get(0));
				for (int i = 1; i < numSigs; i++) {
					Signature toCheck = sigs.get(i);
					ClassCompare comp = toCheck.compareToList(currentSpecificSigs);
					switch (comp) {
					case SUB: //more specific than all, set to currently most specific
						currentSpecificSigs.clear();
					case INCOMP: //incomparable to the list, need to find signature more specific than this one too
						currentSpecificSigs.add(toCheck);  
						break;
					//otherwise, discard this signature - same or less specific than method already in the list	
					}
				}
				
				int resultSize = currentSpecificSigs.size();
				if (resultSize == 1) return currentSpecificSigs.get(0).index;
				else return -1;
			}
		}
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
			return ClassCompare.neg(comparePrimitiveToReference(second, first));
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
	
	// For Plaid purposes, we consider boxed primitives to be more specific than their unboxed counterparts
	// primitives are incomparable to other classes
	private static ClassCompare comparePrimitiveToReference(Class<?> primitive, Class<?> obj) {
		
		//all are subtypes of object (since they are applicable the Plaid object can be used as the primitive
		//or an object)
		//all except boolean are subtypes of java.lang.Number
		if (obj.equals(Object.class) ||
			(!primitive.equals(boolean.class) && obj.equals(java.lang.Number.class)))
			return ClassCompare.SUB;
			
		
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
