package plaid.parser.ast;

import plaid.parser.Token;


public class Arg extends ASTNode {
	
	private final Identifier name;
	private final ArgSpec argSpec;
	
	
	public Arg(Token token, Identifier name, ArgSpec argSpec) {
		super(token);
		this.name = name;
		this.argSpec = argSpec;
	}
	

	public Identifier getName() {
		return name;
	}


	public ArgSpec getArgSpec() {
		return argSpec;
	}

	@Override
	public String toString() {
		return argSpec + " " + name;
	}
}
