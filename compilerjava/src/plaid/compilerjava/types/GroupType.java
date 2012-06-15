package plaid.compilerjava.types;

import plaid.compilerjava.AST.GroupPermission;
import plaid.compilerjava.AST.ID;

public class GroupType implements TypeDecl, MetaType {
	private final ID id;
	private final GroupPermission groupPermission;
	private final boolean isAbstract;

	public GroupType(GroupPermission groupPermission, ID id, boolean isAbstract) {
		this.groupPermission = groupPermission;
		this.isAbstract = isAbstract;
		this.id = id;
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


	public ID getID() {
		return this.id;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public GroupPermission getGroupPermission() {
		return groupPermission;
	}
	
	@Override
	public String toString() {
		return "group " + id + ((isAbstract)?"":" = new group");
	}

}
