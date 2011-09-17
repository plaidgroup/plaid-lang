package plaid.parser.ast;

import plaid.parser.Token;

public abstract class TypeDecl extends ASTNode{

	public TypeDecl(Token t) {
		super(t);
	}
	
	public static final TypeDecl DYNAMIC = new DynamicTypeDecl(DEFAULT_TOKEN);
	public static final TypeDecl UNIT = new UnitType(DEFAULT_TOKEN);	
	public static final TypeDecl VOID = new UnitType(DEFAULT_TOKEN);
	public static final TypeDecl EMPTY = EmptyType.getEmptyType();
}
