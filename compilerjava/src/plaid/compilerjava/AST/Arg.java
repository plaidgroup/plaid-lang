package plaid.compilerjava.AST;

/**
 * This is just a convenience class for moving type/arg pairs around
 * in the parser.
 * 
 * @author mhahnenberg
 *
 */
public class Arg {
	private final Type type;
	private final ID arg;
	
	public Arg(Type type, ID arg) {
		this.type = type;
		this.arg = arg;
	}

	public Type getType() {
		return type;
	}

	public ID getArg() {
		return arg;
	}
}
