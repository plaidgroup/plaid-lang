package plaid.parser.ast;

import plaid.parser.Token;


public class InfixOperator extends Operator {
	private final Expression left;
	private final Expression right;
	
	public InfixOperator(Token token, Expression left, Identifier op, Expression right) {
		super(token, op);
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		return left.toString() + getOperator() + right.toString();
	}
}
