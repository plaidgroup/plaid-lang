package plaid.parser.ast;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public class LambdaType extends Type {
	
	private final List<ChangeType> argTypes;
	private final Map<Identifier,ChangeType> environmentTypes;
	private final List<MetaType> metaTypes;
	private final PermType returnType;

	
	public LambdaType(Token t, List<ChangeType> argTypes, Map<Identifier,ChangeType> sideEffects, List<MetaType> metaTypes, PermType returnType) {
		super(t);
		this.argTypes = argTypes;
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
		this.metaTypes = metaTypes;
	}
	
	public List<ChangeType> getArgTypes() {
		return Collections.unmodifiableList(argTypes);
	}

	public Map<Identifier,ChangeType> getEnvironmentTypes() {
		return environmentTypes;
	}

	public PermType getReturnType() {
		return returnType;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("(");
		for ( ChangeType c : argTypes) s.append(c.toString() + ", "); //TODO: remove trailing comma
		s.append(")[");
		for (Identifier eVar : environmentTypes.keySet()) s.append(environmentTypes.get(eVar).toString() + " " + eVar.getName() + ","); //TODO: remove ending comma
		
		s.append("] -> " + returnType.toString());
		
		return s.toString();
	}

	public List<MetaType> getMetaTypes() {
		return metaTypes;
	}
	
}
