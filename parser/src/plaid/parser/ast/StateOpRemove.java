package plaid.parser.ast;

import plaid.parser.Token;

public class StateOpRemove extends StateOp {
	protected final Identifier name;
	
	public StateOpRemove(Token token, Identifier name) {
		super(token);
		this.name = name;
	}
  
	public Identifier getName() {
		return name;
	}

	@Override
	public String toString() {
		return "remove " + name + ";";
	}
}
