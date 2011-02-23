package plaid.compilerjava.tools;

import plaid.compilerjava.AST.*;
import plaid.compilerjava.types.FieldType;
import plaid.compilerjava.types.MethodType;
import plaid.compilerjava.types.PermType;
import plaid.compilerjava.types.Permission;
import plaid.compilerjava.types.ObjectType;
import plaid.compilerjava.types.TypeDecl;

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
public interface ASTVisitor<T> {
	public ASTVisitor<T> enter(ASTnode node);
	public T leave(ASTnode node, ASTnode oldNode, ASTVisitor<T> visitor);
	public T visitNode(ASTnode node);
	public T visitNode(Application node);
	public T visitNode(Assignment node);
	//public T visitNode(AtomicBlock node);
	public T visitNode(Case node);
	public T visitNode(ChangeState node);
	public T visitNode(CompilationUnit node);
	public T visitNode(Decl node);
	public T visitNode(DeclList node);
	public T visitNode(Dereference node);
	public T visitNode(Expression node);
	public T visitNode(FieldDecl node);
	//public T visitNode(FieldType node);
	public T visitNode(ID node);
	public T visitNode(ImportList node);
	public T visitNode(IntLiteral node);
	public T visitNode(Lambda node);
	public T visitNode(LetBinding node);
	public T visitNode(Match node);
	public T visitNode(MethodCall node);
	public T visitNode(MethodDecl node);
	//public T visitNode(MethodType node);
	public T visitNode(NewInstance node);
	//public T visitNode(Permission node);
	//public T visitNode(PermType node);
	public T visitNode(QI node);
	public T visitNode(State node);
	public T visitNode(StateDecl node);
	public T visitNode(StringLiteral node);
	//public T visitNode(ObjectType node);
	//public T visitNode(TypeDecl node);
	public T visitNode(UnitLiteral node);
	public T visitNode(With node);
}
