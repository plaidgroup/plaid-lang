package plaid.runtime.types;

import plaid.runtime.PlaidPermissionCastException;

public class PlaidPermissionTableMap implements PlaidPermissionTable {
	private int numUnique;
	private int numImmutable;
	private int numNone;
	
	public PlaidPermissionTableMap() {
		this.numUnique = 0;
		this.numImmutable = 0;
		this.numNone = 0;
	}
	
	@Override
	public void execPermCast(PlaidPermission oldPerm, PlaidPermission newPerm) {
		oldPerm.removePermission(this);
		newPerm.addPermission(this);
	}

	@Override
	public void incNumUnique() {
		synchronized(this) {
			if (numUnique > 0 || numImmutable > 0) {
				throw new PlaidPermissionCastException("Cast to unique failed: other aliases exist.");
			}
			else {
				numUnique += 1;
			}
		}
	}

	@Override
	public void incNumImmutable() {
		synchronized(this) {
			if (numUnique > 0) {
				throw new PlaidPermissionCastException("Cast to immutable failed: unique alias exists.");
			}
			else {
				numImmutable += 1;
			}
		}
	}

	@Override
	public void incNumNone() {
		synchronized(this) {
			this.numNone += 1;
		}
	}

	@Override
	public void decNumUnique() {
		synchronized(this) {
			this.numUnique -= 1;
		}
	}

	@Override
	public void decNumImmutable() {
		synchronized(this) {
			this.numImmutable -= 1;
		}
	}

	@Override
	public void decNumNone() {
		synchronized(this) {
			this.numNone -= 1;
		}
	}

	@Override
	public void execPermSplit(PlaidPermission initialPerm, PlaidPermission neededPerm, PlaidPermission residuePerm) {
		initialPerm.removePermission(this);
		neededPerm.addPermission(this);
		residuePerm.addPermission(this);
	}

	@Override
	public void execPermJoin(PlaidPermission toJoinPerm1, PlaidPermission toJoinPerm2, PlaidPermission resultPerm) {
		toJoinPerm1.removePermission(this);
		toJoinPerm2.removePermission(this);
		resultPerm.addPermission(this);
	}

}
