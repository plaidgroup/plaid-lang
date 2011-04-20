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

public class ChangeState implements Expression {
	
	private Token token;
	private Expression e;
	private State st;
	private boolean wipe;

	public ChangeState(Expression e, State st, boolean wipe) {
		super();
		this.e = e;
		this.st = st;
		this.wipe = wipe;
	}

	public ChangeState(Token t, Expression e, State st, boolean wipe) {
		super();
		this.token = t;
		this.e = e;
		this.st = st;
		this.wipe = wipe;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

	public State getSt() {
		return st;
	}

	public void setSt(State st) {
		this.st = st;
	}
	
	public boolean wipeSemantics() {
		return wipe;
	}
	
	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {

		out.setLocation(token);
		
		//generate code for getting the object to change
		ID x = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType,x.getName());
		e.codegenExpr(out, x, localVars, stateVars);

		//generate code for the new State
		ID r = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType,r.getName());
		st.codegenState(out, r, localVars, stateVars, null);

		//cast to State
		ID s = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidStateType,s.getName());
		out.assignCastedtoState(s.getName(), r.getName());
		
		// create instance - KBN - don't do this to allow us to choose which fields are initialized (ie don't always launch the missles)
//		ID i = IdGen.getId();
//		out.declareFinalVar(CodeGen.plaidObjectType,i.getName());
//		out.assignToInstantiation(i.getName(), s.getName());
		
//		ID oldObj = IdGen.getId();
//		out.declareFinalVar(CodeGen.plaidObjectType, oldObj.getName());
//		out.append(oldObj.getName() + " = " + x.getName() + ".copy();");
		
		//assign result of state change to target (y)
		out.assignToChangedState(y.getName(),x.getName(), s.getName(), (wipe ? "true" : "false"));  // y = x.changeState(r);
		
//		// clear away all of the old state members from this state in the local scope
//		out.append(CodeGen.localScope + ".clearOrUpdateOldMembers(" + oldObj.getName() + ", " + x.getName() + ");");
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		e.accept(visitor);
		st.accept(visitor);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
