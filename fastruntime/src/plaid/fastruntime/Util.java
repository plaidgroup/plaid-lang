package plaid.fastruntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		Class<?>[] argClasses = new Class[args.length];
		for (int i = 0;i < args.length;i++) argClasses[i] = args[i].getClass();
		Class<?> receiverClass = receiver.getClass();
		
		try {
			Method toCall = receiverClass.getMethod(mName, argClasses);
			return toCall.invoke(receiver, args);
		} catch (SecurityException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available in class " + receiverClass.getName() ,e.getCause());
		} catch (NoSuchMethodException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " does not exists for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (IllegalArgumentException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " does not exists for provided arguments in class " + receiverClass.getName() ,e.getCause());
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " not available in class " + receiverClass.getName() ,e.getCause());
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalOperationException("Java method " + mName + " does not exists for provided arguments in class " + receiverClass.getName() ,e.getCause());
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
