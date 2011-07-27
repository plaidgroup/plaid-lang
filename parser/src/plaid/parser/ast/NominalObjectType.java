package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class NominalObjectType extends Type {

	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final Permission permission;
	private final QualifiedIdentifier state;
	private final List<MetaArgument> metaArgs;
	
	public NominalObjectType(Token t, Permission permission, QualifiedIdentifier state, List<MetaArgument> metaArgs) {
		super(t);
		this.permission=permission;
		this.state = state;
		this.metaArgs = metaArgs;
	}
	
	public Permission getPermission() {
		return permission;
	}

	public QualifiedIdentifier getState() {
		return state;
	}

	public List<MetaArgument> getMetaArgs() {
		return metaArgs;
	}

}
