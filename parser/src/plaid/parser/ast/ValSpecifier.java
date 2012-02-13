package plaid.parser.ast;

import plaid.parser.Token;

public final class ValSpecifier extends Specifier {

	public ValSpecifier(Token token) {
		super(token);
	}
	
	@Override
	public String toString() { 
		return "val";
	}

}
