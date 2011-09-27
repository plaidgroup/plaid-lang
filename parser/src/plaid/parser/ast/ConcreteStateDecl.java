package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class ConcreteStateDecl extends StateDecl {
	private final StateExpr stateBinding;

	public ConcreteStateDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<StaticArg> StaticArgsSpec,
			QualifiedIdentifier caseOf, List<Expr> metaCaseOfArgs,
			StateExpr stateBinding) {
		super(t, modifiers, name, StaticArgsSpec, caseOf, metaCaseOfArgs);
		this.stateBinding = stateBinding;
	}

	public StateExpr getStateBinding() {
		return stateBinding;
	}
}
