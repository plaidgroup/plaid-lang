package plaid.parser.ast;

import plaid.parser.Token;

public class LocalStateValDecl extends Stmt {
	private final Identifier id;
	private final StateExpression st;
	public LocalStateValDecl(Token token, Identifier id, StateExpression st) {
		super(token);
		this.id = id;
		this.st = st;
	}
	public Identifier getId() {
		return id;
	}
	public StateExpression getState() {
		return st;
	}
}
