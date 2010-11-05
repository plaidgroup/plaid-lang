package plaid.compilerjava.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plaid.compilerjava.AST.ID;
import plaid.compilerjava.coreparser.Token;

public class LambdaType implements Type {
	
	public static final LambdaType DEFAULTLAMBDATYPE = 
		new LambdaType(null, ChangeType.DYN, new HashMap<ID,ChangeType>(), PermType.DYN);
	
	private final Token t;
	private final List<ChangeType> argTypes;
	private final Map<ID,ChangeType> sideEffects;
	private final PermType returnType;
	
	public LambdaType(Token t, List<ChangeType> argTypes, Map<ID,ChangeType> sideEffects,
			PermType returnType) {
		super();
		this.t = t;
		this.argTypes = argTypes;
		this.sideEffects = sideEffects;
		this.returnType = returnType;
	}
	
	public LambdaType(Token t, ChangeType argType, Map<ID, ChangeType> sideEffects, PermType returnType) {
		this.t = t;
		this.argTypes = new ArrayList<ChangeType>();
		argTypes.add(argType);
		this.sideEffects = sideEffects;
		this.returnType = returnType;
		
	}

	public List<ChangeType> getArgType() {
		return Collections.unmodifiableList(argTypes);
	}

	public Map<ID,ChangeType> getSideEffects() {
		return sideEffects;
	}

	public PermType getReturnType() {
		return returnType;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("(");
		for ( ChangeType c : argTypes) s.append(c.toString() + ", "); //TODO: remove trailing comma
		s.append(")[");
		for (ID eVar : sideEffects.keySet()) s.append(sideEffects.get(eVar).toString() + " " + eVar.getName() + ","); //TODO: remove ending comma
		
		s.append("] -> " + returnType.toString());
		
		return s.toString();
	}
	
}
