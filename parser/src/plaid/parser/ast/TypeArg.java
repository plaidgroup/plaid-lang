package plaid.parser.ast;

import plaid.parser.Token;

public class TypeArg extends StaticArg {
	private final Identifier id;
	private final QualifiedIdentifier caseof;
	
	public TypeArg(Token token, Identifier id, QualifiedIdentifier caseof) {
		super(token);
		this.id = id;
		this.caseof = caseof;
	}
	
	public Identifier getId() {
		return id;
	}
	
	public QualifiedIdentifier getCaseof() {
		return caseof;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("type ");
		sb.append(id.toString());
		if ( caseof.getIdentifiers().size() > 0 ) {
			sb.append(" case of ");
			sb.append(caseof.toString());
		}
		return sb.toString();
	}
	
}
