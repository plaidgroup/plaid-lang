package plaid.runtime.types;

public class PlaidImmutablePermission implements PlaidPermission {
	private static PlaidImmutablePermission immutableSingleton = new PlaidImmutablePermission();
	
	public static PlaidImmutablePermission immutable() {
		return immutableSingleton;
	}
	
	private PlaidImmutablePermission() {
		
	}
	
	@Override
	public void addPermission(PlaidPermissionTable permTable) {
		permTable.incNumImmutable();
	}
	
	@Override
	public void removePermission(PlaidPermissionTable permTable) {
		permTable.decNumImmutable();
	}

}
