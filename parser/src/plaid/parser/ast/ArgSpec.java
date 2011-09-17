package plaid.parser.ast;

import plaid.parser.Token;

public class ArgSpec extends ASTNode {
	
	public static final ArgSpec EMPTY = EmptyArgSpec.getEmptyArgSpec();
	private final TypeDeclaration preType;
	private final TypeDeclaration postType;
	
	public ArgSpec(Token t, TypeDeclaration preType, TypeDeclaration postType) {
		super(t);
		this.preType = preType;
		if (postType == null) {
			this.postType = preType;
		} 
		else {
			this.postType = postType;
		}
	}
	
	public TypeDeclaration getPreType() {
		return preType;
	}

	public TypeDeclaration getPostType() {
		return postType;
	}

	@Override
	public String toString() {
		if ( this == EMPTY ) {
			return "";
		} else {
			return preType + ((postType!=TypeDeclaration.EMPTY)?">>"+postType:"");
		}
	}
}
