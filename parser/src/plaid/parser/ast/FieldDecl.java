package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class FieldDecl extends Decl {

	private final Specifier specifier;
	public FieldDecl(Token t, List<Modifier> modifiers,
			Specifier specifier, Identifier name) {
		super(t, modifiers, name);
		this.specifier = specifier;
	}
	public Specifier getSpecifier() {
		return specifier;
	}
}
