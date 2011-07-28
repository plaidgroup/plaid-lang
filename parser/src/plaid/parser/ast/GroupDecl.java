package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class GroupDecl extends Decl {
	protected final List<Modifier> modifiers;
	
	public GroupDecl(Token token, List<Modifier> modifiers, Identifier name) {
		super(token, modifiers, name);
		this.modifiers = modifiers;
	}

	@Override
	public String toString() {
		return "group " + name + " = new group;";
	}
}
