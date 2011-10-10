package plaid.fastruntime.dcg;


public class NamingConventions {
	
	public static final String GENERATED_INTERFACES_PKG = "plaid/generatedinterfaces";
	public static final String GENERATED_INTEFACES_PREFIX = "I";
	public static final String GENERATED_IDENTIFIER_SUFFIX = "$plaid";
	public static final String GENERATED_INTERFACES_DIR = "../generatedinterfaces/bin/" + GENERATED_INTERFACES_PKG;
	
	public static final String PLAID_OBJECT_DESCRIPTOR = "Lplaid/fastruntime/PlaidObject;";
	
	public static final String getGeneratedInterfaceSimpleName(String method, int numArgs) {
		return GENERATED_INTEFACES_PREFIX + 
		method + "$" + numArgs +
		GENERATED_IDENTIFIER_SUFFIX;
	}
	
	public static final String getGeneratedInterfaceInternalName(String method, int numArgs) {
		return GENERATED_INTERFACES_PKG + "/" + 
		getGeneratedInterfaceSimpleName(method, numArgs);
	}
	
	public static final String getGeneratedInterfaceName(String method, int numArgs) {
		return getGeneratedInterfaceInternalName(method, numArgs).replace('/', '.');
	}
	
	public static final String getGeneratedInterfaceFilePath(String method, int numArgs) {
		return GENERATED_INTERFACES_DIR + "/" + getGeneratedInterfaceSimpleName(method, numArgs) + ".class";
	}
	
	public static final String getIdentifierName(String sourceID) {
		return sourceID + "$plaid";
	}
	
	public static final String getMethodDescriptor(int numArgs) {
		StringBuilder sb = new StringBuilder("(");
		sb.append(PLAID_OBJECT_DESCRIPTOR); // receiver
		for(int i=0; i<numArgs; i++) {
			sb.append(PLAID_OBJECT_DESCRIPTOR); //args
		}
		sb.append(")");
		sb.append(PLAID_OBJECT_DESCRIPTOR); // return
		return sb.toString();
	}
}
