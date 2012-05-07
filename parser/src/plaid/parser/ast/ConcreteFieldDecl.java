package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ConcreteFieldDecl extends FieldDecl {
	private final Expr expression;

	public ConcreteFieldDecl(Token t, List<Annotation> annotations, List<Modifier> modifiers, 
			Specifier specifier, Type fieldType,
			Identifier name,  Expr expr) {
		super(t, annotations, modifiers, specifier, fieldType, name);
		this.expression = expr;
	}

	public Expr getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if ( getAnnotations().size() > 0 ) sb.append(annotationsToString(getAnnotations())+" ");
		if (getSpecifier()!= Specifier.EMPTY ) sb.append(getSpecifier().toString() + " ");
		if (getFieldType() != Type.EMPTY ) sb.append(getFieldType().toString()+ " ");
		sb.append(getName());
		sb.append("=");
		sb.append(expression.toString());
		sb.append(";");
		
		return sb.toString();
	}
}
