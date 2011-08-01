package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class QualifiedIdentifier extends ASTNode {
	
	public static final QualifiedIdentifier EMPTY = EmptyQualifiedIdentifier.getEmptyQalifiedIdentifer();

	private final List<Identifier> ids;
	
	public QualifiedIdentifier(Token t, List<Identifier> ids) {
		super(t);
		this.ids = ids;
	}
	
	public List<Identifier> getIdentifiers() {
		return ids;
	}

	@Override
	public String toString() {
		if ( ids.size() == 1 ) {
			return ids.get(0).toString();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(ids.get(0));
			for(int i = 1; i < ids.size(); i++ ) {
				sb.append(".");
				sb.append(ids.get(i).toString());
			}
			return sb.toString();
		}
	}
}
