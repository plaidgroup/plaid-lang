package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class PatternCase extends Case{
	private final QualifiedIdentifier pattern;
	private final List<Expr> staticArgs; 

	public PatternCase(Token token, QualifiedIdentifier pattern, List<Expr> staticArgs, BlockExpr body) {
		super(token, body);
		this.pattern = pattern;
		this.staticArgs = staticArgs;
	}

	public QualifiedIdentifier getPattern() {
		return pattern;
	}
	
	public List<Expr> getStaticArgs() {
		return this.staticArgs;
	}
	
	@Override
	public String toString() {
		return "case " + pattern.toString() + getBody();
	}
}
