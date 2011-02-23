package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class TypeParameterType implements MetaType, Type {
	private final ID id;
	private final Type caseOf;
	
	public TypeParameterType(ID id, Type caseOf) {
		super();
		this.id = id;
		this.caseOf = caseOf;
	}

	public ID getId() {
		return id;
	}

	public Type getCaseOf() {
		return caseOf;
	}
}
