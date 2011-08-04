package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class TypeParameterType implements MetaType, Type {
	private final ID id;
	private final ObjectType caseOf;
	
	public TypeParameterType(ID id, ObjectType caseOf) {
		super();
		this.id = id;
		this.caseOf = caseOf;
	}

	public ID getID() {
		return id;
	}

	public ObjectType getCaseOf() {
		return caseOf;
	}
}
