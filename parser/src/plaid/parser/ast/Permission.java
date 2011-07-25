package plaid.parser.ast;

import plaid.parser.Token;

public class Permission extends ASTNode {
	
	public enum PermissionKind {FULL, IMMUTABLE, PURE, DYN, UNIQUE, SHARED, NONE};
	
	private final PermissionKind permissionKind;
	
	private Permission(Token token, PermissionKind permissionKind) {
		super(token);
		this.permissionKind = permissionKind;
	}
	
	public PermissionKind getPermissionKind() {
		return this.permissionKind;
	}
}
