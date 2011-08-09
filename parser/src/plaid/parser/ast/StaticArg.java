package plaid.parser.ast;

import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class StaticArg extends ASTNode {
	public static final List<StaticArg> EMPTY = Collections.emptyList();
	
	public StaticArg(Token token) {
		super(token);
	}

}
