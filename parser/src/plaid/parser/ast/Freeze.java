package plaid.parser.ast;

import plaid.parser.Token;


public class Freeze extends StatePrim {

	private final Identifier frozenVar;
	
	public Freeze(Token t, Identifier frozenVar) {
		super(t);
		this.frozenVar = frozenVar;
	}

	public Identifier getFrozenVar() {
		return frozenVar;
	}	
}
