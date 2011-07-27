package plaid.parser.ast;

public class GroupParameter implements MetaParameter {
	private final GroupPermission permission;
	private final Identifier id;
	
	public GroupParameter(GroupPermission permission, Identifier id) {
		super();
		this.permission = permission;
		this.id = id;
	}
	
	public GroupPermission getPermission() {
		return permission;
	}
	
	public Identifier getId() {
		return id;
	}
	
	
}
