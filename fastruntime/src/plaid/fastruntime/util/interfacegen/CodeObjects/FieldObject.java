package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Modifier;

public class FieldObject implements CodeObject {
	String name;
	String type;
	int modifiers;
	public FieldObject(String type, String name, int modifiers) {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		StringBuilder sb = new StringBuilder();
		sb.append(generator.getIndentString());
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
		sb.append(type);
		sb.append(" ");
		sb.append(name);
		sb.append(";\n");
		return sb.toString();
	}

}
