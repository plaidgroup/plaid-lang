package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expr {
	private final Type targetType;
	private final Expr expression;
	
	public Cast(Token t, Expr expression, Type targetType) {
		super(t);
		this.targetType = targetType;
		this.expression = expression;
	}
	
	public Type getTargetType() {
		return this.targetType;
	}
	
	public Expr getExpr() {
		return this.expression;
	}

}
