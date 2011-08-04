package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expr {
	private final Type type;
	private final Expr expression;
	
	public Cast(Token t, Expr expression, Type type) {
		super(t);
		this.type = type;
		this.expression = expression;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Expr getExpr() {
		return this.expression;
	}

}
