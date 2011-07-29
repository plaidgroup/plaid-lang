package plaid.parser.ast;

import plaid.parser.Token;

public abstract class Specifier extends ASTNode {
	
	public static final Specifier EMPTY = EmptySpecifier.getEmptySpecifier();
	
	public Specifier(Token token) {
		super(token);
	}
}
