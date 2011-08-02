package plaid.parser.ast;

import plaid.parser.Token;

public class OperatorExpr extends Expr {
	protected final Identifier operator;
	
	public OperatorExpr(Token token, Identifier operator) {
		super(token);
		this.operator = operator;
	}
	
	public Identifier getOperator() {
		return operator;
	}

}
