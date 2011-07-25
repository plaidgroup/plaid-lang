package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class AbstractFieldDecl extends Decl {

	private final Specifier specifier;
	private final Identifier identifier;
	public AbstractFieldDecl(Token t, List<Modifier> modifiers,
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
