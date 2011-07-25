package plaid.parser.ast;

import plaid.parser.Token;


public class Freeze implements State {

	private Token token;
	private ID frozenVar;
	
	public Freeze(ID frozenVar) {
		this(null,frozenVar);
	}
	
	public Freeze(Token t, ID frozenVar) {
		this.token = t;
		this.frozenVar = frozenVar;
	}

	@Override
	public Token getToken() {
		return this.token;
	}

	@Override
	public boolean hasToken() {
		return this.token != null;
	}

	public ID getFrozenVar() {
		return frozenVar;
	}

	public void setFrozenVar(ID frozenVar) {
		this.frozenVar = frozenVar;
	}
	
	
}
