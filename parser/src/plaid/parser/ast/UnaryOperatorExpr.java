package plaid.parser.ast;

import plaid.parser.Token;

public class UnaryOperatorExpr extends OperatorExpr {
	protected final Expr expression;
	
	public UnaryOperatorExpr(Token token, Identifier op, Expr e) {
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
