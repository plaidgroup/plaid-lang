package plaid.parser.ast;

import plaid.parser.Token;

public class Specifier extends ASTNode {
	
	public enum SpecifierKind {VAR, VAL, EMPTY};
	public static final Specifier EMPTY = EmptySpecifier.getEmptySpecifier();
	
	private final SpecifierKind specifierKind;


	public Specifier(Token token, SpecifierKind specifierKind) {
		super(token);
		this.specifierKind = specifierKind;
	}


	public SpecifierKind getSpecifierKind() {
		return specifierKind;
	}
}
