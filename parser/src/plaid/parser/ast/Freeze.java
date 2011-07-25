package plaid.parser.ast;

import plaid.parser.Token;


public class Freeze extends State {

	private final ID frozenVar;
	
	public Freeze(Token t, ID frozenVar) {
		super(t);
		this.frozenVar = frozenVar;
	}

	public ID getFrozenVar() {
		return frozenVar;
	}	
}
