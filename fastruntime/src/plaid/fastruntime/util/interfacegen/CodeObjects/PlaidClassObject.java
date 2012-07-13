package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.util.interfacegen.JavaPlaidFile;

public class PlaidClassObject extends ClassObject implements PlaidCodeGenerator {
	private ArrayList<PlaidMethodInfo> toGenerate = new ArrayList<PlaidMethodInfo>();
	
	public PlaidClassObject(String name, String namespaceName, CodeObject... objs) {
		super(name, 0, objs);
		this.namespace = namespaceName;
		
		this.superClass = "AbstractPlaidDispatch";
		
		this.addDependency("plaid.fastruntime.reference.AbstractPlaidDispatch");
	}
	

	@Override
	public void addMethodInterface(PlaidMethodInfo info) {
		toGenerate.add(info);
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		indent = generator.getIndentString() + "\t";
		StringBuilder output = new StringBuilder();

		for (CodeObject co : elements) {
			output.append(co.getGeneratedCode(this));
		}
		
		String methods = output.toString();
		

		if (generator instanceof JavaPlaidFile)
			for (PlaidMethodInfo mpi : toGenerate) {
				String interf = ((JavaPlaidFile)generator).addPlaidMethodInterface(mpi);
				
				interfaces.add(interf);
				this.addDependency(interf);
			}
		
		
		output = new StringBuilder();
		output.append("class "+name);
		if (superClass != null)
			output.append(" extends "+superClass);
		if (interfaces != null && interfaces.size() > 0) {
			output.append(" implements ");
			for (int i = 0; i < interfaces.size(); i++)
				output.append(((i==0)?"":", ")+interfaces.get(i));
		}
		output.append(" { \n");
		output.append(methods);
		output.append("}\n");
		
		for (String dependency : dependencies) {
			generator.addDependency(dependency);
		}
		return output.toString();
	}

}
