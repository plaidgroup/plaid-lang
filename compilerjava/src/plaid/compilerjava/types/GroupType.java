package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class GroupType implements TypeDecl {
	private final ID name;
	private final boolean isImmutable;

	public GroupType(ID name, boolean immutable) {
		this.isImmutable = immutable;
		this.name = name;
	}

	//      @Override
	//      public <T> T accept(ASTVisitor<T> visitor) {
	//              return visitor.visitNode(this);
	//      }
	//
	//      @Override
	//      public Token getToken() {
	//              // TODO Auto-generated method stub
	//              return null;
	//      }
	//
	//      @Override
	//      public boolean hasToken() {
	//              return false;
	//      }
	//
	//      @Override
	//      public <T> void visitChildren(ASTVisitor<T> visitor) {
	//              this.permType.accept(visitor);
	//      }


	public ID getName() {
		return this.name;
	}

	public boolean isImmutable() {
		return isImmutable;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (isImmutable) s.append("val ");
		else s.append("var ");

		s.append(name.getName());

		return s.toString();

	}

}
