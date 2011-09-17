package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expr {
	private final TypeDeclaration targetType;
	private final Expr expression;
	
	public Cast(Token t, Expr expression, TypeDeclaration targetType) {
		super(t);
		this.targetType = targetType;
		this.expression = expression;
	}
	
	public TypeDeclaration getTargetType() {
		return this.targetType;
	}
	
	public Expr getExpr() {
		return this.expression;
	}

}
