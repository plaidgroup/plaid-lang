package plaid.parser.ast;

import plaid.parser.Token;

public final class RequiresModifier extends Modifier {

	public RequiresModifier(Token t) {
		super(t);
	}
	
	@Override
	public String toString() {
		return "requires";
	}

}
