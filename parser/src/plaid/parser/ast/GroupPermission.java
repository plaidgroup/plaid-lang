package plaid.parser.ast;

import plaid.parser.Token;

public class GroupPermission extends ASTNode {
	protected GroupPermissionKind gpkind;
	
	public enum GroupPermissionKind {
		EMPTY,
		EXCLUSIVE,
		SHARED,
		PROTECTED,
		MUTABLE,
		READONLY
	}
	
	public GroupPermission(Token token, GroupPermissionKind gpkind) {
		super(token);
		this.gpkind = gpkind;
	}
	
	@Override
	public String toString() {
		return gpkind.toString().toLowerCase();
	}
	
	public static GroupPermission EMPTY = new GroupPermission(DEFAULT_TOKEN, GroupPermissionKind.EMPTY);
}
