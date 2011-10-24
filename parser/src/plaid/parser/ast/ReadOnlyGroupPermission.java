package plaid.parser.ast;

import plaid.parser.Token;

public class ReadOnlyGroupPermission extends GroupPermission {

	public ReadOnlyGroupPermission(Token token) {
		super(token);
	}
	
	@Override
	public String toString() {
		return "readOnly";
	}

}
