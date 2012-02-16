package plaid.fastruntime;

import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.InterfaceGenerator;
import plaid.fastruntime.dcg.JavaDispatchGenerator;
import plaid.fastruntime.dcg.StorageGenerator;
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
	
}
