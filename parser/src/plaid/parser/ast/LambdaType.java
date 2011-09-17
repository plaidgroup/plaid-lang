package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class LambdaType extends TypeDeclaration {
	
	private final List<StaticType> metaTypes;
	private final List<ArgSpec> argTypes;
	private final List<Arg> env;
	private final TypeDeclaration returnType;
	
	public LambdaType(Token t, List<StaticType> metaTypes,
			List<ArgSpec> argTypes, List<Arg> env,
			TypeDeclaration returnType) {
		super(t);
		this.metaTypes = metaTypes;
		this.argTypes = argTypes;
		this.env = env;
		this.returnType = returnType;
	}
	
	public List<StaticType> getMetaTypes() {
		return metaTypes;
	}
	public List<ArgSpec> getArgTypes() {
		return argTypes;
	}
	public List<Arg> getEnv() {
		return env;
	}
	public TypeDeclaration getReturnType() {
		return returnType;
	}
}
