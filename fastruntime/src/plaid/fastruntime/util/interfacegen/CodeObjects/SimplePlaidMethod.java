package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import plaid.fastruntime.NamingConventions;

public abstract class SimplePlaidMethod implements CodeObject, PlaidMethodInfo {
	
	protected static final Map<String,Class> builtInMap = new HashMap<String,Class>();
	static {
	       builtInMap.put("int", Integer.class );
	       builtInMap.put("long", Long.class );
	       builtInMap.put("double", Double.class );
	       builtInMap.put("float", Float.class );
	       builtInMap.put("bool", Boolean.class );
	       builtInMap.put("char", Character.class );
	       builtInMap.put("byte", Byte.class );
	       builtInMap.put("void", Void.class );
	       builtInMap.put("short", Short.class );
	}
	
	public enum Return {
		Object, Boolean, Integer, Double, String, Void, Self
	}
	
	protected String name;
	protected int nArgs;
	protected String returnType;
	
	protected Class[] castTypes;
	
	protected String operation;
	
	protected String methodBody = "";
	
	
	public SimplePlaidMethod(String name, 
			int nArgs, Return returnType, Class[] castTypes, String operation) {
		this.name = NamingConventions.getIdentifierName(NamingConventions.getGeneratedIdentifier(name));
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
			methodBody = operation;
			operation = "";
			this.returnType = "Util.unit()";
			break;
		default:
			if (plaidObjectType != null && returnType == Return.Self)
				this.returnType = "new "+plaidObjectType+"(%s)";
			else
				this.returnType = "Util.javaToPlaid(%s)";
			break;
		}
	}
	
	public SimplePlaidMethod(String name, 
			int nArgs, Return returnType, Class[] castTypes, String operation, String plaidObjectType) {
		this(name, nArgs, returnType, castTypes, operation);
		
		parseFromReturn(returnType, plaidObjectType);
	}
	
	protected SimplePlaidMethod() {}
	
	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		String indent = generator.getIndentString();
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "public PlaidObject "+name+"(PlaidObject receiver");
		for (int i = 0; i < nArgs; i++) {
			sb.append(", PlaidObject arg"+i);
		}
		sb.append(") {\n");
		String innerIndent = indent+"\t";

		String[] parsedArgs = new String[nArgs+1];
		
		generator.addDependency(castTypes[0].getCanonicalName());
		
		sb.append(innerIndent+"Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();\n");
		parsedArgs[0] = "(("+castTypes[0].getSimpleName() +")parsedReciever)";
		
		for (int i = 1; i <= nArgs; i++) {
			sb.append(innerIndent+"Object parsedArg"+i+" = ((PlaidJavaObject) arg"+(i-1)+").getJavaObject();\n");
			parsedArgs[i] = "(("+castTypes[i].getSimpleName() +")parsedArg"+i+")";
		}
		
		if (generator instanceof PlaidCodeGenerator) {
			((PlaidCodeGenerator)generator).addMethodInterface(this);
		}
		
		generator.addDependency("plaid.fastruntime.PlaidJavaObject");
		
		String castOp = returnType;
		
		StringBuilder bodyB = new StringBuilder();
		if (!methodBody.isEmpty()) bodyB.append(innerIndent + String.format(methodBody, parsedArgs) +";\n");
		bodyB.append(innerIndent + "return " + String.format(castOp, String.format(operation.replace("\n", "\n"+innerIndent+"\t"), parsedArgs)) + ";\n");
		sb.append(processMethodBody(bodyB.toString(), innerIndent));
		
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

	@Override
	public Class[] getArgumentTypes() {
		return castTypes;
	}

	@Override
	public String getOperation() {
		return operation;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode()*37 + nArgs;
	}
	
	protected String processMethodBody(String source, String indent) {
		return source;
	}
}
