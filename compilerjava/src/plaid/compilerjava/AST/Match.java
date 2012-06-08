/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.compilerjava.AST;

import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class Match implements Expression {
	private Token token;
	private Expression e;
	private List<Case> caseList;
	
	public Match(Expression e, List<Case> caseList) {
		super();
		this.e = e;
		this.caseList = caseList;
	}
	
	public Match(Token t, Expression e, List<Case> caseList) {
		super();
		this.token = t;
		this.e = e;
		this.caseList = caseList;
	}
	
	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

	public List<Case> getCaseList() {
		return caseList;
	}

	public void setCaseList(List<Case> caseList) {
		this.caseList = caseList;
	}

	public Token getToken() {
		return token;
	}

	@Override
	public boolean hasToken() {
		return token != null;
	}

	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		ID toMatch = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, toMatch.getName());

		if (e instanceof ID && !localVars.contains((ID)e) && stateVars.contains((ID)e)) {
			e = new Dereference(new ID("this$plaid"), (ID)e);
		}
		e.codegenExpr(out, toMatch, localVars, stateVars);
		
		for (Case c : caseList) {
			c.codegen(out, y, toMatch, localVars, stateVars);
			out.elseCase(); out.openBlock();
		}
		out.append("throw new plaid.runtime.PlaidRuntimeException(\"Pattern match exhausted.\");");
		for (int i=0; i<caseList.size();i++) {
			out.closeBlock();
		}
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		e.accept(visitor);
		for (Case c : caseList)
			c.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
