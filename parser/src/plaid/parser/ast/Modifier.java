package plaid.parser.ast;

import plaid.parser.Token;

public class Modifier extends ASTNode {

	public static final Modifier OVERRIDE = new Modifier("override");
	public static final Modifier REQUIRES = new Modifier("requires");
	public static final Modifier IMMUTABLE = new Modifier("immutable");
	
	protected final String name;
	
	protected Modifier(String name) {
		super(new Token());
		// TODO: fix me 
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
