package plaid.parser.ast;

import plaid.parser.Token;

public class TypeParameter extends MetaArgSpec {
	private final Identifier id;
	private final QualifiedIdentifier caseof;
	public TypeParameter(Token token, Identifier id, QualifiedIdentifier caseof) {
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
	
	
	
}
