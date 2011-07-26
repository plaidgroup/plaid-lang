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
	
	@Override
	public boolean equivalent(ASTNode other) {
		if(other instanceof Freeze) {
			Freeze otherFreeze = (Freeze) other;
			return frozenExpr.equivalent(otherFreeze.frozenExpr);
		}
		else {
			return false;
		}
	}
}
