package plaid.parser.ast;

import java.util.ArrayList;

public final class EmptyQualifiedIdentifier extends QualifiedIdentifier {

	private static final EmptyQualifiedIdentifier singleton = new EmptyQualifiedIdentifier();
	private EmptyQualifiedIdentifier() {
		super(DEFAULT_TOKEN, new ArrayList<Identifier>());
	}
	protected static EmptyQualifiedIdentifier getEmptyQalifiedIdentifer() {
		return singleton;
	}

}
