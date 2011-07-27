package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class ConcreteStateDecl extends StateDecl {
	private final State stateBinding;

	public ConcreteStateDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<MetaArgSpec> metaArgsSpec,
			QualifiedIdentifier caseOf, List<Expression> metaCaseOfArgs,
			State stateBinding) {
		super(t, modifiers, name, metaArgsSpec, caseOf, metaCaseOfArgs);
		this.stateBinding = stateBinding;
	}

	public State getStateBinding() {
		return stateBinding;
	}
}
