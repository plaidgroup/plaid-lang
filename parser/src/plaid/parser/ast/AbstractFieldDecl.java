package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class AbstractFieldDecl extends FieldDecl {

	//optional - specifier, identifier
	public AbstractFieldDecl(Token t, List<Modifier> modifiers, Specifier specifier, 
			Type type, Identifier name) {
		super(t, modifiers, specifier, type, name);
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (getSpecifier() != Specifier.EMPTY ) sb.append(getSpecifier().toString() + " ");
		if (getType() != Type.EMPTY ) sb.append(getType().toString()+ " ");
		sb.append(getName());
		sb.append(";");
		
		return sb.toString();
	}
}
