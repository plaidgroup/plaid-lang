package plaid.parser.ast;

import java.util.Collections;

import plaid.parser.Token;

public abstract class Type extends ASTNode{
	
	public Type(Token t) {
		super(t);
	}
	
	public static final Type DYNAMIC = 
		new ObjectType(DEFAULT_TOKEN, new QI(DEFAULT_TOKEN, Collections.singletonList("dynamic")));
	public static final Type UNIT = 
		new ObjectType(DEFAULT_TOKEN, new QI(DEFAULT_TOKEN, Collections.singletonList("unit")));
	
	
}
