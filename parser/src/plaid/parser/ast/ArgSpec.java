package plaid.parser.ast;

import plaid.parser.Token;

public class ArgSpec extends ASTNode {
	
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
	
	@Override
	public boolean equivalent(ASTNode other) {
		if (other instanceof ArgSpec) {
			ArgSpec other2 = (ArgSpec) other;
			return 
				this.preType.equivalent(other2.preType) &&
				this.postType.equivalent(other2.postType);
		} else {
			return false;
		}
	}
}
