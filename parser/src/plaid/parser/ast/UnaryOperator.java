package plaid.parser.ast;

import plaid.parser.Token;

public class UnaryOperator extends Operator {
	protected final Expr e;
	
	public UnaryOperator(Token token, Identifier op, Expr e) {
		super(token, op);
		this.e = e;
	}
	
	public Expr getExpression() {
		return e;
	}

	@Override
	public String toString() {
		return getOperator().toString() + e.toString();
	}
}
