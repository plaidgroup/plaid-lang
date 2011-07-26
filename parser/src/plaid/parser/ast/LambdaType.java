package plaid.parser.ast;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public class LambdaType extends Type {
	
	private final List<ArgSpec> argTypes;
	private final Map<Identifier,ArgSpec> environmentTypes;
	private final Type returnType;

	
	public LambdaType(Token t, List<ArgSpec> argTypes, Map<Identifier,ArgSpec> sideEffects, Type returnType) {
		super(t);
		this.argTypes = argTypes;
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
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

	@Override
	public boolean equivalent(ASTNode other) {
		if (other instanceof LambdaType) {
			LambdaType otherLambda = (LambdaType)other;
			for(int i=0; i<argTypes.size(); i++) {
				if(!this.argTypes.get(i).equivalent(otherLambda.argTypes.get(i))) {
					return false;
				}
			}
			for (Identifier key : environmentTypes.keySet()) {
				if(!this.environmentTypes.get(key).equivalent(otherLambda.environmentTypes.get(key))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
}
