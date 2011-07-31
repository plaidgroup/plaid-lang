package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class TypeArgType extends MetaType {
	protected final Identifier name;
	protected final List<MetaArg> metaArgsSpec;
	protected final QualifiedIdentifier qi;
	protected final List<Expr> metaArgs;
		
	public TypeArgType(Token t, Identifier name, List<MetaArg> metaArgsSpec, QualifiedIdentifier qi, List<Expr> metaArgs) {
		super(t);
		this.name = name;
		this.metaArgsSpec = metaArgsSpec;
		this.qi = qi;
		this.metaArgs = metaArgs;
	}

}
