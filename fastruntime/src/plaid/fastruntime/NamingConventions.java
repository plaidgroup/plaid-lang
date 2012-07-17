package plaid.fastruntime;

import java.util.HashMap;

import fj.P2;
import fj.data.List;


public class NamingConventions {
	
	public static final String GENERATED_PKG = "plaid/generated";
	public static final String GENERATED_INTERFACES_PREFIX = "I";
	public static final String GENERATED_VARIABLE_PREFIX = "VaR";
	public static final String GENERATED_STORAGE_PREFIX = "S";
	public static final String GENERATED_SUFFIX = "$plaid";
	public static final String GENERATED_DIR = "../generated/bin/" + GENERATED_PKG;
	
	public static final String GENERATED_METHOD_NAME = "invoke" + GENERATED_SUFFIX;
	public static final String GENERATED_STATE_NAME = "theState" + GENERATED_SUFFIX;
	public static final String GENERATED_FIELD_NAME = "theField" + GENERATED_SUFFIX;
	
	public static final String GENERATED_THIS = "this" + GENERATED_SUFFIX;
	public static final String GENERATED_GET_PREFIX = "get";
	public static final String GENERATED_SET_PREFIX = "set";
	public static final String GENERATED_CONSTRUCTOR = "cstr" + GENERATED_SUFFIX;
	
	public static final String PLAID_OBJECT_INTERNAL_NAME = "plaid/fastruntime/PlaidObject";
	public static final String PLAID_OBJECT_DESCRIPTOR = "L" + PLAID_OBJECT_INTERNAL_NAME + ";";
	public static final String PLAID_JAVA_OBJECT_INTERNAL_NAME = "plaid/fastruntime/PlaidJavaObject";
	public static final String PLAID_JAVA_OBJECT_DESCRIPTOR = "L" + PLAID_JAVA_OBJECT_INTERNAL_NAME + ";";
	public static final String JAVA_OBJECT_INTERNAL_NAME = "java/lang/Object";
	public static final String JAVA_OBJECT_DESCRIPTOR = "L" + JAVA_OBJECT_INTERNAL_NAME + ";";
	public static final String JAVA_STRING_INTERNAL_NAME = "java/lang/String";
	public static final String JAVA_STRING_DESCRIPTOR = "L" + JAVA_STRING_INTERNAL_NAME + ";";
	public static final String JAVA_CLASS_INTERNAL_NAME = "java/lang/Class";
	public static final String JAVA_CLASS_DESCRIPTOR = "L" + JAVA_CLASS_INTERNAL_NAME + ";";
	
	public static final String OVERLOAD_INSTANCE_METHOD_DESC = getMethodDescriptor(JAVA_OBJECT_DESCRIPTOR, 
				JAVA_STRING_DESCRIPTOR, 
			   	1, "[" + PLAID_JAVA_OBJECT_DESCRIPTOR, 
			   	JAVA_OBJECT_DESCRIPTOR);

	public static final String OVERLOAD_CONSTRUCTOR_METHOD_DESC = getMethodDescriptor(JAVA_CLASS_DESCRIPTOR, 
				1, "[" + PLAID_JAVA_OBJECT_DESCRIPTOR, 
				JAVA_OBJECT_DESCRIPTOR);

	public static final String OVERLOAD_STATIC_METHOD_DESC = getMethodDescriptor(JAVA_CLASS_DESCRIPTOR, 
				JAVA_STRING_DESCRIPTOR, 
				1, "[" + PLAID_JAVA_OBJECT_DESCRIPTOR, 
				JAVA_OBJECT_DESCRIPTOR);	

	//precondition: mangledFieldName must have already been mangled by called getGeneratedIdentifier()
	public static final String getGetterName(String mangledFieldName) {
		return GENERATED_GET_PREFIX + mangledFieldName + GENERATED_SUFFIX;
	}
	
	//precondition: mangledFieldName must have already been mangled by called getGeneratedIdentifier()
	public static final String getSetterName(String mangledFieldName) {
		return GENERATED_SET_PREFIX + mangledFieldName + GENERATED_SUFFIX;
	}
	
	public static final String getGeneratedInterfaceSimpleName(String method, int numArgs) {
		return GENERATED_INTERFACES_PREFIX + 
		method + "$" + numArgs +
		GENERATED_SUFFIX;
	}
	
	
	public static final String getGeneratedStorageSimpleName(List<P2<Boolean, String>> fields) {
		StringBuilder storageName = new StringBuilder(GENERATED_STORAGE_PREFIX);
		for(P2<Boolean,String> field : fields) {
			if(field._1()) {
				storageName.append("t");
			} else {
				storageName.append("f");
			}
			storageName.append(field._2());
		}
		storageName.append(GENERATED_SUFFIX);
		return storageName.toString();
	}
	
	public static final String getGeneratedMemberName(MemberInfo mi) {
		return  getGeneratedIdentifier(mi.getName());
	}
	
