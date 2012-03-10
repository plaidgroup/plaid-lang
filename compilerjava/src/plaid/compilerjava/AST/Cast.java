package plaid.compilerjava.AST;

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.types.PermType;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;

public class Cast implements Expression {
	private Token t;
	private PermType pt;
	private Expression exp;
	
	public Cast(Token t, PermType pt, Expression exp) {
		this.t = t;
		this.pt = pt;
		this.exp = exp;
	}
	
	@Override
	public Token getToken() {
		return this.t;
	}
	
	public PermType getPermType() {
		return this.pt;
	}
	
	public Expression getExp() {
		return this.exp;
	}

	@Override
	public boolean hasToken() {
		return true;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		if (this.exp != null) {
			this.exp.accept(visitor);
		}
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars,
			Set<ID> stateVars) {
		this.exp.codegenExpr(out, y, localVars, stateVars);
	}

}
