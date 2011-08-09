package plaid.parser.ast;

import plaid.parser.Token;

public final class VarSpecifier extends Specifier {

	public VarSpecifier(Token token) {
		super(token);
	}
	
	@Override
	public String toString() { 
		return "var";
	}
}
