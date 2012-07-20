package plaid.compilerjava.tools;

import plaid.compilerjava.AST.*;
import plaid.compilerjava.types.FieldType;
import plaid.compilerjava.types.MethodType;
import plaid.compilerjava.types.PermType;
import plaid.compilerjava.types.Permission;
import plaid.compilerjava.types.ObjectType;
import plaid.compilerjava.types.TypeDecl;

/**
 * AbstractASTVisitor is an abstract implementation of ASTVisitor that simply 
 * enters the node, visits the children, and leaves the node without doing 
 * anything fancy.  Implementors can extend this class in order to avoid 
 * having to implement all of the ASTVisitor functions immediately.
 * 
 * @author mhahnenberg
 *
 */
public abstract class AbstractASTVisitor implements ASTVisitor<ASTnode> {
	public ASTVisitor<ASTnode> enter(ASTnode node) {
		return this;
	}
	
	public ASTnode leave(ASTnode node, ASTnode oldNode, ASTVisitor<ASTnode> visitor) {
		return node;
	}
	
	protected <T extends ASTnode> ASTnode visitHelper(T node) {
		ASTVisitor<ASTnode> visitor = this.enter(node);
	    node.visitChildren(this);
	    return this.leave(node, node, visitor);
	}
	
	@Override
	public ASTnode visitNode(ASTnode node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Application node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Assignment node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Case node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(ChangeState node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(CompilationUnit node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Decl node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(DeclList node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Dereference node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Expression node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(FieldDecl node) {
		return visitHelper(node);
	}
	
//	@Override
//	public ASTnode visitNode(FieldType node) {
//		return visitHelper(node);
//	}
	
	@Override
	public ASTnode visitNode(ID node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(ImportList node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(IntLiteral node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Lambda node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(LetBinding node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Match node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(MethodCall node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(MethodDecl node) {
		return visitHelper(node);
	}
	
//	@Override
//	public ASTnode visitNode(MethodType node) {
//		return visitHelper(node);
//	}
	
	@Override
	public ASTnode visitNode(NewInstance node) {
		return visitHelper(node);
	}
	
//	@Override
//	public ASTnode visitNode(Permission node) {
//		return visitHelper(node);
//	}
	
//	@Override
//	public ASTnode visitNode(PermType node) {
//		return visitHelper(node);
//	}
	
	@Override
	public ASTnode visitNode(QI node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(State node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(StateDecl node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(StringLiteral node) {
		return visitHelper(node);
	}
	
//	@Override
//	public ASTnode visitNode(ObjectType node) {
//		return visitHelper(node);
//		
//	}
//	
//	@Override
//	public ASTnode visitNode(TypeDecl node) {
//		return visitHelper(node);
//	}
	
	@Override
	public ASTnode visitNode(UnitLiteral node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(With node) {
		return visitHelper(node);
	}
}


