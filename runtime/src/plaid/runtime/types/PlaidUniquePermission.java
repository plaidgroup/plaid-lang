package plaid.runtime.types;

public class PlaidUniquePermission implements PlaidPermission {
	private static PlaidUniquePermission uniqueSingleton = new PlaidUniquePermission();
	
	public static PlaidUniquePermission unique() {
		return uniqueSingleton;
	}
	
	private PlaidUniquePermission() {
		
	}

	@Override
	public void addPermission(PlaidPermissionTable permTable) {
		permTable.incNumUnique();
	}
	
	@Override
	public void removePermission(PlaidPermissionTable permTable) {
		permTable.decNumUnique();
	}

}
