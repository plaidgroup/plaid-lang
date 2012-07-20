package plaid.parser.ast;

import plaid.parser.Token;

public final class ProtectedGroupPermission extends GroupPermission {

	public ProtectedGroupPermission(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return "protected";
	}
}
