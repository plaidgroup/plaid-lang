package plaid.compilerjava.AST;

import plaid.compilerjava.coreparser.Token;

/**
 * This class has the same functionality as the normal Dereference node, 
 * we just do something different for it during typechecking.
 * 
 * @author mhahnenberg
 *
 */
public class DestructiveDereference extends Dereference {
	public DestructiveDereference(Expression left, ID right) {
		super(left, right);
	}
	
	public DestructiveDereference(Token t, Expression left, ID right) {
		super(t, left, right);
	}
}
