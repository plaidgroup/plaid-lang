package plaid.parser.ast;

import plaid.parser.Token;

public class Import extends ASTNode {
	
	private final QualifiedIdentifier qi;
	private final boolean star;
	
	public Import(Token token, QualifiedIdentifier qi, boolean star) {
		super(token);
		this.qi = qi;
		this.star = star;
	}

	public boolean isStar() {
		return star;
	}

	public QualifiedIdentifier getQualifiedIdentifier() {
		return qi;
	}

	@Override
	public String toString() {
		return "import " + qi.toString() + ((star)?".*":"");
	}
}
