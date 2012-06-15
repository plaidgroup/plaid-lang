package plaid.parser.ast;

import plaid.parser.Token;

public final class ExclusiveGroupPermission extends GroupPermission {

	public ExclusiveGroupPermission(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return "exclusive";
	}
}
