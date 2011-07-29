package plaid.parser.ast;

import plaid.parser.Token;

public class LocalStateValDecl extends Stmt {
	private final Identifier id;
	private final StateExpr st;
	public LocalStateValDecl(Token token, Identifier id, StateExpr st) {
		super(token);
		this.id = id;
		this.st = st;
	}
	public Identifier getId() {
		return id;
	}
	public StateExpr getState() {
		return st;
	}
}
