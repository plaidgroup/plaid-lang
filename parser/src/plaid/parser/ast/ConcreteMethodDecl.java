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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if ( getModifiers().size() > 0 ) sb.append(modifiersToString(getModifiers())+" ");
		sb.append("method ");
		if ( getType() != Type.EMPTY ) sb.append(getType().toString()+ " ");
		sb.append(getName());
		if ( getMetaArgsSpec().size() > 0) sb.append(metaArgSpecsToString(getMetaArgsSpec()));
		sb.append("(");
		if ( getArguments().size() > 0 ) sb.append(argsToString(getArguments()));
		sb.append(")");
		sb.append(body.toString());
		return sb.toString();
	}
}
