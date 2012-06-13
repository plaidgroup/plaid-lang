package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class AbstractFieldDecl extends FieldDecl {

	//optional - specifier, identifier
	public AbstractFieldDecl(Token t, List<Annotation> annotations, List<Modifier> modifiers, Specifier specifier, 
			Type fieldType, Identifier name) {
		super(t, annotations, modifiers, specifier, fieldType, name);
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if ( getAnnotations().size() > 0 ) sb.append(annotationsToString(getAnnotations())+" ");
		if (getSpecifier() != Specifier.EMPTY ) sb.append(getSpecifier().toString() + " ");
		if (getFieldType() != Type.EMPTY ) sb.append(getFieldType().toString()+ " ");
		sb.append(getName());
		sb.append(";");
		
		return sb.toString();
	}
}
