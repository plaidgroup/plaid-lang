package plaid.parser.ast;

import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class MetaArg extends ASTNode {
	public static final List<MetaArg> EMPTY = Collections.emptyList();
	
	public MetaArg(Token token) {
		super(token);
	}

}
