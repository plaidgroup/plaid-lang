package plaid.compilerjava.tools;

import java.util.List;

import javax.swing.tree.*;

import plaid.compilerjava.AST.ASTnode;
import plaid.compilerjava.AST.Application;
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
import plaid.compilerjava.AST.MethodCall;
import plaid.compilerjava.AST.MethodDecl;
import plaid.compilerjava.AST.NewInstance;
import plaid.compilerjava.AST.QI;
import plaid.compilerjava.AST.State;
import plaid.compilerjava.AST.StateDecl;
import plaid.compilerjava.AST.StringLiteral;
import plaid.compilerjava.AST.UnitLiteral;
import plaid.compilerjava.AST.With;
import plaid.compilerjava.types.FieldType;
import plaid.compilerjava.types.MethodType;
import plaid.compilerjava.types.PermType;
import plaid.compilerjava.types.Permission;
import plaid.compilerjava.types.ObjectType;
import plaid.compilerjava.types.TypeDecl;

/* TODO: Refactor this code so that we don't have to have knowledge of the 
 * implicit structure of each AST node.  One way to do this could be to have
 * each AST node implement an additional method called visitChildren or 
 * something along those lines that we could call in each of these methods.  
 * This is still more restricting than simply manually calling accept on each 
 * of the children inside the visitor class, so that represents a slight 
 * tradeoff between flexibility of the ASTVisitor and future modifiability of 
 * the AST.
*/

@SuppressWarnings("unchecked")
public class ASTInspectorVisitor extends AbstractASTVisitor {
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode curr;
	
//	private static String getQID(List<String> ids) {
//		String ret = "";
//		for (String s : ids)
//			ret += "." + s;
//		return ret;
//	}
	
	public ASTInspectorVisitor() {
		root = null;
		curr = null;
	}
	
	public DefaultMutableTreeNode getRoot() {
		return this.root;
	}
	
	private void addNode(DefaultMutableTreeNode newNode) {
		if (root == null) {
			root = newNode;
			curr = newNode;
		}
		else {
			curr.add(newNode);
		}
	}

	@Override
	public ASTVisitor enter(ASTnode node) {
		return this;
	}

	@Override
	public ASTnode leave(ASTnode node, ASTnode oldNode, ASTVisitor visitor) {
		return node;
	}
	
	private <T extends ASTnode> void addNodeVisitChildren(T node, DefaultMutableTreeNode newTreeNode) {
		// add the node to the tree
		addNode(newTreeNode);
		// save the parent
		DefaultMutableTreeNode parent = curr;
		curr = newTreeNode;
		// visit the children
	    node.visitChildren(this);
	    curr = parent;
	}
	
