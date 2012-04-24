package plaid.compilerjava.optimization;

import plaid.compilerjava.AST.*;
import plaid.compilerjava.tools.*;

/*
 * This visitor can walk the AST tree from the root
 * and collect information for Optimizer
 * 
 * Current Optimization: 
 * 	- Tail Call Optimization (TailCallASTVisitor)
 *  
 * others are in implementing
 * 
 * AUTHOR: Fuyao Zhao
 */

public class OptimizerASTVisitor extends AbstractASTVisitor  {
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
	public ASTnode visitNode(Lambda node) {
		node.getBody().accept(new TailCallASTVisitor());
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(MethodDecl node) {
		node.getBody().accept(new TailCallASTVisitor());
		return visitHelper(node);
	}
}
