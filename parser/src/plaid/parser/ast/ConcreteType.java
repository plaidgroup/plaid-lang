package plaid.parser.ast;

import plaid.parser.Token;

public class ConcreteType extends Type {
	private final Permission permission;
	private final Structure structure;

	public ConcreteType(Token token, Permission permission, Structure structure) {
		super(token);
		this.permission = permission;
		this.structure = structure;
	}
	public static final Type EMPTY = EmptyType.getEmptyType();

	public Permission getPermission() {
		return permission;
	}
	public Structure getStructure() {
		return structure;
	}

	@Override
	public String toString() {
		if (permission != Permission.EMPTY) {
			return permission.toString() + " " + structure.toString();
		} else {
			return structure.toString();
		}
	}
}
