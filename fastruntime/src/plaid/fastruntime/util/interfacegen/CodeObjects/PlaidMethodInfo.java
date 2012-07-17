package plaid.fastruntime.util.interfacegen.CodeObjects;

public interface PlaidMethodInfo {
	public String getName();
	public int getNArgs();
	public Class[] getArgumentTypes();
	public String getOperation();
}
