package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Modifier;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.util.interfacegen.CodeObjects.SimplePlaidMethod.Return;

public class JavaMethod implements CodeObject {
	
	private String name;
	private String[] argTypes;
	private String[] argNames;
	
	private String[] annotations;
	
	private String returnType;
	private String body;
	
	private final int modifiers;
	
	public JavaMethod(String name, String[] argTypes, String[] argNames, String returnType, String body, int modifiers) {
		this.name = name;
		this.argTypes = argTypes;
		this.argNames = argNames;
		this.returnType = returnType;
		this.body = body;
		this.modifiers = modifiers;
		this.annotations = new String[0];
	}
	
	public JavaMethod(String name, String[] argTypes, String[] argNames, String returnType, String body, int modifiers, String[] annotations) {
		this(name, argTypes, argNames, returnType, body, modifiers);
		this.annotations = annotations;
	}
	
	
	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		String indent = generator.getIndentString();
		StringBuilder sb = new StringBuilder();
		
		for (String annotation : annotations) {
			sb.append(indent+annotation+"\n");
		}
		sb.append(indent);
		if (Modifier.isPublic(modifiers))
			sb.append("public ");
		else if (Modifier.isPrivate(modifiers))
			sb.append("private ");
		else if (Modifier.isProtected(modifiers))
			sb.append("protected ");
		if (Modifier.isStatic(modifiers))
			sb.append("static ");
		if (Modifier.isFinal(modifiers))
			sb.append("final ");
		
		sb.append(((returnType != null && !returnType.equals(""))?returnType+" ":"")+name+"(");
		for (int i = 0; i < argTypes.length; i++) {
			sb.append(((i>0)?", ":"")+argTypes[i]+" "+argNames[i]);
		}
		sb.append(") {\n");
		sb.append(""+indent+"\t"+body.replace("\n", "\n"+indent+"\t")+"\n");
		sb.append(indent + "}\n");
		return sb.toString();
	}
}