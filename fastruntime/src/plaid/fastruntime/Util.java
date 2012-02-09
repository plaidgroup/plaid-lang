package plaid.fastruntime;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.dcg.*;

public class Util {
	
	public static final JavaDispatchGenerator JAVA_GEN = new JavaDispatchGenerator();
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
