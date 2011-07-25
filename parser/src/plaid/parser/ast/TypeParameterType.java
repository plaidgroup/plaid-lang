package plaid.parser.ast;

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
