package plaid.parser.ast;

import plaid.parser.Token;

public class PermType extends ASTNode {
	
	// This is a marker to indicate that the permission and type of the receiver should remain unchanged
	//public static final PermType RECEIVER = new PermType(Permission.RECEIVER, Type.RECEIVER);
	
	private final Permission perm;
	private final Type type;
	private boolean borrowed = false;
	private Identifier group;

	public PermType(Token token, Permission perm, Identifier group, Type type) {
		super(token);
		if (perm == null || type == null) {
			throw new RuntimeException("Perm and type must not be null!");
		}
		this.perm = perm;
		this.type = type;
		this.group = group;
	}
	
	public PermType(Token token, Permission perm, Type type) {
		this(token, perm, null, type);
	}
	

	public Permission getPermission() {
		return this.perm;
	}
	
	public Type getType() {
		return this.type;
	}

	public boolean hasGroup() {
		return (group==null)?false:true;
	}
	
	public Identifier getGroup() {
		return group;
	}
	
	public boolean isBorrowed() {
		return borrowed;
	}
}
