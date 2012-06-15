package plaid.parser.ast;

import plaid.parser.Token;

public abstract class StatePrim extends StateExpr {

	public StatePrim(Token t) {
		super(t);
	}
}
