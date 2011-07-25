package plaid.parser.ast;

import plaid.parser.Token;

public class Import implements ASTNode {
	
	private final Token token;
	
	public Import(Token token) {
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}

	@Override
	public boolean hasToken() {
		return this.token != null;
	}

}
