package plaid.compilerjava.types;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import plaid.compilerjava.AST.ASTnode;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class Permission implements ASTnode, JSONAware {
	public static final Permission FULL = new Permission(null, "full");
	public static final Permission IMMUTABLE = new Permission(null, "immutable");
	public static final Permission PURE = new Permission(null, "pure");
	public static final Permission DYN = new Permission(null, "dyn");
	public static final Permission UNIQUE = new Permission(null, "unique");
	public static final Permission SHARED = new Permission(null, "shared");
	public static final Permission NONE = new Permission(null, "none");
	
	// This is a marker to indicate that the permission of the receiver should remain unchanged
	//public static final Permission RECEIVER = new Permission(null, "receiver");
	
	// This is a marker to indicate that the permission of the object should be the default of the state declaration
	//public static final Permission DEFAULT = new Permission(null, "default");

	private final Token token;
	private final String image;
	
	private Permission(Token token, String image) {
		this.token = token;
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public Token getToken() {
		return this.token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		// no children
	}

	public String toString() {
		//return "Permission: " + this.image;
		return this.image;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.image);
		return obj.toJSONString();
	}
}
