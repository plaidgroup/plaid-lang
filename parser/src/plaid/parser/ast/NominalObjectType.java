package plaid.parser.ast;


import java.util.List;

import plaid.parser.Token;

public class NominalObjectType extends Type {

	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final Permission permission;
	private final QualifiedIdentifier stateQi;
	private final List<StaticType> metaTypes;
	
	public NominalObjectType(Token t, Permission permission, QualifiedIdentifier st, List<StaticType> metaTypes) {
		super(t);
		this.permission=permission;
		this.stateQi = st;
		this.metaTypes = metaTypes;
	}
	
	public Permission getPermission() {
		return permission;
	}

	public QualifiedIdentifier getStateQi() {
		return stateQi;
	}
	
	public List<StaticType> getMetaTypes() {
		return metaTypes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if ( permission != Permission.EMPTY ) sb.append(permission.toString()+" ");
		sb.append(stateQi.toString());
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
