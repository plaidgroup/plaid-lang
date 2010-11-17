package plaid.compilerjava.types;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class PermType implements /*ASTnode,*/ JSONAware {
	public static final PermType UNIT = new PermType(Permission.IMMUTABLE, Type.UNIT);
	public static final PermType DYN = new PermType(Permission.DYN, Type.DYN);
	public static final PermType VOID = new PermType(Permission.NONE, Type.UNIT);  //VOID = none unit
	// This is a marker to indicate that the permission and type of the receiver should remain unchanged
	//public static final PermType RECEIVER = new PermType(Permission.RECEIVER, Type.RECEIVER);
	
	private final Permission perm;
	private final Type type;
	private boolean borrowed = false;
	
	public PermType(Permission perm, Type type) {
		if (perm == null || type == null) {
			throw new RuntimeException("Perm and type must not be null!");
		}
		this.perm = perm;
		this.type = type;
	}

//	@Override
//	public <T> T accept(ASTVisitor<T> visitor) {
//		return visitor.visitNode(this);
//	}
//
//	@Override
//	public <T> void visitChildren(ASTVisitor<T> visitor) {
//		this.perm.accept(visitor);
//		this.type.accept(visitor);
//	}
//	
//	public String toString() {
//		//System.out.println(this.perm.toString());
//		return this.perm.toString() + " " + this.type.toString();
//	}
//
//	@Override
//	public Token getToken() {
//		return null;
//	}
//	
//	@Override
//	public boolean hasToken() {
//		return false;
//	}

	public Permission getPermission() {
		return this.perm;
	}
	
	public Type getType() {
		return this.type;
	}

	public boolean isBorrowed() {
		return borrowed;
	}
	
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("permission", this.perm);
		obj.put("type", this.type);
		return obj.toJSONString();
	}
	
	@Override
	public String toString() {
		return perm.toString() + " " + type.toString();
		
		
	}
}
