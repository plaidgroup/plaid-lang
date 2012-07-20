package plaid.fastruntime.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlaidObjectBuilder {
	
	private final StringBuilder sb;
	private final StringBuilder endBuilder;
	private final StringBuilder initBuilder;
	
	private final Stack<List<String>> matchStack;
	private final Stack<StringBuilder> caseBuilderStack;
	
	private String lastMatch = null;
	
	private String className;
	private String packageName;
	
	private boolean isInit;
	
	private boolean builtIn;
	
	public PlaidObjectBuilder() {
		this.sb = new StringBuilder();
		this.endBuilder = new StringBuilder();
		this.initBuilder = new StringBuilder();
		this.matchStack = new Stack<List<String>>();
		this.caseBuilderStack = new Stack<StringBuilder>();
		this.isInit = false;
		this.builtIn = false;
	}
	
	public boolean isBuiltIn() {
		return builtIn;
	}



	public void setBuiltIn(boolean isBuiltIn) {
		this.builtIn = isBuiltIn;
	}



	public void startInit() {
		if (this.isInit)  {
			throw new IllegalStateException("Initialization already started. Cannot restart.");
		} else {
			this.isInit = true;
		}
	}
	
	public void endInit() {
		if (this.isInit)  {
			this.isInit = false;
		} else {
			throw new IllegalStateException("Initialization already ended. Cannot re-end.");
		}
	}
	
	public void startMatch() {
		this.matchStack.push(new ArrayList<String>());
	}
	
	public void startCase() {
		this.caseBuilderStack.push(new StringBuilder());
	}
	
	public void endCase() {
		String caseString = this.caseBuilderStack.pop().toString();
		this.matchStack.peek().add(caseString);
	}
	
	public void endMatch(boolean plaidCodeEndsWithDefault) {
		List<String> cases = this.matchStack.pop();
		this.lastMatch = makeSeperatedString(cases,"else ");
		if (!plaidCodeEndsWithDefault) {
			this.lastMatch += "else {\n" +
				"throw new plaid.fastruntime.errors.PlaidIllegalOperationException(\"Pattern match exausted.\");\n" +
				"}\n";
		}
	}
	
	public void appendCaseList() {
		if (lastMatch != null) {
			this.append(this.lastMatch);
		} else {
			throw new IllegalStateException("Must start and end match before calling appendCaseList.");
		}
		this.lastMatch = null;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public void append(String s) {
		if(!matchStack.isEmpty() && !caseBuilderStack.isEmpty()) {
			caseBuilderStack.peek().append(s);
		} else if (!matchStack.isEmpty() && caseBuilderStack.isEmpty()) {
			throw new IllegalStateException("Match started but case not started");
		} else if (isInit) {
			initBuilder.append(s);
		}
		else {
			sb.append(s);
		}
	}
	
	public void appendEnd(String s) {
		if (this.isInit) {
			initBuilder.append(s);
		} else {
			endBuilder.append(s);
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
