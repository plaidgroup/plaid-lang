package plaid.parser.ast;

public final class EmptySpecifier extends Specifier {
	private static EmptySpecifier singleton = new EmptySpecifier();
	private EmptySpecifier() {
		super(DEFAULT_TOKEN, SpecifierKind.EMPTY);
	}
	protected static EmptySpecifier getEmptySpecifier() {
		return singleton;
	}
}
