package plaid.parser.ast;

import plaid.parser.Token;

public abstract class TypeDeclaration extends ASTNode{

	public TypeDeclaration(Token t) {
		super(t);
	}
	
	public static final TypeDeclaration DYNAMIC = new DynamicType(DEFAULT_TOKEN);
	public static final TypeDeclaration UNIT = new UnitType(DEFAULT_TOKEN);	
	public static final TypeDeclaration VOID = new UnitType(DEFAULT_TOKEN);
	public static final TypeDeclaration EMPTY = EmptyType.getEmptyType();
}
