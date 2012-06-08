package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class TypeArgType extends StaticType {
	protected final Identifier name;
	protected final List<StaticArg> StaticArgsSpec;
	protected final QualifiedIdentifier qi;
	protected final List<Expr> StaticArgs;
		
	public TypeArgType(Token t, Identifier name, List<StaticArg> StaticArgsSpec, QualifiedIdentifier qi, List<Expr> StaticArgs) {
		super(t);
		this.name = name;
		this.StaticArgsSpec = StaticArgsSpec;
		this.qi = qi;
		this.StaticArgs = StaticArgs;
	}

}
