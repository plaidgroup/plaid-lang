package plaid.compilerjava.AST;

import plaid.compilerjava.types.ChangeType;

/**
 * This is just a convenience class for moving type/arg pairs around
 * in the parser.
 * 
 * @author mhahnenberg
 *
 */
public class Arg {
	private final ChangeType changeType;
	private final ID arg;
	
	public Arg(ChangeType changeType, ID arg) {
		this.changeType = changeType;
		this.arg = arg;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public ID getArg() {
		return arg;
	}
}
