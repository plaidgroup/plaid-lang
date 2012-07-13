package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class ClassObject implements CodeObject, CodeGenerator {
	protected ArrayList<String> dependencies = new ArrayList<String>();
	protected LinkedList<CodeObject> elements = new LinkedList<CodeObject>();
	protected String indent;
	protected String superClass;
	protected ArrayList<String> interfaces = new ArrayList<String>();
	protected int modifier;
	
	protected String name;
	
	protected String namespace;
	
	@Override
	public void addDependency(String dependencyName) {
		if (!dependencies.contains(dependencyName))
			dependencies.add(dependencyName);

	}
	
	public ClassObject(String name, int modifier, CodeObject... objs) {
		this.name = name;
		this.modifier = modifier;
		elements.addAll(Arrays.asList(objs));
	}
	
	public ClassObject(String name, String superClass, String[] interfaces, int modifier, CodeObject... objs) {
		this(name, modifier, objs);
		this.superClass = superClass;
		this.interfaces.addAll(Arrays.asList(interfaces));
	}
	
	public void addMember(CodeObject obj) {
		elements.add(obj);
	}

	public void addCodeObject(CodeObject obj) {
		this.elements.add(obj);
	}
	
	@Override
	public String getIndentString() {
		return indent;
	}
	
	public String getNamespace() {
		return namespace;
	}

	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		indent = generator.getIndentString() +"\t";
		String innerIndent = generator.getIndentString();
		
		StringBuilder output = new StringBuilder();
		output.append(innerIndent);
		

		if (Modifier.isPublic(modifier))
			output.append("public ");
		else if (Modifier.isPrivate(modifier))
			output.append("private ");
		else if (Modifier.isProtected(modifier))
			output.append("protected ");
		if (Modifier.isStatic(modifier))
			output.append("static ");
		if (Modifier.isFinal(modifier))
			output.append("final ");
		
		output.append("class "+name);
		if (superClass != null)
			output.append(" extends "+superClass);
		if (interfaces != null && interfaces.size() > 0) {
			output.append(" implements ");
			for (int i = 0; i < interfaces.size(); i++)
				output.append(((i==0)?"":", ")+interfaces.get(i));
		}
		output.append(" { \n");
		for (CodeObject co : elements) {
			output.append(co.getGeneratedCode(this));
		}
		output.append(innerIndent+"}\n");
		
		for (String dependency : dependencies) {
			generator.addDependency(dependency);
		}
		return output.toString();
	}

}
