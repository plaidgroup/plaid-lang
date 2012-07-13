package plaid.parser.ast;

import plaid.parser.Token;

public class GroupArg extends StaticArg {

	private final GroupPermission gp;
	private final Identifier id;

	public GroupArg(Token token, GroupPermission gp, Identifier id) {
		super(token);
		this.gp = gp;
		this.id = id;
	}

	public GroupPermission getGp() {
		return gp;
	}

	public Identifier getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("group ");
		if ( gp != GroupPermission.EMPTY ) {
			sb.append(gp.toString().toLowerCase() + " ");
		}
		sb.append(id.toString());
		return sb.toString();
	}
}
