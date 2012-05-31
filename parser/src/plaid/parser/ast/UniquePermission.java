package plaid.parser.ast;

import plaid.parser.Token;

public final class UniquePermission extends Permission {

	public UniquePermission(Token token) {
		super(token);
	}
	
	@Override
	public String toString() {
		return "unique";
	}

}
