package plaid.parser.ast;

public final class EmptyExpr extends Expr {
	private final static EmptyExpr singleton = new EmptyExpr();
	private EmptyExpr() {
		super(DEFAULT_TOKEN);
	}
	protected static EmptyExpr getEmptyExpression() {
		return singleton;
	}
	
	public String toString() {
		return "EMPTY";
	}
}
