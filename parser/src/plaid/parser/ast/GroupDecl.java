package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class GroupDecl extends Decl {
	protected final List<Modifier> modifiers;
	protected final Identifier name;
	
	public GroupDecl(Token token, List<Modifier> modifiers, Identifier name) {
		super(token, modifiers, name);
		this.modifiers = modifiers;
		this.name = name;
	}

	@Override
	public String toString() {
		return "group " + name + " = new group;";
	}
}
