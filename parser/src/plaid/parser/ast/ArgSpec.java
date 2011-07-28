package plaid.parser.ast;

import plaid.parser.Token;

public class ArgSpec extends ASTNode {
	
	public static final ArgSpec EMPTY = EmptyArgSpec.getEmptyArgSpec();
	private final Type preType;
	private final Type postType;
	
	public ArgSpec(Token t, Type preType, Type postType) {
		super(t);
		this.preType = preType;
		if (postType == null) {
			this.postType = preType;
		} 
		else {
			this.postType = postType;
		}
	}
	
	public Type getPreType() {
		return preType;
	}

	public Type getPostType() {
		return postType;
	}
}
