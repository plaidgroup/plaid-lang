package plaid.parser.ast;


import plaid.parser.Token;

public class NominalObjectType extends Type {

	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final Permission permission;
	private final QualifiedIdentifier state;
	
	public NominalObjectType(Token t, Permission permission, QualifiedIdentifier state) {
		super(t);
		this.permission=permission;
		this.state = state;
	}
	
	public Permission getPermission() {
		return permission;
	}

	public QualifiedIdentifier getState() {
		return state;
	}
}
