package plaid.parser.ast;

import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public abstract class Modifier extends ASTNode {
	public static final List<Modifier> EMPTY = Collections.emptyList();
	
	public Modifier(Token t) {
		super(t);
	}
}
