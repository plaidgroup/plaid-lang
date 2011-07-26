package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class AbstractFieldDecl extends FieldDecl {

	public AbstractFieldDecl(Token t, List<Modifier> modifiers, Specifier specifier, 
			Type type, Identifier name) {
		super(t, modifiers, specifier, name);
	}

}
