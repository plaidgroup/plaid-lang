package plaid.compilerjava.types;

import java.util.List;

public class LambdaType implements Type {
	
	private final ChangeType argType;
	private final List<EnvironmentType> sideEffects;
	private final PermType returnType;
	
	public LambdaType(ChangeType argType, List<EnvironmentType> sideEffects,
			PermType returnType) {
		super();
		this.argType = argType;
		this.sideEffects = sideEffects;
		this.returnType = returnType;
	}

	public ChangeType getArgType() {
		return argType;
	}

	public List<EnvironmentType> getSideEffects() {
		return sideEffects;
	}

	public PermType getReturnType() {
		return returnType;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("(");
		s.append(argType.toString() + ")[");
		for (EnvironmentType c : sideEffects) s.append(c.toString() + ","); //TODO: remove ending comma
		
		s.append("] -> " + returnType.toString());
		
		return s.toString();
	}
	
}
