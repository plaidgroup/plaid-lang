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

public class LetBinding implements Expression {

	private final Token token;
	private final ID x;
	private final Expression exp;
	private Expression body;
	private final boolean mutable;
	private final PermType permType;

	public LetBinding(Token t, ID x, Expression e1, Expression e2, boolean mutable, PermType permType) {
		super();
		this.token = t;
		this.x = x;
		this.permType = permType;
		this.exp = e1;
		this.body = e2;
		this.mutable = mutable;
	}

	public boolean isMutable() {
		return mutable;
	}

	public Token getToken() {
		return token;
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

		// if the variable name contains our plaid suffix, then this might be a lookup variable, so 
		// we don't want to check if the variable is a lookup map like we do normally when inserting 
		// variables into the local scope
		if (x.getName().contains("$plaid")) {
			out.append(CodeGen.localScope + ".insert(\"" + x.getName() + "\", " + x.getName() + ", "+ !this.mutable + ");");
		}
		else {
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

	public Expression getExp() {
		return exp;
	}

	public Expression getBody() {
		return body;
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		x.accept(visitor);
		exp.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}
}
