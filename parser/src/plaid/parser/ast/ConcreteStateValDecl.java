package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class ConcreteStateValDecl extends StateValDecl {
	private final StateExpression stateBinding;

	
	
	public ConcreteStateValDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<MetaArgSpec> metaArgsSpec, StateExpression stateBinding) {
		super(t, modifiers, name, metaArgsSpec);
		this.stateBinding = stateBinding;
	}



	public StateExpression getStateBinding() {
		return stateBinding;
	}
	
}
