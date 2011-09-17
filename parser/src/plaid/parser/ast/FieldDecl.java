package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class FieldDecl extends Decl {

	private final Specifier specifier;
	private final TypeDeclaration fieldType; 
	
	public FieldDecl(Token t, List<Modifier> modifiers,
			Specifier specifier, TypeDeclaration type, Identifier name) {
		super(t, modifiers, name);
		this.specifier = specifier;
		this.fieldType = type;
	}
	
	public Specifier getSpecifier() {
		return specifier;
	}
	
	public TypeDeclaration getFieldType() {
		return fieldType;
	}
}
