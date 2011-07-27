package plaid.parser.ast;


import java.util.List;

import plaid.parser.Token;

public class NominalObjectType extends Type {

	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final Permission permission;
	private final QualifiedIdentifier state;
	private final List<MetaType> metaTypes;
	
	public NominalObjectType(Token t, Permission permission, QualifiedIdentifier state, List<MetaType> metaTypes) {
		super(t);
		this.permission=permission;
		this.state = state;
		this.metaTypes = metaTypes;
	}
	
	public Permission getPermission() {
		return permission;
	}

	public QualifiedIdentifier getState() {
		return state;
	}
	
	public List<MetaType> getMetaTypes() {
		return metaTypes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if ( permission != Permission.EMPTY ) sb.append(permission.toString()+" ");
		sb.append(state.toString());
		if ( metaTypes.size() > 0 ) {
			sb.append("<");
			sb.append(metaTypes.toString());
			for (int i = 1; i < metaTypes.size(); i++) {
				sb.append(","+metaTypes.toString());
			}
			sb.append(">");
		}
		return sb.toString();
	}
}
