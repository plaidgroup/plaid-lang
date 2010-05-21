package plaid.compilerjava.tools;

import plaid.compilerjava.AST.*;

/**
 * ASTVisitor consists of 3 main functions:
 *	- 'enter' called before the children of the node are visited. This method 
 *		returns the ASTVisitor used to traverse the children
 *	- 'leave' called after the children have been visited. The call contains 
 *		the new(replacement) node, the old node and the visitor used to 
 *		traverse the children.
 *	- 'visitEdge' called to visit a new edge in the AST tree.
 *
 * The remainder of the methods are just the implementations of visiting the 
 * specific node types.
 *
 * @author mhahnenberg
 *
 */
public interface ASTVisitor {
	public ASTVisitor enter(ASTnode node);
	public ASTnode leave(ASTnode node, ASTnode oldNode, ASTVisitor visitor);
	public ASTnode visitNode(ASTnode node);
	public ASTnode visitNode(Application node);
	public ASTnode visitNode(Assignment node);
	public ASTnode visitNode(Case node);
	public ASTnode visitNode(ChangeState node);
	public ASTnode visitNode(CompilationUnit node);
	public ASTnode visitNode(Decl node);
	public ASTnode visitNode(DeclList node);
	public ASTnode visitNode(Dereference node);
	public ASTnode visitNode(Expression node);
	public ASTnode visitNode(FieldDecl node);
	public ASTnode visitNode(ID node);
	public ASTnode visitNode(ImportList node);
	public ASTnode visitNode(IntLiteral node);
	public ASTnode visitNode(Lambda node);
	public ASTnode visitNode(LetBinding node);
	public ASTnode visitNode(Match node);
	public ASTnode visitNode(MethodDecl node);
	public ASTnode visitNode(NewInstance node);
	public ASTnode visitNode(QI node);
	public ASTnode visitNode(State node);
	public ASTnode visitNode(StateDecl node);
	public ASTnode visitNode(StringLiteral node);
	public ASTnode visitNode(Type node);
	public ASTnode visitNode(UnitLiteral node);
	public ASTnode visitNode(With node);
}
