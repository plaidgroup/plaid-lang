package plaid.parser.ast;

import plaid.parser.Token;

public final class SharedPermission extends SymmetricPermission {
	private final Expr datagroup;

	public SharedPermission(Token token, Expr datagroup) {
		super(token);
		this.datagroup = datagroup;
	}

	public Expr getDatagroup() {
		return datagroup;
	}
	
	@Override
	public String toString() {
		if ( datagroup != Expr.EMPTY ) {
			return "shared" + "<" + datagroup.toString() + ">";
		} else {
			return "shared";
		}
	}
}
