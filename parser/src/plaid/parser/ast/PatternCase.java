package plaid.parser.ast;

import plaid.parser.Token;

public final class PatternCase extends Case{
	private final QualifiedIdentifier pattern;

	public PatternCase(Token token, QualifiedIdentifier pattern, BlockExpr body) {
		super(token, body);
		this.pattern = pattern;
	}

	public QualifiedIdentifier getPattern() {
		return pattern;
	}
	
	@Override
	public String toString() {
		return "case " + pattern.toString() + getBody();
	}
}
