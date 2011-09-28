package plaid.fastruntime.dcg;

public class NamingConventions {
	
	public static final String GENERATED_INTERFACES_PKG = "plaid/fastruntime/generatedinterfaces";
	public static final String GENERATED_INTEFACES_PREFIX = "I";
	public static final String GENERATED_IDENTIFIER_SUFFIX = "$plaid";
	public static final String GENERATED_INTERFACES_DIR = "src/" + GENERATED_INTERFACES_PKG;
	
	public static final String getGeneratedInterfaceSimpleName(String method) {
		return GENERATED_INTEFACES_PREFIX + 
		method + 
		GENERATED_IDENTIFIER_SUFFIX;
	}
	
	public static final String getGeneratedInterfaceInternalName(String method) {
		return GENERATED_INTERFACES_PKG + "/" + 
		getGeneratedInterfaceSimpleName(method);
	}
	
	public static final String getGeneratedInterfaceName(String method) {
		return getGeneratedInterfaceInternalName(method).replace('/', '.');
	}
	
	public static final String getGeneratedInterfaceFilePath(String method) {
		return GENERATED_INTERFACES_DIR + getGeneratedInterfaceSimpleName(method) + ".class";
	}
	
	public static final String getIdentifierName(String sourceID) {
		return sourceID + "$plaid";
	}
}
