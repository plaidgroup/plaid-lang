package plaid.parser.ast;

import plaid.parser.Token;

public class Permission extends ASTNode {
	
	public enum PermissionKind {FULL, IMMUTABLE, PURE, DYN, UNIQUE, SHARED, NONE, EMPTY};
	public static final Permission EMPTY = EmptyPermission.getEmptyPermission();
	
	private final PermissionKind permissionKind;
	private final Expr datagroup;
	
	public Permission(Token token, PermissionKind permissionKind, Expr datagroup) {
		super(token);
		this.permissionKind = permissionKind;
		this.datagroup = datagroup;
	}
	
	public PermissionKind getPermissionKind() {
		return this.permissionKind;
	}

	public Expr getDatagroup() {
		return datagroup;
	}

	@Override
	public String toString() {
		if ( permissionKind == PermissionKind.SHARED ) {
			if ( datagroup != Expr.EMPTY ) {
				return permissionKind.toString().toLowerCase() + "<" + datagroup.toString() + ">";
			} else {
				return permissionKind.toString().toLowerCase();
			}
		} else {
			return permissionKind.toString().toLowerCase();
		}
	}
}
