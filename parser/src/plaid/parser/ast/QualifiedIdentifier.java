package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class QualifiedIdentifier extends ASTNode {
	
	public static final QualifiedIdentifier EMPTY = EmptyQualifiedIdentifier.getEmptyQalifiedIdentifer();

	private final List<Identifier> identifiers;
	
	public QualifiedIdentifier(Token t, List<Identifier> identifiers) {
		super(t);
		this.identifiers = identifiers;
	}
	
	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	@Override
	public String toString() {
		if ( identifiers.size() == 1 ) {
			return identifiers.get(0).toString();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(identifiers.get(0));
			for(int i = 1; i < identifiers.size(); i++ ) {
				sb.append(".");
				sb.append(identifiers.get(i).toString());
			}
			return sb.toString();
		}
	}
}
