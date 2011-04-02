package plaid.runtime.types;

public class PlaidNominalType implements PlaidType {
	private final String name;
	
	private PlaidNominalType(String name) {
		this.name = name;
	}
	
	public static PlaidNominalType nominalType(String name) {
		return new PlaidNominalType(name);
	}
	
	public final String getName() {
		return this.name;
	}
}
