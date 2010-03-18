package plaid.compilerjava.tools;

import plaid.compilerjava.AST.*;

public interface ASTVisitor {
	public void visit(Application e);
	public void visit(Assignment e);
	public void visit(Case e);
	public void visit(ChangeState e);
	public void visit(CompilationUnit e);
	public void visit(Decl e);
	public void visit(DeclList e);
	public void visit(Dereference e);
	public void visit(Expression e);
	public void visit(FieldDecl e);
	public void visit(ID e);
	public void visit(ImportList e);
	public void visit(IntLiteral e);
	public void visit(Lambda e);
	public void visit(LetBinding e);
	public void visit(Match e);
	public void visit(MethodDecl e);
	public void visit(NewInstance e);
	public void visit(QI e);
	public void visit(State e);
	public void visit(StateDecl e);
	public void visit(StringLiteral e);
	public void visit(UnitLiteral e);
	public void visit(With e);
	public void visit(ASTnode e);
}
