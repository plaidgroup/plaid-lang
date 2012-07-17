package plaid.fastruntime.util;

import java.util.ArrayList;
import java.util.List;

public class PlaidObjectBuilder {
	
	private final StringBuilder sb;
	private final StringBuilder endBuilder;
	private final StringBuilder initBuilder;
	
	private StringBuilder caseBuilder;
	private List<String> cases = new ArrayList<String>();
	private boolean inCase = false;
	
	private String className;
	private String packageName;
	
	public PlaidObjectBuilder() {
		sb = new StringBuilder();
		endBuilder = new StringBuilder();
		initBuilder = new StringBuilder();
	}
	
	public void startCase() {
		caseBuilder = new StringBuilder();
		if (inCase) {
			throw new IllegalStateException("Cannot start case that is already started");
		} else {
			inCase = true;
		}
	}
	
	public void endCase() {
		cases.add(caseBuilder.toString());
		if (inCase) {
			inCase = false;
		} else {
			throw new IllegalStateException("Cannot start case that is already started");
		}
	}
	
	public void appendCaseList() {
		sb.append(makeSeperatedString(cases,"else "));
		cases = new ArrayList<String>();
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public void append(String s) {
		if(inCase) {
			caseBuilder.append(s);
		} else {
			sb.append(s);
		}
	}
	
	public void appendEnd(String s) {
		endBuilder.append(s);
	}
	
	public void appendInit(String s) {
		endBuilder.append(s);
	}
	
	public void appendIsStatic(String s, boolean isStatic) {
		if (isStatic) {
			appendInit(s);
		} else {
			append(s);
		}
	}
	
	private String makeSeperatedString(List<String> stringList, String seperator) {
		StringBuilder result = new StringBuilder();
		for(int i=0; i<stringList.size(); i++) {
			if (i>0) {
				result.append(seperator);
			}
			result.append(stringList.get(i));
		}
		return result.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder finalCode = new StringBuilder();
		
		finalCode.append("package " + packageName + ";\n");
		finalCode.append("public class " + className + " {\n");
        
		finalCode.append(sb.toString());
		finalCode.append("\n");
		finalCode.append("static {\n");
		finalCode.append(initBuilder.toString());
		finalCode.append("}\n");
		finalCode.append("\n");
		finalCode.append(endBuilder.toString());

		finalCode.append("}\n");
		return finalCode.toString();
	}
}
