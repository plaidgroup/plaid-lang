package plaid.parser;

import plaid.parser.ast.Identifier;

public class StateOpRemove extends StateOp {
	protected final Identifier name;
	
	public StateOpRemove(Token token, Identifier name) {
		super(token);
		this.name = name;
	}
  
	public Identifier getName() {
		return name;
	}
	
}
