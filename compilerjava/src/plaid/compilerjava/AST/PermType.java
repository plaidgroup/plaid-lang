package plaid.compilerjava.AST;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class PermType implements ASTnode {
	public static final PermType UNIT = new PermType(Permission.IMMUTABLE, Type.UNIT);
	public static final PermType DYN = new PermType(Permission.DYN, Type.DYN);
	// This is a marker to indicate that the permission and type of the receiver should remain unchanged
	public static final PermType RECEIVER = new PermType(Permission.RECEIVER, Type.RECEIVER);
	
	private final Permission perm;
	private final Type type;
	
	public PermType(Permission perm, Type type) {
		if (perm == null || type == null) {
			throw new RuntimeException("Perm and type must not be null!");
		}
		this.perm = perm;
		this.type = type;
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		this.perm.accept(visitor);
		this.type.accept(visitor);
	}
	
	public String toString() {
		System.out.println(this.perm.toString());
		return this.perm.toString() + " " + this.type.toString();
	}

	@Override
	public Token getToken() {
		return null;
	}
}
