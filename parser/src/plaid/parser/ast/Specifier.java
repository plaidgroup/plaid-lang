package plaid.parser.ast;

import plaid.parser.Token;

public class Specifier extends ASTNode {
	
	public enum SpecifierKind {VAR, VAL};
	
	private final SpecifierKind sk;


	public Specifier(Token token, SpecifierKind sk) {
		super(token);
		this.sk = sk;
	}


	public SpecifierKind getSpecifierKind() {
		return sk;
	}
}
