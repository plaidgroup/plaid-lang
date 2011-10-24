package plaid.parser.ast;

import plaid.parser.Token;

public final class MutableGroupPermission extends GroupPermission {

	public MutableGroupPermission(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return "mutable";
	}
}
