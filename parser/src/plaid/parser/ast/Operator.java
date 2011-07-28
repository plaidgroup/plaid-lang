package plaid.parser.ast;

import plaid.parser.Token;

public class Operator extends Expression {
	protected final Identifier operator;
	
	public Operator(Token token, Identifier operator) {
		super(token);
		this.operator = operator;
	}
	
	public Identifier getOperator() {
		return operator;
	}

}
