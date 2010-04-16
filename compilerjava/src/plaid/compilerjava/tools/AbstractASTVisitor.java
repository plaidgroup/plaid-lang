package plaid.compilerjava.tools;

import plaid.compilerjava.AST.*;
import plaid.compilerjava.AST.Assignment;
import plaid.compilerjava.AST.Case;
import plaid.compilerjava.AST.ChangeState;
import plaid.compilerjava.AST.CompilationUnit;
import plaid.compilerjava.AST.Decl;
import plaid.compilerjava.AST.DeclList;
import plaid.compilerjava.AST.Dereference;
import plaid.compilerjava.AST.Expression;
import plaid.compilerjava.AST.FieldDecl;
import plaid.compilerjava.AST.ID;
import plaid.compilerjava.AST.ImportList;
import plaid.compilerjava.AST.IntLiteral;
import plaid.compilerjava.AST.Lambda;
import plaid.compilerjava.AST.LetBinding;
import plaid.compilerjava.AST.Match;
import plaid.compilerjava.AST.MethodDecl;
import plaid.compilerjava.AST.NewInstance;
import plaid.compilerjava.AST.QI;
import plaid.compilerjava.AST.State;
import plaid.compilerjava.AST.StateDecl;
import plaid.compilerjava.AST.StringLiteral;
import plaid.compilerjava.AST.UnitLiteral;
import plaid.compilerjava.AST.With;

/**
 * AbstractASTVisitor is an abstract implementation of ASTVisitor that simply 
 * enters the node, visits the children, and leaves the node without doing 
 * anything fancy.  Implementors can extend this class in order to avoid 
 * having to implement all of the ASTVisitor functions immediately.
 * 
 * @author mhahnenberg
 *
 */
public abstract class AbstractASTVisitor implements ASTVisitor {
	public ASTVisitor enter(ASTnode node) {
		return this;
	}
	
	public ASTnode leave(ASTnode node, ASTnode oldNode, ASTVisitor visitor) {
		return node;
	}
	
	private <T extends ASTnode> ASTnode visitHelper(T node) {
		ASTVisitor visitor = this.enter(node);
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
	public ASTnode visitNode(MethodDecl node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(NewInstance node) {
		return visitHelper(node);
	}
	
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
	
	@Override
	public ASTnode visitNode(UnitLiteral node) {
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(With node) {
		return visitHelper(node);
	}
}


