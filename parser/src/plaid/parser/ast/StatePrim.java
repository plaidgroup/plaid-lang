package plaid.parser.ast;

import plaid.parser.Token;

public abstract class StatePrim extends State {

	public StatePrim(Token t) {
		super(t);
	}
}
