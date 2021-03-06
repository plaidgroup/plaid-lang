package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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

		HashMap<Integer, PlaidMethodInfo> seenMethods = new HashMap<Integer, PlaidMethodInfo>();
		ArrayList<OverloadedPlaidMethod> overloaded = new ArrayList<OverloadedPlaidMethod>();
		
		
		Iterator elementIterator = elements.iterator();
		while (elementIterator.hasNext()) {
			CodeObject co = (CodeObject) elementIterator.next();
			if (co instanceof PlaidMethodInfo) {
				PlaidMethodInfo castMethod = (PlaidMethodInfo) co;
				if (seenMethods.containsKey(castMethod.hashCode())) {
					PlaidMethodInfo pmi = seenMethods.get(castMethod.hashCode());
					if (pmi instanceof OverloadedPlaidMethod) {
						((OverloadedPlaidMethod) pmi).addMethod(castMethod);
					} else {
						seenMethods.remove(castMethod.hashCode());
						OverloadedPlaidMethod opm = new OverloadedPlaidMethod(pmi);
						opm.addMethod(castMethod);
						seenMethods.put(opm.hashCode(), opm);
						overloaded.add(opm);
					}
					
				} else {
					seenMethods.put(castMethod.hashCode(), castMethod);
				}
				elementIterator.remove();
			}
		}
		
		elements.addAll(seenMethods.values());

		for (CodeObject co : elements) {
			output.append(co.getGeneratedCode(this));
		}
		
		String methods = output.toString();
		

		if (generator instanceof JavaPlaidFile)
			for (PlaidMethodInfo mpi : toGenerate) {
				String interf = ((JavaPlaidFile)generator).addPlaidMethodInterface(mpi);
				
				if (!interfaces.contains(interf))
					interfaces.add(interf);
			}
		
		
		output = new StringBuilder();
		output.append("public class "+name);
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
