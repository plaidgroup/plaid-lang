package plaid.fastruntime;

import plaid.fastlang.DPrintStream$plaid;
import plaid.fastlang.Pint$plaid;
import plaid.fastlang.String$plaid;
import plaid.fastlang.True$plaid;
import plaid.fastlang.False$plaid;
import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.InterfaceGenerator;
import plaid.fastruntime.dcg.JavaDispatchGenerator;
import plaid.fastruntime.dcg.StorageGenerator;

public class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
	static {
		JAVA_GEN.preloadPlaidState(java.io.PrintStream.class, new DPrintStream$plaid());
		JAVA_GEN.preloadPlaidState(java.lang.String.class, new String$plaid());
		JAVA_GEN.preloadPlaidState(java.lang.Integer.class, new Pint$plaid());
	}
	public static final InterfaceGenerator INTERFACE_GEN = new InterfaceGenerator();
	public static final StorageGenerator STORAGE_GEN = new StorageGenerator();
	public static final DispatchGenerator DISPATCH_GEN = new DispatchGenerator();
	
	public static PlaidObject unit() {
		return null;
	}
	public static PlaidObject string(String javaString) {
		return JAVA_GEN.createPlaidJavaObject(javaString);
	}
	public static PlaidObject bool(Boolean javaBool) {
		if (javaBool) {
			return True$plaid.TRUE_VALUE;
		} else {
			return False$plaid.FALSE_VALUE;
		}
	}
	
	public static PlaidObject integer(Integer javaInt) {
		return JAVA_GEN.createPlaidJavaObject(javaInt);
	}
	
	public static PlaidObject float64(Double javaDouble) {
		return JAVA_GEN.createPlaidJavaObject(javaDouble);
	}
}
