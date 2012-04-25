package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class LambdaStructure extends Structure {
	
	private final List<StaticType> metaTypes;
	private final List<ArgSpec> argTypes;
	private final Type returnType;
	
	public LambdaStructure(Token t, List<StaticType> metaTypes,
			List<ArgSpec> argTypes,
			Type returnType) {
		super(t);
		this.metaTypes = metaTypes;
		this.argTypes = argTypes;
		this.returnType = returnType;
	}
	
	public List<StaticType> getMetaTypes() {
		return metaTypes;
	}
	public List<ArgSpec> getArgTypes() {
		return argTypes;
	}
	public Type getReturnType() {
		return returnType;
	}
}
