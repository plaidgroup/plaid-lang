package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class AbstractStateValDecl extends StateValDecl {

	public AbstractStateValDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<StaticArg> StaticArgsSpec) {
		super(t, modifiers, name, StaticArgsSpec);
	}
	
}
