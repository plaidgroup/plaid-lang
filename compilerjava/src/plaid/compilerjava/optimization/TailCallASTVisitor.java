package plaid.compilerjava.optimization;

import plaid.compilerjava.AST.*;
import plaid.compilerjava.tools.*;

/**
 * Using this visitor to identify tail call
 * Call by OptimizerASTVistor
 */
public class TailCallASTVisitor extends AbstractASTVisitor {
	protected <T extends ASTnode> ASTnode visitHelper(T node) {
		return null;
	}
	
	@Override
	public ASTnode visitNode(Case node) {
		node.getE().accept(this);
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(LetBinding node) {
		node.getBody().accept(this);
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Application node) {
		node.setTailCall(true);
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(MethodCall node) {
		node.setTailCall(true);
		return visitHelper(node);
	}
	
	@Override
	public ASTnode visitNode(Match node) {
		for (Case c: node.getCaseList()) {
			c.accept(this);
		}
		return visitHelper(node);
	}
}


