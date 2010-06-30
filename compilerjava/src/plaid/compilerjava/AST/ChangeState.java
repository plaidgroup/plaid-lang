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

	public ChangeState(Expression e, State st) {
		super();
		this.e = e;
		this.st = st;
	}

	public ChangeState(Token t, Expression e, State st) {
		super();
		this.token = t;
		this.e = e;
		this.st = st;
	}
	
	public Token getToken() {
		return token;
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
	
	@Override
	public void codegen(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		
		out.setLocation(token);
		
		//generate code for getting the object to change
		ID x = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType,x.getName());
		e.codegen(out, x, localVars, stateVars);

		//generate code for the new State
		ID r = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType,r.getName());
		st.codegen(out, r, localVars, stateVars);

		//cast to State
		ID s = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidStateType,s.getName());
		out.assignCastedtoState(s.getName(), r.getName());
		
		// create instance
		ID i = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType,i.getName());
		out.assignToInstantiation(i.getName(), s.getName());
		
//		ID oldObj = IdGen.getId();
//		out.declareFinalVar(CodeGen.plaidObjectType, oldObj.getName());
//		out.append(oldObj.getName() + " = " + x.getName() + ".copy();");
		
		//assign result of state change to target (y)
		out.assignToChangedState(y.getName(),x.getName(), i.getName());  // y = x.changeState(r);
		
//		// clear away all of the old state members from this state in the local scope
//		out.append(CodeGen.localScope + ".clearOrUpdateOldMembers(" + oldObj.getName() + ", " + x.getName() + ");");
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		e.accept(visitor);
		st.accept(visitor);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}
}
