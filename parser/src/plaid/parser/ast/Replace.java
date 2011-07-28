package plaid.parser.ast;

import plaid.parser.Token;

public class Replace extends Expression {

	private final Expression e;
	private final State st;
	
	public Replace(Token token, Expression e, State st) {
		super(token);
		this.e = e;
		this.st = st;
	}
	
	public Expression getE() {
		return e;
	}
	
	public State getSt() {
		return st;
	}

	@Override
	public String toString() {
		return e.toString()+"<<-"+st.toString();
	}
}
