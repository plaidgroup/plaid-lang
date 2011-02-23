package plaid.compilerjava.AST;

import plaid.compilerjava.types.PermType;
import plaid.compilerjava.types.Type;

public class TypeParameter implements MetaParameter {
	private final ID id;
	private final Type caseOf;
	
	
	public TypeParameter(ID id, Type caseOf) {
		super();
		this.id = id;
		this.caseOf = caseOf;
	}

	public TypeParameter(ID id) {
		this(id, Type.UNIT);
	}

	public ID getId() {
		return id;
	}

	public Type getCaseOf() {
		return caseOf;
	}	
}
