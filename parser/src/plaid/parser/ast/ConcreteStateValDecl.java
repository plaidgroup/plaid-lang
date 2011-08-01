package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class ConcreteStateValDecl extends StateValDecl {
	private final StateExpr stateBinding;

	public ConcreteStateValDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<MetaArg> metaArgsSpec, StateExpr stateBinding) {
		super(t, modifiers, name, metaArgsSpec);
		this.stateBinding = stateBinding;
	}

	public StateExpr getStateBinding() {
		return stateBinding;
	}
	
}
