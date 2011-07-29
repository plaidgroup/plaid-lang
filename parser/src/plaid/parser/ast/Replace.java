package plaid.parser.ast;

import plaid.parser.Token;

public class Replace extends Expr {

	private final Expr e;
	private final StateExpr st;
	
	public Replace(Token token, Expr e, StateExpr st) {
		super(token);
		this.e = e;
		this.st = st;
	}
	
	public Expr getE() {
		return e;
	}
	
	public StateExpr getSt() {
		return st;
	}

	@Override
	public String toString() {
		return e.toString()+"<<-"+st.toString();
	}
}
