package plaid.parser.ast;

import plaid.parser.Token;

public class GroupDecl implements Decl {
	private final Token token; 
	private final ID id;
	private final boolean isAbstract;
		
	public GroupDecl(Token token, ID id, boolean isAbstract) {
		super();
		this.token = token;
		this.id = id;
		this.isAbstract = isAbstract;
	}

	public ID getId() {
		return id;
	}
	
	public boolean isAbstract() {
		return isAbstract;
	}
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return id.getName();
	}


	@Override
	public Token getToken() {
		return token;
	}

	@Override
	public boolean hasToken() {
		return true;
	}

}
