package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class StateValDecl extends Decl {
	private final List<MetaArg> metaArgsSpec;

	public StateValDecl(Token t, List<Modifier> modifiers, Identifier name,
			List<MetaArg> metaArgsSpec) {
		super(t, modifiers, name);
		this.metaArgsSpec = metaArgsSpec;
	}

	public List<MetaArg> getMetaArgsSpecs() {
		return metaArgsSpec;
	}
}
