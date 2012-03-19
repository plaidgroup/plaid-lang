package plaid.parser.ast;

public final class EmptyArgSpec extends ArgSpec {
	private static final EmptyArgSpec singleton = new EmptyArgSpec();
	
	private EmptyArgSpec() {
		super(DEFAULT_TOKEN,Type.EMPTY,Type.EMPTY);
	}
	
	protected static EmptyArgSpec getEmptyArgSpec() {
		return singleton;
	}
}
