package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class TypeParameterType implements MetaType, Type {
	private final ID id;
	private final PermType caseOf;
	
	public TypeParameterType(ID id, PermType caseOf) {
		super();
		this.id = id;
		this.caseOf = caseOf;
	}

	public ID getId() {
		return id;
	}

	public PermType getCaseOf() {
		return caseOf;
	}
}
