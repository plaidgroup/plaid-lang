package plaid.runtime.types;


public class PlaidPermType {
	private PlaidPermission perm;
	private PlaidType type;
	
	private PlaidPermType(PlaidPermission perm, PlaidType type) {
		this.perm = perm;
		this.type = type;
	}
	
	public static PlaidPermType permType(PlaidPermission perm, PlaidType type) {
		return new PlaidPermType(perm, type);
	}
	
	public PlaidPermission getPermission() {
		return this.perm;
	}
	
	public PlaidType getType() {
		return this.type;
	}
}
