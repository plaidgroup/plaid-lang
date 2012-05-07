package plaid.parser.ast;

import plaid.parser.Token;

public final class ImmutableModifier extends Modifier {

	public ImmutableModifier(Token t) {
		super(t);
	}
	
	@Override
	public String toString() {
		return "immutable";
	}

}
