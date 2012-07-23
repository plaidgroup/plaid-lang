package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import plaid.fastruntime.NamingConventions;

public class OverloadedPlaidMethod implements PlaidMethodInfo, CodeObject {
	
	private TreeSet<PlaidMethodInfo> methods = new TreeSet<PlaidMethodInfo>(new Comparator<PlaidMethodInfo>() {
		@Override
		public int compare(PlaidMethodInfo o1, PlaidMethodInfo o2) {
			Class[] o1ArgTypes = o1.getArgumentTypes();
			Class[] o2ArgTypes = o2.getArgumentTypes();
			for (int i = 0; i < o1ArgTypes.length && i < o2ArgTypes.length; i++) {
				//o2 <: o1
				if (o1ArgTypes[i].isAssignableFrom(o2ArgTypes[i])) {
					return -1;
				}
				//o1 <: o2
				else if (o2ArgTypes[i].isAssignableFrom(o1ArgTypes[i])) {
					continue;
				} 
				//o1 == o2
				else if (o2ArgTypes[i] == o1ArgTypes[i]) {
					continue;
				}
			}
			return 1;
		}});

	public OverloadedPlaidMethod(PlaidMethodInfo... baseMethods) {
		methods.addAll(Arrays.asList(baseMethods));
	}
	
	public void addMethod(PlaidMethodInfo method) {
		methods.add(method);
	}

	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		if (methods.size() <= 0)
			return "";
		
		String name = methods.first().getName();
		int nArgs = methods.first().getNArgs();
		
		
		String indent = generator.getIndentString();
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "public PlaidObject "+name+"(PlaidObject receiver");
		for (int i = 0; i < nArgs; i++) {
			sb.append(", PlaidObject arg"+i);
		}
		sb.append(") {\n");
		String innerIndent = indent+"\t";

		String[] parsedArgs = new String[nArgs+1];
		
		String recvType = methods.first().getArgumentTypes()[0].getSimpleName();
		sb.append(innerIndent+recvType+" parsedReciever = ("+recvType+")((PlaidJavaObject) receiver).getJavaObject();\n");
		parsedArgs[0] = "parsedReciever";
		
		for (int i = 1; i <= nArgs; i++) {
			sb.append(innerIndent+"Object parsedArg"+i+" = ((PlaidJavaObject) arg"+(i-1)+").getJavaObject();\n");
			parsedArgs[i] = "parsedArg"+i+"";
		}
		
		if (generator instanceof PlaidCodeGenerator) {
			((PlaidCodeGenerator)generator).addMethodInterface(methods.first());
		}
		
		generator.addDependency("plaid.fastruntime.PlaidJavaObject");
		boolean first = true;
		Iterator i = methods.descendingIterator();
		sb.append(indent+"\ttry {\n");
		innerIndent += "\t";
		while (i.hasNext()) {
			String[] castArgs = new String[parsedArgs.length];
			SimplePlaidMethod current = (SimplePlaidMethod)i.next();

			if (current instanceof JavaPlaidMethod) {
				Class declaringClass = ((JavaPlaidMethod)current).getDeclaringClass();
				castArgs[0] = ((declaringClass != current.castTypes[0])?"("+declaringClass.getCanonicalName() +")":"")+parsedArgs[0];
			} else  {
				castArgs[0] = parsedArgs[0];
			}
			sb.append(innerIndent+((first)?"if (":"else if("));
			for (int n = 1; n < parsedArgs.length; n++) {
				if (!current.castTypes[n].isArray() && !current.castTypes[n].isPrimitive())
					generator.addDependency(current.castTypes[n].getCanonicalName());
				castArgs[n] = "("+current.castTypes[n].getSimpleName()+")"+parsedArgs[n];
				sb.append(((n>1)?" && ":"")+parsedArgs[n]+" instanceof "+current.castTypes[n].getSimpleName());
			}
			sb.append(") {\n");
			String innerInnerIndent = innerIndent + "\t";
			String castOp = current.returnType;
			if (!current.methodBody.isEmpty()) sb.append(innerInnerIndent + String.format(current.methodBody, castArgs) +";\n");
			sb.append(innerInnerIndent + "return " + String.format(castOp, String.format(current.operation.replace("\n", "\n"+innerIndent+"\t"), castArgs)) + ";\n");
			sb.append(innerIndent+"}\n");
			first = false;
		}
		
		sb.append(innerIndent+"else {\n"+innerIndent+"\t\tthrow new PlaidIllegalOperationException(\"Invalid arguments to function\"); \n"+innerIndent+"}\n");
		sb.append(indent+"\t} catch (Exception e) { throw new PlaidIllegalOperationException(\"A java exception was thrown\", e); }\n");
		sb.append(indent + "}\n");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode()*37 + getNArgs();
	}

	@Override
	public String getName() {
		return methods.first().getName();
	}

	@Override
	public int getNArgs() {
		return methods.first().getNArgs();
	}

	@Override
	public Class[] getArgumentTypes() {
		throw new RuntimeException("An overloaded method has no defined arguments");
	}

	@Override
	public String getOperation() {
		throw new RuntimeException("An overloaded method has no defined operation");
	}

}
