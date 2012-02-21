package plaid.fastruntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.InterfaceGenerator;
import plaid.fastruntime.dcg.JavaDispatchGenerator;
import plaid.fastruntime.dcg.StorageGenerator;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.lang.DPrintStream$plaid;
import plaid.lang.False;
import plaid.lang.Float64;
import plaid.lang.Integer32;
import plaid.lang.String;
import plaid.lang.True;
import plaid.lang.Unit;

public class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
	static {
		JAVA_GEN.preloadPlaidState(java.io.PrintStream.class, new DPrintStream$plaid());
		JAVA_GEN.preloadPlaidState(java.lang.String.class, String.theState$plaid);
		JAVA_GEN.preloadPlaidState(java.lang.Integer.class, Integer32.theState$plaid);
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
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object... args) {
		Class<?> receiverClass = receiver.getClass();
		
		List<Method> candidates = new ArrayList<Method>();
		Method candidate = null;
		
		for (Method m : receiverClass.getMethods()) {
			if (m.getName().equals(mName)){
				Class<?>[] mArgTypes = m.getParameterTypes();
				if ( mArgTypes.length == args.length) {
					boolean match = true;
					for (int i = 0; i < mArgTypes.length && match; i++) {
						Class<?> argType = mArgTypes[i];
						if (argType.isPrimitive()) { //convert to appropriate boxed type
							
							if (argType.equals(boolean.class)) {
								match &= args[i].getClass().equals(Boolean.class);
							} else if (argType.equals(int.class) ||  //can pass plaid integers into int and long methods
									   argType.equals(long.class) ) {
								match &= args[i].getClass().equals(Integer.class);
							} else if (argType.equals(double.class)) {
								match &= (args[i].getClass().equals(Double.class) || args[i].getClass().equals(Integer.class));  //can pass plaid Float64 or Integers into double methods
							} else {
								match = false;
							}
							
						} else {
							match &= argType.isInstance(args[i]);
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
					int toCheckStatus = 0; 
					for (int k = 0; k < checkAgainstParams.length && toCheckStatus >= 0; k++) {
						Class<?> caParam = checkAgainstParams[k];
						Class<?> tcParam = toCheckParams[k];
						
						if (caParam.isPrimitive() && tcParam.isPrimitive()) { //special treatment of primitives
							//Java doesn't have to choose between these things, but we do because we cannot do compile time boxing/unboxing
							if (!caParam.equals(tcParam)) { //if they are equal, we won't change anything
								//enumerate valid pairs
								if (caParam.equals(boolean.class)) {
									toCheckStatus = -1;//other one is not boolean, so ambiguous
								} else if (caParam.equals(int.class)) {
									if (tcParam.equals(long.class) || tcParam.equals(double.class)) {  //treat long and double as less specific than int
										if (toCheckStatus == 0) toCheckStatus = 2;
										else if (toCheckStatus == 1) toCheckStatus = -1;
									} else
										toCheckStatus = -1;
								} else if (caParam.equals(long.class)) {
									if (tcParam.equals(int.class)) { //treat int as more specific than long
										if (toCheckStatus == 0) toCheckStatus = 1;
										else if (toCheckStatus == 2) toCheckStatus = -1;
									} else if (tcParam.equals(double.class)) { //treat double as less specific than long
										if (toCheckStatus == 0) toCheckStatus = 2;
										else if (toCheckStatus == 1) toCheckStatus = -1;
									} else
										toCheckStatus = -1;
								} else if (caParam.equals(double.class)) {
									if (tcParam.equals(long.class) || tcParam.equals(int.class)) {  //treat long and int as more specific than double
										if (toCheckStatus == 0) toCheckStatus = 1;
										else if (toCheckStatus == 2) toCheckStatus = -1;
									} else
										toCheckStatus = -1;
								} else {
									toCheckStatus = -1;  //unhandled primitive type
								}
							}
						} else if (caParam.isPrimitive()) {  
							if (caParam.equals(boolean.class) && !tcParam.equals(Boolean.class)) { //either ok if both booleans, otherwise ambiguous
								toCheckStatus = -1;
							} else if (caParam.equals(int.class)) {
								if (tcParam.equals(Long.class) || tcParam.equals(Double.class)) {  //treat int as more specific than long and double
									if (toCheckStatus == 0) toCheckStatus = 2;
									else if (toCheckStatus == 1) toCheckStatus = -1;
								} else if (!tcParam.equals(Integer.class)) { //either ok if Integer, otherwise ambiguous
									toCheckStatus = -1;
								}
							} else if (caParam.equals(long.class)) {
								if (tcParam.equals(Integer.class)) { //treat int as more specific than long
									if (toCheckStatus == 0) toCheckStatus = 1;
									else if (toCheckStatus == 2) toCheckStatus = -1;
								} else if (tcParam.equals(Double.class)) { //treat double as less specific than long
									if (toCheckStatus == 0) toCheckStatus = 2;
									else if (toCheckStatus == 1) toCheckStatus = -1;
								} else if (!tcParam.equals(Long.class))
									toCheckStatus = -1;
							} else if (caParam.equals(double.class)) {
								if (tcParam.equals(Long.class) || tcParam.equals(Integer.class)) {  //treat double as less specific than long and int
									if (toCheckStatus == 0) toCheckStatus = 1;
									else if (toCheckStatus == 2) toCheckStatus = -1;
								} else if (!tcParam.equals(Double.class)) { //either ok if Double, otherwise ambiguous
									toCheckStatus = -1;
								}
							} else {
								toCheckStatus = -1;  //unhandled primitive type
							}
							
						} else if (tcParam.isPrimitive()) {
							if (tcParam.equals(boolean.class) && !caParam.equals(Boolean.class)) { //either ok if both booleans, otherwise ambiguous
								toCheckStatus = -1;
							} else if (tcParam.equals(int.class)) {
								if (caParam.equals(Long.class) || caParam.equals(Double.class)) {  //treat int as more specific than long and double
									if (toCheckStatus == 0) toCheckStatus = 1;
									else if (toCheckStatus == 2) toCheckStatus = -1;
								} else if (!caParam.equals(Integer.class)) { //either ok if Integer, otherwise ambiguous
									toCheckStatus = -1;
								}
							} else if (tcParam.equals(long.class)) {
								if (caParam.equals(Integer.class)) { //treat int as more specific than long
									if (toCheckStatus == 0) toCheckStatus = 2;
									else if (toCheckStatus == 1) toCheckStatus = -1;
								} else if (caParam.equals(Double.class)) { //treat double as less specific than long
									if (toCheckStatus == 0) toCheckStatus = 1;
									else if (toCheckStatus == 2) toCheckStatus = -1;
								} else if (!caParam.equals(Long.class))
									toCheckStatus = -1;
							} else if (tcParam.equals(double.class)) {
								if (caParam.equals(Long.class) || caParam.equals(Integer.class)) {  //treat double as less specific than long and int
									if (toCheckStatus == 0) toCheckStatus = 2;
									else if (toCheckStatus == 1) toCheckStatus = -1;
								} else if (!caParam.equals(Double.class)) { //either ok if Double, otherwise ambiguous
									toCheckStatus = -1;
								}
							} else {
								toCheckStatus = -1;  //unhandled primitive type
							}
							
						} else { 
							
							if (toCheckStatus == 0) {
								if (caParam.isAssignableFrom(toCheckParams[k])) {  				 //tcp[k] <: cap[k] 
									if (!tcParam.isAssignableFrom(caParam)) toCheckStatus=1; 	 // NOT(cap[k] <: tcp[k]), so tocheck potentially more specific
																							     //else types equivalent, still undecided		
								} else if (tcParam.isAssignableFrom(caParam)) toCheckStatus=2;   //cap[k] <: tcp[k] so checkAgainst potentially more specific
								else toCheckStatus=-1; 											 // incomparable, so therefore ambiguous
							} else if (toCheckStatus == 1) {
								if (!caParam.isAssignableFrom(tcParam)) toCheckStatus=-1;  		 //if not tcp[k] <: cap[k], ambiguous
								
							} else { //toCheckStatus==2
								 if (!tcParam.isAssignableFrom(caParam)) toCheckStatus=-1; 		 //if not cap[k] <: tcp[k], ambiguous
							}
						}
					}
					
					if (toCheckStatus == 2 || toCheckStatus == 0) done = true;  //discard this method, it is less specific than (or the same as?) an existing candidate
					else if (toCheckStatus == -1) {
						done = true;
						mostSpecific.add(toCheck);
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
		

		try {
			return candidate.invoke(receiver, args);
		} catch (IllegalArgumentException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available for provided arguments in class " + receiverClass.getName() ,e.getCause());
		}
		
		
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object arg) {
		return staticOverloadingCall(receiver, mName, new Object[] { arg });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object arg1, Object arg2) {
		return staticOverloadingCall(receiver, mName, new Object[] { arg1, arg2 });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object arg1, Object arg2, Object arg3) {
		return staticOverloadingCall(receiver, mName, new Object[] { arg1, arg2, arg3 });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object arg1, Object arg2, Object arg3, Object arg4) {
		return staticOverloadingCall(receiver, mName, new Object[] { arg1, arg2, arg3, arg4 });
	}

	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
		return staticOverloadingCall(receiver, mName, new Object[] { arg1, arg2, arg3, arg4, arg5 });
	}
	
	public static Object staticOverloadingCall(Object receiver, java.lang.String mName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
		return staticOverloadingCall(receiver, mName, new Object[] { arg1, arg2, arg3, arg4, arg5, arg6 });
	}
	
}
