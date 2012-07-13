package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import plaid.fastruntime.NamingConventions;

public class SimplePlaidMethod implements CodeObject, PlaidMethodInfo {
	
	private static final Map<String,Class> builtInMap = new HashMap<String,Class>();
	static {
	       builtInMap.put("int", Integer.TYPE );
	       builtInMap.put("long", Long.TYPE );
	       builtInMap.put("double", Double.TYPE );
	       builtInMap.put("float", Float.TYPE );
	       builtInMap.put("bool", Boolean.TYPE );
	       builtInMap.put("char", Character.TYPE );
	       builtInMap.put("byte", Byte.TYPE );
	       builtInMap.put("void", Void.TYPE );
	       builtInMap.put("short", Short.TYPE );
	}
	
	public enum Return {
		Object, Boolean, Integer, Double, String, Void, Self
	}
	
	protected String name;
	private int nArgs;
	private String returnType;
	
	protected String[] castTypes;
	
	private String operation;
	
	public SimplePlaidMethod(String name, 
			int nArgs, Return returnType, String[] castTypes, String operation) {
		this.name = name;
		this.nArgs = nArgs;
		this.castTypes = castTypes;
		this.operation = operation;
		
		parseFromReturn(returnType, null);
	}

	protected void parseFromReturn(Return returnType, String plaidObjectType) {
		switch (returnType) {
		case Boolean:
			this.returnType = "Util.bool(%s)";
			break;
		case Integer:
			this.returnType = "Util.integer(%s)";
			break;
		case Double:
			this.returnType = "Util.float64(%s)";
			break;
		case String:
			this.returnType = "Util.string(%s)";
			break;
		case Void:
			this.returnType = "Util.unit()";
		default:
			if (plaidObjectType != null && returnType == Return.Self)
				this.returnType = "new "+plaidObjectType+"(%s)";
			else
				this.returnType = "Util.javaToPlaid(%s)";
		}
	}
	
	public SimplePlaidMethod(String name, 
			int nArgs, Return returnType, String[] castTypes, String operation, String plaidObjectType) {
		this(name, nArgs, returnType, castTypes, operation);
		
		parseFromReturn(returnType, plaidObjectType);
	}
	
	public SimplePlaidMethod(Method mInfo, Class javaType, String returnPlaidObjectType) {
		this.name = mInfo.getName();
		this.nArgs = mInfo.getParameterTypes().length;
		Class returnType = mInfo.getReturnType();
		if (returnType == int.class)
			this.returnType = "Util.integer(%s)";
		else if (returnType == boolean.class)
			this.returnType = "Util.bool(%s)";
		else if (returnType == double.class)
			this.returnType = "Util.float64(%s)";
		else if (returnType == String.class)
			this.returnType = "Util.string(%s)";
		else if (returnType == void.class)
			this.returnType = "Util.unit()";
		else if (returnType == javaType)
			this.returnType = "new "+returnPlaidObjectType+"(%s)";
		else 
			this.returnType = "Util.javaToPlaid(%s)";
		
		this.castTypes = new String[nArgs+1];
		castTypes[0] = mInfo.getDeclaringClass().getSimpleName();
		Class[] argTypes = mInfo.getParameterTypes();
		for (int i = 1; i <= nArgs; i++) {
			if (!argTypes[i-1].isPrimitive())
				castTypes[i] = argTypes[i-1].getSimpleName();
			else
				castTypes[i] = builtInMap.get(argTypes[i-1].getSimpleName()).getSimpleName();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("%s."+name+"(");
		for (int i = 0; i < nArgs; i++) {
			if (i > 0)
				sb.append(", ");
			sb.append("%s");
		}
		sb.append(")");
		this.operation = sb.toString();
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		String indent = generator.getIndentString();
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "public PlaidObject "+NamingConventions.getIdentifierName(name)+"(PlaidObject receiver");
		for (int i = 0; i < nArgs; i++) {
			sb.append(", PlaidObject arg"+i);
		}
		sb.append(") {\n");
		String innerIndent = indent+"\t";

		String[] parsedArgs = new String[nArgs+1];
		
		sb.append(innerIndent+""+castTypes[0] +" parsedReciever = ("+castTypes[0]+")((PlaidJavaObject) receiver).getJavaObject();\n");
		parsedArgs[0] = "parsedReciever";
		
		for (int i = 1; i <= nArgs; i++) {
			sb.append(innerIndent+""+castTypes[i] +" parsedArg"+i+" = ("+castTypes[i]+")((PlaidJavaObject) arg"+(i-1)+").getJavaObject();\n");
			parsedArgs[i] = "parsedArg"+i;
		}
		
		if (generator instanceof PlaidCodeGenerator) {
			((PlaidCodeGenerator)generator).addMethodInterface(this);
		}
		
		generator.addDependency("plaid.fastruntime.PlaidJavaObject");
		
		String castOp = returnType;
		
		sb.append(innerIndent + "return " + String.format(castOp, String.format(operation, parsedArgs)) + ";\n");
		
		sb.append(indent + "}\n");
		return sb.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getNArgs() {
		return nArgs;
	}
}
