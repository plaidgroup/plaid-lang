package plaid.compilerjava.AST;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class FieldTypeDecl implements TypeDecl {
	private final PermType permType;
	
	public FieldTypeDecl(PermType permType) {
		this.permType = permType;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
