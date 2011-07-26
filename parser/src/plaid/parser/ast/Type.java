package plaid.parser.ast;

import plaid.parser.Token;

public abstract class Type extends ASTNode{

	public Type(Token t) {
		super(t);
	}
	
	public static final Type DYNAMIC = new DynamicType(DEFAULT_TOKEN);
	public static final Type UNIT = new UnitType(DEFAULT_TOKEN);	
	public static final Type VOID = new UnitType(DEFAULT_TOKEN);
}
