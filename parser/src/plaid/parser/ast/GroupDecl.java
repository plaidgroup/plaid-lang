package plaid.parser.ast;

import plaid.parser.Token;

public class GroupDecl extends Decl {
	private final boolean isAbstract;
		
	public GroupDecl(Token token, ID name, boolean isAbstract) {
		super(token, name);
		this.isAbstract = isAbstract;
	}
	
	public boolean isAbstract() {
		return isAbstract;
	}

}
