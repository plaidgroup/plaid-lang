package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class StateValDecl extends Decl {
	private final List<StaticArg> StaticArgsSpec;

	public StateValDecl(Token t, List<Modifier> modifiers, Identifier name,
			List<StaticArg> StaticArgsSpec) {
		super(t, modifiers, name);
		this.StaticArgsSpec = StaticArgsSpec;
	}

	public List<StaticArg> getStaticArgsSpecs() {
		return StaticArgsSpec;
	}
}
