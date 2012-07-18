package plaid.fastruntime.util.interfacegen.CodeObjects;

public interface PlaidMethodInfo extends CodeObject {
	public String getName();
	public int getNArgs();
	public Class[] getArgumentTypes();
	public String getOperation();
}
