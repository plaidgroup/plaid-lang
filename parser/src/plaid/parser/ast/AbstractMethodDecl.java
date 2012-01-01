package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class AbstractMethodDecl extends MethodDecl {

	public AbstractMethodDecl(Token t,
			                  List<Annotation> annotations,
							  List<Modifier> modifiers,
							  Type returnType,
							  Identifier name,
							  List<StaticArg> StaticArgsSpec,
							  List<Arg> arguments,
							  List<Arg> env) {
		super(t, annotations, modifiers, returnType, name, StaticArgsSpec, arguments, env);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if ( getAnnotations().size() > 0 ) sb.append(annotationsToString(getAnnotations())+" ");
		if ( getModifiers().size() > 0 ) sb.append(modifiersToString(getModifiers())+" ");
		sb.append("method ");
		if ( getReturnType() != Type.EMPTY ) sb.append(getReturnType().toString()+ " ");
		sb.append(getName());
		if ( getStaticArgsSpec().size() > 0) sb.append(StaticArgSpecsToString(getStaticArgsSpec()));
		sb.append("(");
		if ( getArguments().size() > 0 ) sb.append(argsToString(getArguments()));
		sb.append(")");
		sb.append(";");
		return sb.toString();
	}
}
