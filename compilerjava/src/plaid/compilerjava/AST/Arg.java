package plaid.compilerjava.AST;

import plaid.compilerjava.types.PermType;

/**
 * This is just a convenience class for moving type/arg pairs around
 * in the parser.
 * 
 * @author mhahnenberg
 *
 */
public class Arg {
	private final PermType permType;
	private final ID arg;
	
	public Arg(PermType permType, ID arg) {
		this.permType = permType;
		this.arg = arg;
	}

	public PermType getPermType() {
		return permType;
	}

	public ID getArg() {
		return arg;
	}
}
