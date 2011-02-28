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
import plaid.compilerjava.types.PermType;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;

public class LetBinding implements Expression {

	private Token token;
	private ID x;
	private Expression exp, body;
	private boolean mutable;
	private final PermType permType;
	private final boolean annotated;
	private final boolean take;

	public LetBinding(Token t, ID x, Expression e1, Expression e2, boolean mutable) {
		super();
		this.token = t;
		this.x = x;
		this.exp = e1;
		this.body = e2;
		this.mutable = mutable;
		this.permType = PermType.getDynPT();
		this.annotated = false;
		this.take = false;
	}
	
	public LetBinding(Token t, ID x, Expression e1, Expression e2, boolean mutable, PermType permType) {
		super();
		this.token = t;
		this.x = x;
		this.exp = e1;
		this.body = e2;
		this.mutable = mutable;
		this.permType = permType;
		this.annotated = true;
		this.take = false;
	}
	
	public LetBinding(Token t, ID x, Expression e1, Expression e2, boolean mutable, PermType permType, boolean take) {
		super();
		this.token = t;
		this.x = x;
		this.exp = e1;
		this.body = e2;
		this.mutable = mutable;
		this.permType = permType;
		this.annotated = true;
		this.take = take;
	}

	public boolean isMutable() {
		return mutable;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public boolean isAnnotated() {
			return annotated;
	}
	
	public boolean isTaken() {
		return take;
	}
	
	public PermType getPermType() {
		return permType;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		out.openBlock(); //{
		out.declareFinalVar(CodeGen.plaidObjectType, x.getName());
		exp.codegenExpr(out, x, localVars, stateVars);

		if (!x.getName().contains("$plaid")) {
			// set the immutability of the variable
			out.insertIntoScope(CodeGen.localScope, x.getName(), !this.mutable);
		}
		
		// remove the variable if it's in the set of state vars
		stateVars.remove(x);
		
		localVars = localVars.add(x);
		body.codegenExpr(out, y, localVars, stateVars);

		
		out.closeBlock(); // }
		
		// add the state variable back
		stateVars.add(x);
	}

	public ID getX() {
		return x;
	}

	public void setX(ID x) {
		this.x = x;
	}

	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}

	public Expression getBody() {
		return body;
	}

	public void setBody(Expression body) {
		this.body = body;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		x.accept(visitor);
		exp.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
