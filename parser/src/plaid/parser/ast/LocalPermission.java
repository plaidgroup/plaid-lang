package plaid.parser.ast;

import plaid.parser.Token;

public final class LocalPermission extends Permission {
	
	private final SymmetricPermission symmetricPermission;

	public LocalPermission(Token token, SymmetricPermission symmetricPermission) {
		super(token);
		this.symmetricPermission = symmetricPermission;
	}

	public SymmetricPermission getSymmetricPermission() {
		return symmetricPermission;
	}
	
	@Override
	public String toString() {
		return "local " + symmetricPermission.toString();
	}
}
