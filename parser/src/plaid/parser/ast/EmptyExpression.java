package plaid.parser.ast;

public final class EmptyExpression extends Expression {
	private final static EmptyExpression singleton = new EmptyExpression();
	private EmptyExpression() {
		super(DEFAULT_TOKEN);
	}
	protected static EmptyExpression getEmptyExpression() {
		return singleton;
	}
}
