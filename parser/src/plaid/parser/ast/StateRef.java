package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class StateRef extends StatePrim {
	private final Expression state;
	private final List<DeclOrStateOp> specializations;
	
	public StateRef(Token t, Expression state,
			List<DeclOrStateOp> specializations) {
		super(t);
		this.state = state;
		this.specializations = specializations;
	}

	public Expression getState() {
		return state;
	}

	public List<DeclOrStateOp> getSpecializations() {
		return specializations;
	}
	
	
	
	
}
