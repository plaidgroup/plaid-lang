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

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class NewInstance implements Expression{

	private Token token;
	private State st;
	
	public NewInstance() {
		super();
	}

	public NewInstance(State st) {
		super();
		this.st = st;
	}

	public State getSt() {
		return st;
	}

	public NewInstance(Token t, State st) {
		super();
		this.token = t;
		this.st = st;
	}

	public State getSt(Token t) {
		return st;
	}
	
	public void setSt(State st) {
		this.st = st;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	@Override

	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {

		out.setLocation(token);
		ID r = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidStateType, r.getName()); 

		st.codegenState(out, r, localVars, stateVars, null);

		out.assignToInstantiation(y.getName(),r.getName()); //y = r.instantiate();
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		st.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
