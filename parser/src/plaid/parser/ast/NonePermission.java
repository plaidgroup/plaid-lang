package plaid.parser.ast;

import plaid.parser.Token;

public final class NonePermission extends Permission {

	public NonePermission(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return "none";
	}
}
