package plaid.parser.ast;

import plaid.parser.Token;


public class InfixOperatorExpr extends OperatorExpr {
	private final Expr left;
	private final Expr right;
	
	public InfixOperatorExpr(Token token, Expr left, Identifier op, Expr right) {
		super(token, op);
		this.left = left;
		this.right = right;
	}

	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		return left.toString() + getOperator() + right.toString();
	}
}
