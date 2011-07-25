package plaid.parser.ast;

/**
 * This is just a convenience class for moving type/arg pairs around
 * in the parser.
 * 
 * @author mhahnenberg
 *
 */
public class Arg {
	private final ChangeType changeType;
	private final Identifier arg;
	
	public Arg(ChangeType changeType, Identifier arg) {
		this.changeType = changeType;
		this.arg = arg;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public Identifier getArg() {
		return arg;
	}
}
