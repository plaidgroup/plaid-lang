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
	
	public DestructiveDereference(Token t, Expr left, Identifier right) {
		super(t, left, right);
	}
	
	@Override
	public String toString() {
		String result = "";
		if ( getLeft() != null ) result += getLeft().toString();
		result += "!";
		if ( getRight() != null ) result += getRight().toString();
		return result;
	}
}
