package plaid.parser.ast;

public class TypeParameter implements MetaParameter {
	private final ID id;
	private final ObjectType caseOf;
	
	
	public TypeParameter(ID id, ObjectType caseOf) {
		super();
		this.id = id;
		this.caseOf = caseOf;
	}

	public TypeParameter(ID id) {
		this(id, ObjectType.UNIT);
	}

	public ID getId() {
		return id;
	}

	public ObjectType getCaseOf() {
		return caseOf;
	}	
}
