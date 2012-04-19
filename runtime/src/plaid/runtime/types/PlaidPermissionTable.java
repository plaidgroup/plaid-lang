package plaid.runtime.types;

public interface PlaidPermissionTable {
	public void incNumUnique();
	public void incNumImmutable();
	public void incNumNone();
	
	public void decNumUnique();
	public void decNumImmutable();
	public void decNumNone();

	public void execPermCast(PlaidPermission oldPerm, PlaidPermission newPerm);
	public void execPermSplit(PlaidPermission initialPerm, PlaidPermission neededPerm, PlaidPermission residuePerm);
	public void execPermJoin(PlaidPermission toJoinPerm1, PlaidPermission toJoinPerm2, PlaidPermission resultPerm);
}
