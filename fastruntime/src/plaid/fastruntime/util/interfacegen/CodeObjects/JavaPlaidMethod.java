package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.lang.reflect.Method;
import java.util.ArrayList;

import plaid.fastruntime.NamingConventions;

public class JavaPlaidMethod extends SimplePlaidMethod {
	private boolean isDeprecated;
	public JavaPlaidMethod(Method mInfo, Class javaType, String returnPlaidObjectType) {
		this.mInfo = mInfo;
		String methodName = mInfo.getName();
		this.name = NamingConventions.getGeneratedIdentifier(methodName);
		this.nArgs = mInfo.getParameterTypes().length;
		Class returnType = mInfo.getReturnType();
		getReturn(javaType, returnPlaidObjectType, returnType);
		
		this.castTypes = new Class[nArgs+1];
		castTypes[0] = mInfo.getDeclaringClass();
		Class[] argTypes = mInfo.getParameterTypes();
		for (int i = 1; i <= nArgs; i++) {
			if (!argTypes[i-1].isPrimitive())
				castTypes[i] = argTypes[i-1];
			else {
				castTypes[i] = builtInMap.get(argTypes[i-1].getSimpleName());
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("%s."+methodName+"(");
		for (int i = 0; i < nArgs; i++) {
			if (i > 0)
				sb.append(", ");
			sb.append("%s");
		}
		sb.append(")");
		this.operation = sb.toString();
		if (returnType == void.class) {
			methodBody = operation;
			operation = "";
		}
		
		declaringClass = mInfo.getDeclaringClass();
		
		for (Class exce : mInfo.getExceptionTypes()) {
			if (!(RuntimeException.class.isAssignableFrom(exce))) {
				throwsEx.add(exce);
			}
		}
		
		isDeprecated = mInfo.isAnnotationPresent(Deprecated.class);
	}
	
	private Method mInfo;
	
	private Class declaringClass;
	public Class getDeclaringClass() {
		return declaringClass;
	}
	
	public Class[] getThrowsEx() {
		return (Class[]) throwsEx.toArray();
	}

	private ArrayList<Class> throwsEx = new ArrayList<Class>();
	
	@Override
	protected String processMethodBody(String body, String indent) {
		if (throwsEx.size() > 0) {
			StringBuilder outputBuilder = new StringBuilder();
			outputBuilder.append(indent+"try {\n");
			outputBuilder.append("\t"+body.replace("\n","\n\t")+"\n");
			outputBuilder.append(indent+"} catch (Exception e) { \n"+indent+"\tthrow new PlaidIllegalOperationException(\"A java error has occured.\",e); \n"+indent+"}\n");
			return outputBuilder.toString();
		} else
			return body;
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator g) {
		
		return ((isDeprecated)?g.getIndentString()+"@Deprecated\n":"")+super.getGeneratedCode(g);
	}
	

}
