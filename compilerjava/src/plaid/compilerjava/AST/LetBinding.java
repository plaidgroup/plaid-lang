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

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public class LetBinding implements Expression {

	private Token token;
	private ID x;
	private Expression exp, body;
	private boolean mutable;
	private final Type type;

	public LetBinding(Token t, ID x, Type type, Expression e1, Expression e2, boolean mutable) {
		super();
		this.token = t;
		this.setX(x);
		if (type == null)
			this.type = Type.DYN;
		else	
			this.type = type;
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
	
	public Type getType() {
		return type;
	}

	@Override
	public void codegen(CodeGen out, ID y, IDList localVars) {
		out.setLocation(token);
		out.openBlock(); //{
		if(mutable) {
			out.declareVar(CodeGen.plaidObjectType, x.getName());
		} else {
			out.declareFinalVar(CodeGen.plaidObjectType, x.getName());
		}
		exp.codegen(out, x, localVars);
		
		localVars = localVars.add(x);
		body.codegen(out, y, localVars);
		
		out.closeBlock(); // }
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
