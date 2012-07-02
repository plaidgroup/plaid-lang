package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class ConcreteStateValDecl extends StateValDecl {
	private final StateExpr stateBinding;

	public ConcreteStateValDecl(Token t, List<Annotation> annotations, List<Modifier> modifiers,
			Identifier name, List<StaticArg> StaticArgsSpec, StateExpr stateBinding) {
		super(t, annotations, modifiers, name, StaticArgsSpec);
		this.stateBinding = stateBinding;
	}

	public StateExpr getStateBinding() {
		return stateBinding;
	}
	
}
