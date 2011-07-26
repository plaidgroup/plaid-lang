package plaid.parser.ast;

import plaid.parser.Token;

public class StateOp extends ASTNode implements DeclOrStateOp {

	public StateOp(Token token) {
		super(token);
	}
}
