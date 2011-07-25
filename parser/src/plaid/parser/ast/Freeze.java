package plaid.parser.ast;

import plaid.parser.Token;


public class Freeze extends StatePrim {

	private final Expression frozenExpr;
	
	public Freeze(Token t, Expression frozenExpr) {
		super(t);
		this.frozenExpr = frozenExpr;
	}

	public Expression getFrozenExpr() {
		return frozenExpr;
	}	
}
