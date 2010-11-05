package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class FieldType implements TypeDecl {
	private final PermType permType;
	private final ID name;
	private final boolean immutable;
	
	public FieldType(ID name, PermType permType, boolean immutable) {
		this.immutable = immutable;
		this.name = name;
		if (permType == null)
			throw new RuntimeException("permType cannot be null");
		this.permType = permType;
	}

//	@Override
//	public <T> T accept(ASTVisitor<T> visitor) {
//		return visitor.visitNode(this);
//	}
//
//	@Override
//	public Token getToken() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public boolean hasToken() {
//		return false;
//	}
//
//	@Override
//	public <T> void visitChildren(ASTVisitor<T> visitor) {
//		this.permType.accept(visitor);
//	}

	public PermType getPermType() {
		return this.permType;
	}
	
	public ID getName() {
		return this.name;
	}
	
	public boolean isImmutable() {
		return immutable;
	}

}
