package plaid.parser.ast;

import plaid.parser.Token;

public class UnaryOperator extends Operator {
	protected final Expression e;
	
	public UnaryOperator(Token token, Identifier op, Expression e) {
		super(token, op);
		this.e = e;
	}
	
	public Expression getExpression() {
		return e;
	}

	@Override
	public String toString() {
		return getOperator().toString() + e.toString();
	}
}
