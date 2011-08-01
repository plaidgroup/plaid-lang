package plaid.parser.ast;

import plaid.parser.Token;

public class UnaryOperator extends Operator {
	protected final Expr expression;
	
	public UnaryOperator(Token token, Identifier op, Expr e) {
		super(token, op);
		this.expression = e;
	}
	
	public Expr getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return getOperator().toString() + expression.toString();
	}
}
