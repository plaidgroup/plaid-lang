package plaid.compilerjava.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plaid.compilerjava.AST.ID;
import plaid.compilerjava.AST.MetaParameter;
import plaid.compilerjava.coreparser.Token;

public class LambdaType implements Type {
	
	public static final LambdaType DEFAULTLAMBDATYPE = 
		new LambdaType(null, ChangeType.DYN, new HashMap<ID,ChangeType>(), PermType.getDynPT());
	
	private final Token t;
	private final List<ChangeType> argTypes;
	private final Map<ID,ChangeType> environmentTypes;
	private final List<MetaType> metaTypes;
	private final PermType returnType;
	
	public LambdaType(Token t, List<ChangeType> argTypes, Map<ID,ChangeType> sideEffects,
			PermType returnType) {
		super();
		this.t = t;
		this.argTypes = argTypes;
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
		this.metaTypes = new ArrayList<MetaType>();
	}
	
	public LambdaType(Token t, List<ChangeType> argTypes, Map<ID,ChangeType> sideEffects, List<MetaType> metaTypes, PermType returnType) {
		super();
		this.t = t;
		this.argTypes = argTypes;
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
		this.metaTypes = metaTypes;
	}
	
	public LambdaType(Token t, ChangeType argType, Map<ID, ChangeType> sideEffects, PermType returnType) {
		this.t = t;
		this.argTypes = new ArrayList<ChangeType>();
		argTypes.add(argType);
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
		this.metaTypes = new ArrayList<MetaType>();		
	}

	public LambdaType(Token t, ChangeType argType, Map<ID, ChangeType> sideEffects, List<MetaType> metaTypes, PermType returnType) {
		this.t = t;
		this.argTypes = new ArrayList<ChangeType>();
		argTypes.add(argType);
		this.environmentTypes = sideEffects;
		this.returnType = returnType;
		this.metaTypes = metaTypes;		
	}
	
	public List<ChangeType> getArgTypes() {
		return Collections.unmodifiableList(argTypes);
	}

	public Map<ID,ChangeType> getEnvironmentTypes() {
		return environmentTypes;
	}

	public PermType getReturnType() {
		return returnType;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("(");
		for ( ChangeType c : argTypes) s.append(c.toString() + ", "); //TODO: remove trailing comma
		s.append(")[");
		for (ID eVar : environmentTypes.keySet()) s.append(environmentTypes.get(eVar).toString() + " " + eVar.getName() + ","); //TODO: remove ending comma
		
		s.append("] -> " + returnType.toString());
		
		return s.toString();
	}
	
}
