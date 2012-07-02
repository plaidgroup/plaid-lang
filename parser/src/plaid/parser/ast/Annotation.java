package plaid.parser.ast;

import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class Annotation extends ASTNode {
	public static final List<Annotation> EMPTY = Collections.emptyList();
	
	private final Identifier name;
	
	public Annotation(Token t, Identifier name) {
		super(t);
		this.name = name;
	}
	
	public Identifier getName() {
		return this.name;
	}
	
	public String toString() {
		return "Annotation("+name+")";
	}
}
