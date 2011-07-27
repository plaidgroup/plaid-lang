package plaid.parser.ast;

import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public class LambdaType extends Type {
	
	private final List<MetaType> metaTypes;
	private final List<ArgSpec> argTypes;
	private final Map<Identifier,ArgSpec> env;
	private final Type returnType;
	
	public LambdaType(Token t, List<MetaType> metaTypes,
			List<ArgSpec> argTypes, Map<Identifier, ArgSpec> env,
			Type returnType) {
		super(t);
		this.metaTypes = metaTypes;
		this.argTypes = argTypes;
		this.env = env;
		this.returnType = returnType;
	}
	
	public List<MetaType> getMetaTypes() {
		return metaTypes;
	}
	public List<ArgSpec> getArgTypes() {
		return argTypes;
	}
	public Map<Identifier, ArgSpec> getEnv() {
		return env;
	}
	public Type getReturnType() {
		return returnType;
	}
}