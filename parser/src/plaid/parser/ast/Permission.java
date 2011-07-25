package plaid.parser.ast;

import plaid.parser.Token;

public class Permission extends ASTNode {
	public static final Permission FULL = new Permission(DEFAULT_TOKEN, "full");
	public static final Permission IMMUTABLE = new Permission(DEFAULT_TOKEN, "immutable");
	public static final Permission PURE = new Permission(DEFAULT_TOKEN, "pure");
	public static final Permission DYN = new Permission(DEFAULT_TOKEN, "dyn");
	public static final Permission UNIQUE = new Permission(DEFAULT_TOKEN, "unique");
	public static final Permission SHARED = new Permission(DEFAULT_TOKEN, "shared");
	public static final Permission NONE = new Permission(DEFAULT_TOKEN, "none");
	
	// This is a marker to indicate that the permission of the receiver should remain unchanged
	//public static final Permission RECEIVER = new Permission(null, "receiver");
	
	// This is a marker to indicate that the permission of the object should be the default of the state declaration
	//public static final Permission DEFAULT = new Permission(null, "default");

	private final String image;
	
	private Permission(Token token, String image) {
		super(token);
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}
}
