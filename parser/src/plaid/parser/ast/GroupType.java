package plaid.parser.ast;

import plaid.parser.Token;

public class GroupType extends StaticType {

	private final GroupPermission gp;
	private Identifier id;
	private Boolean isAbstract;
	public GroupType(Token token, GroupPermission gp, Identifier id,
			Boolean isAbstract) {
		super(token);
		this.gp = gp;
		this.id = id;
		this.isAbstract = isAbstract;
	}
	public Identifier getId() {
		return id;
	}
	public void setId(Identifier id) {
		this.id = id;
	}
	public Boolean getIsAbstract() {
		return isAbstract;
	}
	public void setIsAbstract(Boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	public GroupPermission getGp() {
		return gp;
	}
}
