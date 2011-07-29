package plaid.parser.ast;

import plaid.parser.Token;

public abstract class Permission extends ASTNode {
	
	public static final Permission EMPTY = EmptyPermission.getEmptyPermission();
	
	public Permission(Token token) {
		super(token);
	}

}
