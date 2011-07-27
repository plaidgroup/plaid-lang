package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class StateValDecl extends Decl {
	private final State state;
	private final List<MetaArgSpec> metaArgsSpec;
	public StateValDecl(Token t, List<Modifier> modifiers, Identifier name,
			List<MetaArgSpec> metaArgsSpec, State state) {
		super(t, modifiers, name);
		this.state = state;
		this.metaArgsSpec = metaArgsSpec;
	}
	public State getState() {
		return state;
	}
	public List<MetaArgSpec> getMetaArgsSpecs() {
		return metaArgsSpec;
	}
}
