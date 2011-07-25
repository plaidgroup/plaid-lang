package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class GroupDecl extends Decl {
		
	public GroupDecl(Token token, List<Modifier> modifiers, Identifier name) {
		super(token, modifiers, name);
	}
}
