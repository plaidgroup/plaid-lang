package plaid.compilerjava.tools;

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

public class ASTPrintVisitor implements ASTVisitor {
	int tablevel;
	public ASTPrintVisitor() {
		tablevel = -1;
	}
	
	private static void printlnWithTabs(String str, int num) {
		for (int i = 0; i < num; i++)
			System.out.print("\t");
		System.out.println(str);
	}

	@Override
	public void visit(Application e) {
		printlnWithTabs("Application", tablevel);
		tablevel++;
		e.getFunction().accept(this);
		e.getArgument().accept(this);
		tablevel--;
	}

	@Override
	public void visit(Assignment e) {
		printlnWithTabs("Assigment", tablevel);
	}

	@Override
	public void visit(Case e) {
		printlnWithTabs("Case", tablevel);
		tablevel++;
		e.getX().accept(this);
		e.getQi().accept(this);
		e.getE().accept(this);
		tablevel--;
	}

	@Override
	public void visit(ChangeState e) {
		
		printlnWithTabs("ChangeState", tablevel);
		tablevel++;
		e.getSt().accept(this);
		e.getE().accept(this);
		tablevel--;
	}

	@Override
	public void visit(CompilationUnit e) {
		printlnWithTabs("CompilationUnit", tablevel);
		tablevel++;
		e.getImports().accept(this);
		for (Decl d : e.getDecls())
			d.accept(this);
		tablevel--;
	}

	@Override
	public void visit(Decl e) {
		printlnWithTabs("Decl", tablevel);
		tablevel++;
		e.accept(this);
		tablevel--;
	}

	@Override
	public void visit(DeclList e) {
		printlnWithTabs("DeclList", tablevel);
		tablevel++;
		for (Decl d : e.getDecls())
			d.accept(this);
		tablevel--;
	}

	@Override
	public void visit(Dereference e) {
		printlnWithTabs("Dereference", tablevel);
		tablevel++;
		e.getLeft().accept(this);
		e.getRight().accept(this);
		tablevel--;
	}

	@Override
	public void visit(Expression e) {
		printlnWithTabs("Expression", tablevel);
	}

	@Override
	public void visit(FieldDecl e) {
		printlnWithTabs("FieldDecl", tablevel);
		tablevel++;
		e.getF().accept(this);
		e.getE().accept(this);
		tablevel--;
	}

	@Override
	public void visit(ID e) {
		printlnWithTabs("ID", tablevel);
	}

	@Override
	public void visit(Imports e) {
		printlnWithTabs("Imports", tablevel);
	}

	@Override
	public void visit(IntLiteral e) {
		printlnWithTabs("IntLiteral", tablevel);
	}

	@Override
	public void visit(Lambda e) {
		printlnWithTabs("Lambda", tablevel);
		tablevel++;
		e.getVar().accept(this);
		e.getBody().accept(this);
		tablevel--;
	}

	@Override
	public void visit(LetBinding e) {
		printlnWithTabs("LetBinding", tablevel);
		tablevel++;
		e.getX().accept(this);
		e.getExp().accept(this);
		e.getBody().accept(this);
		tablevel--;
	}

	@Override
	public void visit(Match e) {
		printlnWithTabs("Match", tablevel);
		tablevel++;
		e.getE().accept(this);
		for (Case c : e.getCaseList())
			c.accept(this);
		tablevel--;
	}

	@Override
	public void visit(MethodDecl e) {
		printlnWithTabs("MethodDecl", tablevel);
		tablevel++;
		if (e.getArg() != null)
			e.getArg().accept(this);
		
		if (e.getBody() != null)
			e.getBody().accept(this);
		tablevel--;
	}

	@Override
	public void visit(NewInstance e) {
		printlnWithTabs("NewInstance", tablevel);
		tablevel++;
		e.getSt().accept(this);
		tablevel--;
	}

	@Override
	public void visit(QI e) {
		printlnWithTabs("QI", tablevel);
	}

	@Override
	public void visit(State e) {
		printlnWithTabs("State", tablevel);
	}

	@Override
	public void visit(StateDecl e) {
		printlnWithTabs("StateDecl", tablevel);
		tablevel++;
		e.getName().accept(this);
		e.getStateDef().accept(this);
		tablevel--;
	}

	@Override
	public void visit(StringLiteral e) {
		printlnWithTabs("StringLiteral", tablevel);
	}

	@Override
	public void visit(UnitLiteral e) {
		printlnWithTabs("UnitLiteral", tablevel);
	}

	@Override
	public void visit(With e) {
		printlnWithTabs("With", tablevel);
		tablevel++;
		e.getR1().accept(this);
		e.getR2().accept(this);
		tablevel--;
	}

	@Override
	public void visit(ASTnode e) {
		printlnWithTabs("ASTnode", tablevel);
	}

}
