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

public class With implements State {

	private Token token;
	private State r1,r2;
	
	public With(State r1, State r2) {
		this(null, r1, r2);
	}

	public With(Token t) {
		token = t;
	}

	public With(Token t, State r1, State r2) {
		super();
		this.token = t;
		this.r1 = r1;
		this.r2 = r2;
	}
	
	public With() {
	}
	
	public State getR1() {
		return r1;
	}

	public void setR1(State r1) {
		this.r1 = r1;
	}

	public State getR2() {
		return r2;
	}

	public void setR2(State r2) {
		this.r2 = r2;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	@Override
	public void codegenState(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {

		out.setLocation(token);
		
		ID fresh1 = IdGen.getId();
		ID fresh2 = IdGen.getId();
		
		out.declareFinalVar(CodeGen.plaidStateType, fresh1.getName());
		out.declareFinalVar(CodeGen.plaidStateType, fresh2.getName());
		
		r1.codegenState(out, fresh1, localVars, stateVars, tagContext);

		r2.codegenState(out, fresh2, localVars, stateVars, tagContext);

		
		out.assignToWith(y.getName(),fresh1.getName(),fresh2.getName());  //y = fresh1.with(fresh2); 
	}
	
	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		r1.accept(visitor);
		r2.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

//	@Override
//	public void codegen(CodeGen out, ID y, IDList localVars) {
//		// TODO Auto-generated method stub
//		
//	}

}
