package plaid.parser.ast;

import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public final class AbstractMethodDecl extends MethodDecl {

	public AbstractMethodDecl(Token t, List<Modifier> modifiers, Type type,
			Identifier name, List<MetaArgSpec> metaArgsSpec,
			List<Arg> arguments, Map<Identifier, ArgSpec> env) {
		super(t, modifiers, type, name, metaArgsSpec, arguments, env);
	}
	
}
