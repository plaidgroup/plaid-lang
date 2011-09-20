package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expr {
	private final TypeDecl targetType;
	private final Expr expression;
	
	public Cast(Token t, Expr expression, TypeDecl targetType) {
		super(t);
		this.targetType = targetType;
		this.expression = expression;
	}
	
	public TypeDecl getTargetType() {
		return this.targetType;
	}
	
	public Expr getExpr() {
		return this.expression;
	}

}