	@Override
	public ASTnode visitNode(Application node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Application"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Assignment node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Assignment"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Case node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Case"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(ChangeState node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("ChangeState"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(CompilationUnit node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("CompilationUnit"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Decl node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Decl"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(DeclList node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("DeclList"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Dereference node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Dereference"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Expression node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Expression"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(FieldDecl node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(node.getF().getName() + " (FieldDecl)");
		newNode.add(new DefaultMutableTreeNode("Immutable: " + node.getImmutable()));
		addNodeVisitChildren(node, newNode);
		for (int i = 0; i < newNode.getChildCount(); i++) {
	    	if (((String)((DefaultMutableTreeNode)newNode.getChildAt(i)).getUserObject()).startsWith("ID")) {
	    		newNode.remove(i);
	    		break;
	    	}
	    }
		//TODO: visit Field Type
		newNode.add(new DefaultMutableTreeNode("Type: " + node.getFieldType().toString()));
		
		// leave
	    return this.leave(node, node, visitor);
	}
	
//	@Override
//	public ASTnode visitNode(FieldType node) {
//		ASTVisitor visitor = this.enter(node);
//		// create the new tree node and add it to the tree
//		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("FieldTypeDecl");
//		addNodeVisitChildren(node, newNode);
//		// leave
//	    return this.leave(node, node, visitor);
//	}

	@Override
	public ASTnode visitNode(ID node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode(node.getName() + " (ID)"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(ImportList node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("ImportList"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(IntLiteral node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("IntLiteral : " + node.toString()));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Lambda node) {
		ASTVisitor visitor = this.enter(node);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Lambda");
		newNode.add(new DefaultMutableTreeNode("Type: " + node.getType().toString()));
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, newNode);
		
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(LetBinding node) {
		ASTVisitor visitor = this.enter(node);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("LetBinding");
		addNodeVisitChildren(node, newNode);
	    for (int i = 0; i < newNode.getChildCount(); i++) {
	    	String label = ((String)((DefaultMutableTreeNode)newNode.getChildAt(i)).getUserObject());
	    	if (label.contains("(ID)") && !label.contains("$plaid")) {
	    		DefaultMutableTreeNode newChildNode = new DefaultMutableTreeNode(label + " : " + node.getPermType());
	    		newNode.remove(i);
	    		newNode.insert(newChildNode, i);
	    		break;
	    	}
	    }
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(Match node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("Match"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(MethodCall node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("MethodCall");
		addNodeVisitChildren(node, newNode);
	    // leave
	    return this.leave(node, node, visitor);
	}
	
	@Override
	public ASTnode visitNode(MethodDecl node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(node.getName() + " (MethodDecl)");
		addNodeVisitChildren(node, newNode);
	    // leave
		newNode.add( new DefaultMutableTreeNode("Type: " + node.getMethodType().toString()));
	    return this.leave(node, node, visitor);
	}
	
	private static String buildFunctionTypeString(List<PermType> argTypes, PermType retType) {
		StringBuilder sb = new StringBuilder();
		if (argTypes == null) {
			sb.append("null -> ");
		}
		else {
			for (PermType permType : argTypes) {
				sb.append(permType + " -> ");
			}
		}
		sb.append(retType);
		return sb.toString();
	}

	@Override
	public ASTnode visitNode(NewInstance node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("NewInstance"));
	    // leave
	    return this.leave(node, node, visitor);
	}
	
//	@Override
//	public ASTnode visitNode(Permission node) {
//		ASTVisitor visitor = this.enter(node);
//		// create the new tree node and add it to the tree
//		addNodeVisitChildren(node, new DefaultMutableTreeNode(node.toString()));
//	    // leave
//	    return this.leave(node, node, visitor);
//	}

	@Override
	public ASTnode visitNode(QI node) {
		ASTVisitor visitor = this.enter(node);
		addNodeVisitChildren(node, new DefaultMutableTreeNode(node.toString() + " (QI)"));
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(State node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("State"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(StateDecl node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(node.getID().getName() + " (StateDecl)");
		addNodeVisitChildren(node, newNode);
	    for (int i = 0; i < newNode.getChildCount(); i++) {
	    	if (((String)((DefaultMutableTreeNode)newNode.getChildAt(i)).getUserObject()).startsWith("ID")) {
	    		newNode.remove(i);
	    		break;
	    	}
	    }
		// leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(StringLiteral node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("StringLiteral : " + node.toString()));
	    // leave
	    return this.leave(node, node, visitor);
	}
	
//	@Override
//	public ASTnode visitNode(ObjectType node) {
//		ASTVisitor visitor = this.enter(node);
//		addNodeVisitChildren(node, new DefaultMutableTreeNode(node.toString()));
//		return this.leave(node, node, visitor);
//	}

	@Override
	public ASTnode visitNode(UnitLiteral node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("UnitLiteral"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(With node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("With"));
	    // leave
	    return this.leave(node, node, visitor);
	}

	@Override
	public ASTnode visitNode(ASTnode node) {
		ASTVisitor visitor = this.enter(node);
		// create the new tree node and add it to the tree
		addNodeVisitChildren(node, new DefaultMutableTreeNode("ASTnode"));
	    // leave
	    return this.leave(node, node, visitor);
	}

}
