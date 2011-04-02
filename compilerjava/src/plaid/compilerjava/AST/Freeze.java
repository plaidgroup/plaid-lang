package plaid.compilerjava.AST;

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class Freeze implements State {

	private Token token;
	private ID frozenVar;
	
	public Freeze(ID frozenVar) {
		this(null,frozenVar);
	}
	
	public Freeze(Token t, ID frozenVar) {
		this.token = t;
		this.frozenVar = frozenVar;
	}

	@Override
	public void codegenState(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {
		out.setLocation(this.token);
		
		ID fresh = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, fresh.getName());
		this.frozenVar.codegenExpr(out, fresh, localVars, stateVars);
		
		out.assignToFreeze(y.getName(), fresh.getName());
		
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {

		return visitor.visitNode(this);
	}

	@Override
	public Token getToken() {
		return this.token;
	}

	@Override
	public boolean hasToken() {
		return this.token != null;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		this.frozenVar.accept(visitor);
		
	}
	
	
}
