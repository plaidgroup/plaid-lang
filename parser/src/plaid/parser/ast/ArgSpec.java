package plaid.parser.ast;

import plaid.parser.Token;

public class ArgSpec extends ASTNode {
	
	public static final ArgSpec EMPTY = EmptyArgSpec.getEmptyArgSpec();
	private final TypeDecl preType;
	private final TypeDecl postType;
	
	public ArgSpec(Token t, TypeDecl preType, TypeDecl postType) {
		super(t);
		this.preType = preType;
		if (postType == null) {
			this.postType = preType;
		} 
		else {
			this.postType = postType;
		}
	}
	
	public TypeDecl getPreType() {
		return preType;
	}

	public TypeDecl getPostType() {
		return postType;
	}

	@Override
	public String toString() {
		if ( this == EMPTY ) {
			return "";
		} else {
			return preType + ((postType!=TypeDecl.EMPTY)?">>"+postType:"");
		}
	}
}
