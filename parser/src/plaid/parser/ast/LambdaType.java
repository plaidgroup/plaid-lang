package plaid.parser.ast;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public class LambdaType extends Type {
	
	private final List<ArgSpec> argTypes;
	private final Map<Identifier,ArgSpec> environmentTypes;
	private final List<MetaType> metaTypes;
	private final Type returnType;

	
	public LambdaType(Token t, List<ArgSpec> argTypes, Map<Identifier,ArgSpec> sideEffects, List<MetaType> metaTypes, Type returnType) {
		super(t);
		this.argTypes = argTypes;
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
		this.metaTypes = metaTypes;
	}
	
	public List<ArgSpec> getArgTypes() {
		return Collections.unmodifiableList(argTypes);
	}

	public Map<Identifier,ArgSpec> getEnvironmentTypes() {
		return environmentTypes;
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<MetaType> getMetaTypes() {
		return metaTypes;
	}
	
}
