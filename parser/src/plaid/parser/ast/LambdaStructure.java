package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class LambdaStructure extends Structure {
	
	private final List<StaticType> metaTypes;
	private final List<ArgSpec> argTypes;
	private final List<Arg> env;
	private final Type returnType;
	
	public LambdaStructure(Token t, List<StaticType> metaTypes,
			List<ArgSpec> argTypes, List<Arg> env,
			Type returnType) {
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
	public Type getReturnType() {
		return returnType;
	}
}
