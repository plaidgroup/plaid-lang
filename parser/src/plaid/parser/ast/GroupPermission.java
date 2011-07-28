package plaid.parser.ast;

public class GroupPermission {
	protected GroupPermissionKind gpkind;
	
	public enum GroupPermissionKind {
		EMPTY,
		EXCLUSIVE,
		SHARED,
		PROTECTED,
		MUTABLE,
		READONLY
	}
	
	protected GroupPermission(GroupPermissionKind gpkind) {
		this.gpkind = gpkind;
	}
	
	@Override
	public String toString() {
		return gpkind.toString().toLowerCase();
	}
	
	public static GroupPermission EMPTY = new GroupPermission(GroupPermissionKind.EMPTY);
	public static GroupPermission EXCLUSIVE = new GroupPermission(GroupPermissionKind.EXCLUSIVE);
	public static GroupPermission SHARED = new GroupPermission(GroupPermissionKind.SHARED);
	public static GroupPermission PROTECTED = new GroupPermission(GroupPermissionKind.PROTECTED);

}
