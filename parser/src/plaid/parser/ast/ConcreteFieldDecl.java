package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ConcreteFieldDecl extends FieldDecl {
	private final Expr expr;

	public ConcreteFieldDecl(Token t, List<Modifier> modifiers, 
			Specifier specifier, Type type,
			Identifier name,  Expr expr) {
		super(t, modifiers, specifier, type, name);
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (getSpecifier().getSpecifierKind() != Specifier.SpecifierKind.EMPTY ) sb.append(getSpecifier().getSpecifierKind().toString().toLowerCase() + " ");
		if (getType() != Type.EMPTY ) sb.append(getType().toString()+ " ");
		sb.append(getName());
		sb.append("=");
		sb.append(expr.toString());
		sb.append(";");
		
		return sb.toString();
	}
}
