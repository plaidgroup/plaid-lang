package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expr {
	private final Type type;
	private final Expr exp;
	
	public Cast(Token t, Expr exp, Type type) {
		super(t);
		this.type = type;
		this.exp = exp;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Expr getExp() {
		return this.exp;
	}

}
