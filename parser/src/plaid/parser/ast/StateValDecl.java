package plaid.parser.ast;

import plaid.parser.Token;

public class StateValDecl extends Stmt {
	private final Identifier var;
	private final State state;
	public StateValDecl(Token token, Identifier var, State state) {
		super(token);
		this.var = var;
		this.state = state;
	}
	public Identifier getVar() {
		return var;
	}
	public State getState() {
		return state;
	}
}
