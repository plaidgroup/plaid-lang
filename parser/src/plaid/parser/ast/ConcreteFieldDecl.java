package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ConcreteFieldDecl extends FieldDecl {
	private final Expression expr;

	public ConcreteFieldDecl(Token t, List<Modifier> modifiers,
			Identifier name, Specifier specifier, Identifier identifier,
			Expression expr) {
		super(t, modifiers, name, specifier, identifier);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	
}
