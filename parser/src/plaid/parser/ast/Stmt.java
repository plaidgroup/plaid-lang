package plaid.parser.ast;

import plaid.parser.Token;

public abstract class Stmt extends ASTNode {

	public Stmt(Token token) {
		super(token);
	}

}
