package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ConcreteFieldDecl extends FieldDecl {
	private final Expr expression;

	public ConcreteFieldDecl(Token t, List<Modifier> modifiers, 
			Specifier specifier, Type type,
			Identifier name,  Expr expr) {
		super(t, modifiers, specifier, type, name);
		this.expression = expr;
	}

	public Expr getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (getSpecifier()!= Specifier.EMPTY ) sb.append(getSpecifier().toString() + " ");
		if (getType() != Type.EMPTY ) sb.append(getType().toString()+ " ");
		sb.append(getName());
		sb.append("=");
		sb.append(expression.toString());
		sb.append(";");
		
		return sb.toString();
	}
}
