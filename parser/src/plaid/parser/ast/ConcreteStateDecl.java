package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class ConcreteStateDecl extends StateDecl {
	private final StateExpr stateBinding;

	public ConcreteStateDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<MetaArg> metaArgsSpec,
			QualifiedIdentifier caseOf, List<Expr> metaCaseOfArgs,
			StateExpr stateBinding) {
		super(t, modifiers, name, metaArgsSpec, caseOf, metaCaseOfArgs);
		this.stateBinding = stateBinding;
	}

	public StateExpr getStateBinding() {
		return stateBinding;
	}
}
