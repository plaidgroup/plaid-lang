package plaid.fastruntime;

import plaid.fastruntime.dcg.DispatchGenerator;
import plaid.fastruntime.dcg.InterfaceGenerator;
import plaid.fastruntime.dcg.JavaDispatchGenerator;
import plaid.fastruntime.dcg.StorageGenerator;
import plaid.lang.Boolean$plaid;
import plaid.lang.DPrintStream$plaid;
import plaid.lang.Integer$plaid;
import plaid.lang.String$plaid;

public class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
	static {
		JAVA_GEN.preloadPlaidState(java.io.PrintStream.class, new DPrintStream$plaid());
		JAVA_GEN.preloadPlaidState(java.lang.String.class, new String$plaid());
		JAVA_GEN.preloadPlaidState(java.lang.Integer.class, new Integer$plaid());
//		JAVA_GEN.preloadPlaidState(plaid.lang.Boolean$plaid.True.class, new Boolean$plaid(null));
//		JAVA_GEN.preloadPlaidState(plaid.lang.Boolean$plaid.False.class, new Boolean$plaid(null));
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
		return null;
	}
	
	public static PlaidObject integer(Integer javaInt) {
		return JAVA_GEN.createPlaidJavaObject(javaInt);
	}
	
	public static PlaidObject float64(Double javaDouble) {
		return JAVA_GEN.createPlaidJavaObject(javaDouble);
	}
}
