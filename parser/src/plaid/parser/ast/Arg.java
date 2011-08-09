package plaid.parser.ast;

import java.util.Collections;
import java.util.List;

import plaid.parser.Token;


public class Arg extends ASTNode {
	
	public static final List<Arg> EMPTY = Collections.emptyList();
	
	private final Identifier name;
	private final ArgSpec argSpec;
	
	
	public Arg(Token token, ArgSpec argSpec, Identifier name) {
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
		if ( argSpec.toString().length() == 0) {
			return name.toString();
		}
		return argSpec + " " + name;
	}
}
