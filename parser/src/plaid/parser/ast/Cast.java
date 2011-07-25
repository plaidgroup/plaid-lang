package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expression {
	private Type type;
	private Expression exp;
	
	public Cast(Token t, Type type, Expression exp) {
		super(t);
		this.type = type;
		this.exp = exp;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Expression getExp() {
		return this.exp;
	}
}
