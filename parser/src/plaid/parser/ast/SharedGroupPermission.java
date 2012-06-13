package plaid.parser.ast;

import plaid.parser.Token;

public final class SharedGroupPermission extends GroupPermission {

	public SharedGroupPermission(Token token) {
		super(token);
	}
	
	@Override
	public String toString() {
		return "shared";
	}

}
