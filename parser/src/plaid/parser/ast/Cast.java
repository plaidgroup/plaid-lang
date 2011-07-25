package plaid.parser.ast;

import plaid.parser.Token;

public class Cast extends Expression {
	private PermType pt;
	private Expression exp;
	
	public Cast(Token t, PermType pt, Expression exp) {
		super(t);
		this.pt = pt;
		this.exp = exp;
	}
	
	public PermType getPermType() {
		return this.pt;
	}
	
	public Expression getExp() {
		return this.exp;
	}
}
