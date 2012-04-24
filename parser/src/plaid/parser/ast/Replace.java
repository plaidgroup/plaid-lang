package plaid.parser.ast;

import plaid.parser.Token;

public class Replace extends Expr {

	private final Expr expression;
	private final StateExpr stateExpr;
	
	public Replace(Token token, Expr e, StateExpr st) {
		super(token);
		this.expression = e;
		this.stateExpr = st;
	}
	
	public Expr getExpression() {
		return expression;
	}
	
	public StateExpr getStateExpr() {
		return stateExpr;
	}

	@Override
	public String toString() {
		return expression.toString()+"<<-"+stateExpr.toString();
	}
}
