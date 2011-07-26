package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class QualifiedIdentifier extends ASTNode {
	private final List<Identifier> ids;

	public QualifiedIdentifier(Token t, List<Identifier> ids) {
		super(t);
		this.ids = ids;
	}
	
	public List<Identifier> getIdentifiers() {
		return ids;
	}
}
