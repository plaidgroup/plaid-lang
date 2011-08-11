package plaid.parser.ast;

import plaid.parser.Token;

public final class ImmutablePermission extends Permission {

	public ImmutablePermission(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return "immutable";
	}
}
