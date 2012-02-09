package plaid.runtime.types;

public class PlaidNonePermission implements PlaidPermission {
	private static PlaidNonePermission noneSingleton = new PlaidNonePermission();
	
	public static PlaidNonePermission none() {
		return noneSingleton;
	}
	
	private PlaidNonePermission() {
		
	}
	
	@Override
	public void addPermission(PlaidPermissionTable permTable) {
		permTable.incNumNone();
	}
	
	@Override
	public void removePermission(PlaidPermissionTable permTable) {
		permTable.decNumNone();
	}

}
