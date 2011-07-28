package plaid.parser.ast;

import plaid.parser.Token;

public class Replace extends Expression {

	private final Expression e;
	private final StateExpression st;
	
	public Replace(Token token, Expression e, StateExpression st) {
		super(token);
		this.e = e;
		this.st = st;
	}
	
	public Expression getE() {
		return e;
	}
	
	public StateExpression getSt() {
		return st;
	}

	@Override
	public String toString() {
		return e.toString()+"<<-"+st.toString();
	}
}
