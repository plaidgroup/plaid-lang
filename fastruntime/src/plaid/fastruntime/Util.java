package plaid.fastruntime;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.dcg.*;
import plaid.lang.DPrintStream$plaid;
import plaid.lang.String$plaid;
import plaid.lang.Integer32$plaid;

public class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
	static {
		JAVA_GEN.preloadPlaidState(java.io.PrintStream.class, new DPrintStream$plaid(null));
		JAVA_GEN.preloadPlaidState(java.lang.String.class, new String$plaid(null));
		JAVA_GEN.preloadPlaidState(java.lang.Integer.class, new Integer32$plaid());
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
