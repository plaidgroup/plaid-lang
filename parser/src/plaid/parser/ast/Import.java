package plaid.parser.ast;

import plaid.parser.Token;

public class Import extends ASTNode {
	
	private final QualifiedIdentifier qualifiedIdentifier;
	private final boolean star;
	
	public Import(Token token, QualifiedIdentifier qi, boolean star) {
		super(token);
		this.qualifiedIdentifier = qi;
		this.star = star;
	}

	public boolean getStar() {
		return star;
	}

	public QualifiedIdentifier getQualifiedIdentifier() {
		return qualifiedIdentifier;
	}

	@Override
	public String toString() {
		return "import " + qualifiedIdentifier.toString() + ((star)?".*":"");
	}
}
