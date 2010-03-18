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
import plaid.compilerjava.AST.Imports;
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
import plaid.compilerjava.util.QualifiedID;

// TODO: Refactor this code so that we don't have to have knowledge of the implicit
// structure of each AST node.  One way to do this could be to have each AST node 
// implement an additional method called visitChildren or something along those 
// lines that we could call in each of these methods.  This is still more 
// restricting than simply manually calling accept on each of the children inside 
// the visitor class, so that represents a slight tradeoff between flexibility and 
// modifiability.

public class ASTInspectorVisitor implements ASTVisitor {
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode curr;
	
	private static String getQID(List<String> ids) {
		String ret = "";
		for (String s : ids)
			ret += "." + ret;
		return ret;
	}
	
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
	public void visit(Application e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Application");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getFunction().accept(this);
		e.getArgument().accept(this);
		curr = parent;
	}

	@Override
	public void visit(Assignment e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Assignment");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getField().accept(this);
		e.getTarget().accept(this);
		e.getValue().accept(this);
		curr = parent;
	}

	@Override
	public void visit(Case e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Case");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getX().accept(this);
		e.getQi().accept(this);
		e.getE().accept(this);
		curr = parent;
	}

	@Override
	public void visit(ChangeState e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("ChangeState");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getE().accept(this);
		e.getSt().accept(this);
		curr = parent;
	}

	@Override
	public void visit(CompilationUnit e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("CompilationUnit");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getImports().accept(this);
		for (Decl d : e.getDecls())
			d.accept(this);
		curr = parent;
	}

	@Override
	public void visit(Decl e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Decl");
		addNode(newNode);
	}

	@Override
	public void visit(DeclList e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("DeclList");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		for (Decl d : e.getDecls())
			d.accept(this);
		curr = parent;
	}

	@Override
	public void visit(Dereference e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Dereference");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getLeft().accept(this);
		e.getRight().accept(this);
		curr = parent;
	}

	@Override
	public void visit(Expression e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Expression");
		addNode(newNode);
	}

	@Override
	public void visit(FieldDecl e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("FieldDecl");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getF().accept(this);
		e.getE().accept(this);
		curr = parent;
	}

	@Override
	public void visit(ID e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("ID : " + e.getName());
		addNode(newNode);
	}

	@Override
	public void visit(Imports e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Imports");
		addNode(newNode);
		for (QualifiedID qi : e.getImports())
			newNode.add(new DefaultMutableTreeNode(getQID(qi.getQidList())));
	}

	@Override
	public void visit(IntLiteral e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("IntLiteral : " + e.toString());
		addNode(newNode);
	}

	@Override
	public void visit(Lambda e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Lambda");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getVar().accept(this);
		e.getBody().accept(this);
		curr = parent;
	}

	@Override
	public void visit(LetBinding e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("LetBinding");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getX().accept(this);
		e.getExp().accept(this);
		e.getBody().accept(this);
		curr = parent;
	}

	@Override
	public void visit(Match e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Match");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getE().accept(this);
		for (Case c : e.getCaseList())
			c.accept(this);
		curr = parent;
	}

	@Override
	public void visit(MethodDecl e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("MethodDecl");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		if (e != null && e.getArg() != null)
			e.getArg().accept(this);
		if (e != null && e.getBody() != null)
			e.getBody().accept(this);
		curr = parent;
	}

	@Override
	public void visit(NewInstance e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("NewInstance");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getSt().accept(this);
		curr = parent;
	}

	@Override
	public void visit(QI e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("QI");
		addNode(newNode);
		String qid = "";
		for (String s : e.getQid())
			qid += "." + s;
		newNode.add(new DefaultMutableTreeNode(qid));
	}

	@Override
	public void visit(State e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("State");
		addNode(newNode);
	}

	@Override
	public void visit(StateDecl e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("StateDecl");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getName().accept(this);
		e.getStateDef().accept(this);
		curr = parent;
	}

	@Override
	public void visit(StringLiteral e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("StringLiteral");
		addNode(newNode);
		newNode.add(new DefaultMutableTreeNode("\"" + e.toString() + "\""));
	}

	@Override
	public void visit(UnitLiteral e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("UnitLiteral");
		addNode(newNode);
	}

	@Override
	public void visit(With e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("With");
		addNode(newNode);
		DefaultMutableTreeNode parent = curr;
		curr = newNode;
		e.getR1().accept(this);
		e.getR2().accept(this);
		curr = parent;
	}

	@Override
	public void visit(ASTnode e) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("ASTnode");
		addNode(newNode);
	}

}