	public static final String getGeneratedInternal(MemberInfo mi) {
		return  getInternalFQN(mi.getStaticClassInternalName());
	}
	
	public static final String getGeneratedFQN(String fqn) {
		String[] ids = fqn.split("\\.");
		StringBuilder newFqn = new StringBuilder();
		for(int i=0; i<ids.length; i++) {
			if(i == ids.length - 1) {
				newFqn.append(getGeneratedIdentifier(ids[i]));
			} else {
				newFqn.append(ids[i] + ".");
			}
		}
		return newFqn.toString();
	}
	
	public static final String getInternalFQN(String fqn) {
		String[] ids = fqn.split("/");
		StringBuilder newFqn = new StringBuilder();
		for(int i=0; i<ids.length; i++) {
			if(i == ids.length - 1) {
				newFqn.append(getGeneratedIdentifier(ids[i]));
			} else {
				newFqn.append(ids[i] + "/");
			}
		}
		return newFqn.toString();
	}
	
	
	public static final String getGeneratedInterfaceInternalName(String method, int numArgs) {
		return GENERATED_PKG + "/" + 
		getGeneratedInterfaceSimpleName(method, numArgs);
	}
	
	public static final String getGeneratedInterfaceFullyQualifiedName(String method, int numArgs) {
		return getGeneratedInterfaceInternalName(method, numArgs).replace('/', '.');
	}
	
	public static final String getGeneratedStorageInternalName(List<P2<Boolean, String>> fields) {
		return GENERATED_PKG + "/" + 
		getGeneratedStorageSimpleName(fields);
	}
	
	public static final String getGeneratedInterfaceName(String method, int numArgs) {
		return getGeneratedInterfaceInternalName(method, numArgs).replace('/', '.');
	}
	
	public static final String getGeneratedStorageName(List<P2<Boolean, String>> fields) {
		return getGeneratedStorageInternalName(fields) .replace('/', '.');
	}
	
	public static final String getGeneratedInterfaceFilePath(String method, int numArgs) {
		return GENERATED_DIR + "/" + getGeneratedInterfaceSimpleName(method, numArgs) + ".class";
	}
	
	public static final String getIdentifierName(String sourceID) {
		return sourceID + "$plaid";
	}
	
	/*Generate Method Descriptors*/
	
	//Generates method descriptor for function that takes in PlaidObjects and returns a PlaidObject
	public static final String getMethodDescriptor(int numArgs) {
		return getMethodDescriptor(numArgs, PLAID_OBJECT_DESCRIPTOR, PLAID_OBJECT_DESCRIPTOR);
	}
	
	public static final String getMethodDescriptor(int numArgs, String argDescriptor, String retDescriptor) {
		StringBuilder sb = new StringBuilder("(");
		for(int i=0; i<numArgs; i++) {
			sb.append(argDescriptor); //args
		}
		sb.append(")");
		sb.append(retDescriptor); // return
		return sb.toString();
	}
	
	public static final String getMethodDescriptor(String firstArgDescriptor, int numArgs, String argDescriptor, String retDescriptor) {
		String start = getMethodDescriptor(numArgs, argDescriptor, retDescriptor);
		return start.replace("(", "("+firstArgDescriptor);
	}
	
	public static final String getMethodDescriptor(String firstArgDescriptor, String secondArgDescriptor, int numArgs, String argDescriptor, String retDescriptor) {
		String start = getMethodDescriptor(numArgs, argDescriptor, retDescriptor);
		String next = start.replace("(", "("+secondArgDescriptor);
		return next.replace("(", "("+firstArgDescriptor);
	}
	
	public static final String internalNameToDescriptor(String internalName) {
		return "L" + internalName + ";";
	}
	
