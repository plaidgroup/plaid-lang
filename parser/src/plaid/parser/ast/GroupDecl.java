package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class GroupDecl extends Decl {
	
	public GroupDecl(Token token, List<Annotation> annotations, List<Modifier> modifiers, Identifier name) {
		super(token, annotations, modifiers, name);
	}

	@Override
	public String toString() {
		return "group " + name + " = new group;";
	}
}
