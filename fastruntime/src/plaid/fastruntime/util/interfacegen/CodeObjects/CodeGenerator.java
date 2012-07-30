package plaid.fastruntime.util.interfacegen.CodeObjects;

public interface CodeGenerator {
	public void addDependency(String dependencyName);
	public String getIndentString();
}

