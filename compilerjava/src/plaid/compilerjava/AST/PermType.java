package plaid.compilerjava.AST;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class PermType implements ASTnode {
	public static final PermType UNIT = new PermType(null, Type.UNIT);
	public static final PermType DYN = new PermType(null, Type.DYN);
	
	private final Permission perm;
	private final Type type;
	
	public PermType(Permission perm, Type type) {
		this.perm = perm;
		this.type = type;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		this.perm.accept(visitor);
		this.type.accept(visitor);
	}
	
	public String toString() {
		if (this.perm == null) {
			return this.type.toString();
		}
		return this.perm.toString() + " " + this.type.toString();
	}

	@Override
	public Token getToken() {
		return null;
	}
}
