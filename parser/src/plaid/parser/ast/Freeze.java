package plaid.parser.ast;

import plaid.parser.Token;


public class Freeze extends StatePrim {

	private final Expr frozenExpr;
	
	public Freeze(Token t, Expr frozenExpr) {
		super(t);
		this.frozenExpr = frozenExpr;
	}

	public Expr getFrozenExpr() {
		return frozenExpr;
	}	
	
	@Override
	public String toString() {
		return "freeze " + frozenExpr.toString() + ";"; 
	}
}
