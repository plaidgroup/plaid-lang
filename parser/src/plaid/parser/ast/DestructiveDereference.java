package plaid.parser.ast;

import plaid.parser.Token;

/**
 * This class has the same functionality as the normal Dereference node, 
 * we just do something different for it during typechecking.
 * 
 * @author mhahnenberg
 *
 */
public class DestructiveDereference extends Dereference {
	
	public DestructiveDereference(Token t, Expression left, Identifier right) {
		super(t, left, right);
	}
}
