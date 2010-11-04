package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class EnvironmentType {

	private final ChangeType typeChange;
	private final ID var;
	
	public EnvironmentType(ChangeType typeChange, ID var) {
		super();
		this.typeChange = typeChange;
		this.var = var;
	}

	public ChangeType getTypeChange() {
		return typeChange;
	}

	public ID getVar() {
		return var;
	}
	
}
