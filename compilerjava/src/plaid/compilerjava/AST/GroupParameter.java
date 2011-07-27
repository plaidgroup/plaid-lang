package plaid.compilerjava.AST;

public class GroupParameter implements MetaParameter {
	private final GroupPermission permission;
	private final ID id;
	
	public GroupParameter(GroupPermission permission, ID id) {
		super();
		this.permission = permission;
		this.id = id;
	}
	
	public GroupPermission getPermission() {
		return permission;
	}
	
	public ID getId() {
		return id;
	}
	
	
}
