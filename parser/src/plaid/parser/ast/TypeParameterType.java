package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class TypeParameterType extends MetaType {
	protected final Identifier name;
	protected final List<MetaArgSpec> metaArgsSpec;
	protected final QualifiedIdentifier qi;
	protected final List<Expression> metaArgs;
		
	public TypeParameterType(Token t, Identifier name, List<MetaArgSpec> metaArgsSpec, QualifiedIdentifier qi, List<Expression> metaArgs) {
		super(t);
		this.name = name;
		this.metaArgsSpec = metaArgsSpec;
		this.qi = qi;
		this.metaArgs = metaArgs;
	}

}
