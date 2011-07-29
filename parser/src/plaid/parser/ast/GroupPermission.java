package plaid.parser.ast;

import plaid.parser.Token;

public abstract class GroupPermission extends ASTNode {
	
	public GroupPermission(Token token) {
		super(token);
	}
	
	public static GroupPermission EMPTY = EmptyGroupPermission.singleton;
}
