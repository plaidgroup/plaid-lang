package plaid.parser.ast;

import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public final class ConcreteMethodDecl extends MethodDecl {
	private final Expression body;

	public ConcreteMethodDecl(Token t, List<Modifier> modifiers, Type type,
			Identifier name, List<MetaArgSpec> metaArgsSpec,
			List<Arg> arguments, Map<Identifier, ArgSpec> env, Expression body) {
		super(t, modifiers, type, name, metaArgsSpec, arguments, env);
		this.body = body;
	}

	public Expression getBody() {
		return body;
	}
	
}
