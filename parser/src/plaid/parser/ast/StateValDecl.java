package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class StateValDecl extends Decl {
	private final List<MetaArgSpec> metaArgsSpec;
	public StateValDecl(Token t, List<Modifier> modifiers, Identifier name,
			List<MetaArgSpec> metaArgsSpec) {
		super(t, modifiers, name);
		this.metaArgsSpec = metaArgsSpec;
	}
	public List<MetaArgSpec> getMetaArgsSpecs() {
		return metaArgsSpec;
	}
}
