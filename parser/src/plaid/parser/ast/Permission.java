package plaid.parser.ast;

import plaid.parser.Token;

public class Permission extends ASTNode {
	
	public enum PermissionKind {FULL, IMMUTABLE, PURE, DYN, UNIQUE, SHARED, NONE};
	
	private final PermissionKind permissionKind;
	private final Expression datagroup;
	
	private Permission(Token token, PermissionKind permissionKind, Expression datagroup) {
		super(token);
		this.permissionKind = permissionKind;
		this.datagroup = datagroup;
	}
	
	public PermissionKind getPermissionKind() {
		return this.permissionKind;
	}

	public Expression getDatagroup() {
		return datagroup;
	}
}
