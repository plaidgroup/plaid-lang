package plaid.parser.ast;

import plaid.parser.Token;

public final class OverrideModifier extends Modifier {

	public OverrideModifier(Token t) {
		super(t);
	}
	
	@Override
	public String toString() {
		return "override";
	}

}
