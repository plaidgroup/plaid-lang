package plaid.parser.ast;

import plaid.parser.Token;

public class LocalStateValDecl extends Stmt {
	private final Identifier id;
	private final State st;
	public LocalStateValDecl(Token token, Identifier id, State st) {
		super(token);
		this.id = id;
		this.st = st;
	}
	public Identifier getId() {
		return id;
	}
	public State getState() {
		return st;
	}
}