	private static final HashMap<String,String> javaReservedMap = new HashMap<String,String>();
	static {
		javaReservedMap.put("abstract", "abstract" + GENERATED_SUFFIX);
		javaReservedMap.put("continue", "continue" + GENERATED_SUFFIX);
		javaReservedMap.put("for", "for" + GENERATED_SUFFIX);
		javaReservedMap.put("new", "new" + GENERATED_SUFFIX);
		javaReservedMap.put("switch", "switch" + GENERATED_SUFFIX);
		javaReservedMap.put("assert", "assert" + GENERATED_SUFFIX);
		javaReservedMap.put("default", "default" + GENERATED_SUFFIX);
		javaReservedMap.put("goto", "goto" + GENERATED_SUFFIX);
		javaReservedMap.put("package", "package" + GENERATED_SUFFIX);
		javaReservedMap.put("synchronized", "synchronized" + GENERATED_SUFFIX);
		javaReservedMap.put("boolean", "boolean" + GENERATED_SUFFIX);
		javaReservedMap.put("do", "do" + GENERATED_SUFFIX);
		javaReservedMap.put("if", "if" + GENERATED_SUFFIX);
		javaReservedMap.put("private", "private" + GENERATED_SUFFIX);
		javaReservedMap.put("this", "this" + GENERATED_SUFFIX);
		javaReservedMap.put("break", "break" + GENERATED_SUFFIX);
		javaReservedMap.put("double", "double" + GENERATED_SUFFIX);
		javaReservedMap.put("implements", "implements" + GENERATED_SUFFIX);
		javaReservedMap.put("protected", "protected" + GENERATED_SUFFIX);
		javaReservedMap.put("throw", "throw" + GENERATED_SUFFIX);
		javaReservedMap.put("byte", "byte" + GENERATED_SUFFIX);
		javaReservedMap.put("else", "else" + GENERATED_SUFFIX);
		javaReservedMap.put("import", "import" + GENERATED_SUFFIX);
		javaReservedMap.put("public", "public" + GENERATED_SUFFIX);
		javaReservedMap.put("throws", "throws" + GENERATED_SUFFIX);
		javaReservedMap.put("case", "case" + GENERATED_SUFFIX);
		javaReservedMap.put("enum", "enum" + GENERATED_SUFFIX);
		javaReservedMap.put("instanceof", "instanceof" + GENERATED_SUFFIX);
		javaReservedMap.put("return", "return" + GENERATED_SUFFIX);
		javaReservedMap.put("transient", "transient" + GENERATED_SUFFIX);
		javaReservedMap.put("catch", "catch" + GENERATED_SUFFIX);
		javaReservedMap.put("extends", "extends" + GENERATED_SUFFIX);
		javaReservedMap.put("int", "int" + GENERATED_SUFFIX);
		javaReservedMap.put("short", "short" + GENERATED_SUFFIX);
		javaReservedMap.put("try", "try" + GENERATED_SUFFIX);
		javaReservedMap.put("char", "char" + GENERATED_SUFFIX);
		javaReservedMap.put("final", "final" + GENERATED_SUFFIX);
		javaReservedMap.put("interface", "interface" + GENERATED_SUFFIX);
		javaReservedMap.put("static", "static" + GENERATED_SUFFIX);
		javaReservedMap.put("void", "void" + GENERATED_SUFFIX);
		javaReservedMap.put("class", "class" + GENERATED_SUFFIX);
		javaReservedMap.put("finally", "finally" + GENERATED_SUFFIX);
		javaReservedMap.put("long", "long" + GENERATED_SUFFIX);
		javaReservedMap.put("strictfp", "strictfp" + GENERATED_SUFFIX);
		javaReservedMap.put("volatile", "volatile" + GENERATED_SUFFIX);
		javaReservedMap.put("const", "const" + GENERATED_SUFFIX);
		javaReservedMap.put("float", "float" + GENERATED_SUFFIX);
		javaReservedMap.put("native", "native" + GENERATED_SUFFIX);
		javaReservedMap.put("super", "super" + GENERATED_SUFFIX);
		javaReservedMap.put("while", "while" + GENERATED_SUFFIX);
		javaReservedMap.put("true", "true" + GENERATED_SUFFIX);
		javaReservedMap.put("false", "false" + GENERATED_SUFFIX);
		javaReservedMap.put("null", "null" + GENERATED_SUFFIX);
	}
	
	
	public static final String getGeneratedIdentifier(String id) {
		if(javaReservedMap.containsKey(id)) {
			return javaReservedMap.get(id);
		} else {
			String mangledOps = convertOpStringToMethodName(id);
			return convertUpperCase(mangledOps);
		}
	}
	
	private static final String convertUpperCase(String id) {
		StringBuilder newId = new StringBuilder();
		char[] chars = id.toCharArray();
		for(char c : chars) {
			newId.append(c);
			if (Character.isUpperCase(c)) {
				newId.append('_');
			} else if(c == '_') {
				newId.append('$');
			}
		}
		return newId.toString();
	}
	
	private static final HashMap<String,String> opNames = new HashMap<String,String>();
	static {
        opNames.put("=","eq");
        opNames.put("<","lt");
        opNames.put(">","gt");
        opNames.put("!","bang");
        opNames.put("~","tilde");
        opNames.put("?","quest");
        opNames.put(":","colon");
        opNames.put("&","amp");
        opNames.put("|","pipe");
        opNames.put("+","plus");
        opNames.put("-","sub");
        opNames.put("*","mult");
        opNames.put("/","div");
        opNames.put("^","carat");
        opNames.put("%","mod");
	}
	
	public static String convertOpStringToMethodName(String op) {
		StringBuilder output = new StringBuilder();
		for (int i=0; i<op.length(); i++) {
			String opChar = op.substring(i, i+1);
			if(opNames.containsKey(opChar)) {
				output.append(opNames.get(opChar));
			} else {
				output.append(opChar); //original method
			}
		}
		if (output.length() != op.length()) { // modified name so we must prevent collision
			output.append(NamingConventions.GENERATED_SUFFIX);
	    }
		return output.toString();
	}
	
	//Standard Annotations
	public static final String BUILTIN_ANNOTATION = "builtin";
	
}
