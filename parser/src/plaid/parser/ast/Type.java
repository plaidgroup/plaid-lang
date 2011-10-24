package plaid.parser.ast;

import plaid.parser.Token;

public abstract class Type extends ASTNode{
	public Type(Token token) {
		super(token);
	}
	public static final Type EMPTY = EmptyType.getEmptyType();
}
