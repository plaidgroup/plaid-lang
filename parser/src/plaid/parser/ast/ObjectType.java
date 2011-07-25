package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ObjectType extends Type {

	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final QualifiedIdentifier nominalType;
	private final List<MetaArgument> metaArgs;
	
	public ObjectType(Token t, QualifiedIdentifier typeAbbrev) {
		this(t,typeAbbrev,null);
	}
	
	public ObjectType(Token t, QualifiedIdentifier typeAbbrev, List<MetaArgument> metaArgs) {
		super(t);
		this.nominalType = typeAbbrev;	
		this.metaArgs = metaArgs;
	}

	public QualifiedIdentifier getNominalType() {
		return nominalType;
	}

	public List<MetaArgument> getMetaArgs() {
		return metaArgs;
	}
}
