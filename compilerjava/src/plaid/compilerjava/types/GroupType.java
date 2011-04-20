package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public class GroupType implements TypeDecl, MetaType {
	private final ID name;
	private final boolean isAbstract;

	public GroupType(ID name, boolean isAbstract) {
		this.isAbstract = isAbstract;
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

	public boolean isAbstract() {
		return isAbstract;
	}

	@Override
	public String toString() {
		return "group " + name + ((isAbstract)?"":" = new group");
	}

}
