package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ConcreteFieldDecl extends FieldDecl {
	private final Expression expr;

	public ConcreteFieldDecl(Token t, List<Modifier> modifiers, Specifier specifier,
			Identifier name,  Expression expr) {
		super(t, modifiers, specifier, name);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	
}
