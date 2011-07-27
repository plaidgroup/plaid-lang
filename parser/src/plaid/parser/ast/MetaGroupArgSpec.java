package plaid.parser.ast;

import plaid.parser.Token;

public class MetaGroupArgSpec extends MetaArgSpec {

	private final GroupPermission gp;
	private final Identifier id;

	public MetaGroupArgSpec(Token token, GroupPermission gp, Identifier id) {
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
}