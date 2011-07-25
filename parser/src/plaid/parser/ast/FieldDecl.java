package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class FieldDecl extends Decl {

	private final Specifier specifier;
	private final Identifier identifier;
	public FieldDecl(Token t, List<Modifier> modifiers,
			Identifier name, Specifier specifier, Identifier identifier) {
		super(t, modifiers, name);
		this.specifier = specifier;
		this.identifier = identifier;
	}
	public Specifier getSpecifier() {
		return specifier;
	}
	public Identifier getIdentifier() {
		return identifier;
	}
}
