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

public class LetBinding implements Expression {

	private Token token;
	private ID x;
	private Expression exp, body;
	
	public LetBinding(ID x, Expression e1, Expression e2) {
		super();
		this.x = x;
		this.exp = e1;
		this.body = e2;
	}

	public LetBinding(ID x, Expression e1) {
		super();
		this.x = x;
		this.exp = e1;
	}
	
	public LetBinding() {
		super();
	}

	public LetBinding(Token t, ID x, Expression e1, Expression e2) {
		super();
		this.token = t;
		this.x = x;
		this.exp = e1;
		this.body = e2;
	}

	public LetBinding(Token t, ID x, Expression e1) {
		super();
		this.token = t;
		this.x = x;
		this.exp = e1;
	}
	
	public LetBinding(Token t) {
		super();
		this.token = t;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public void codegen(CodeGen out, ID y, IDList localVars) {
		out.setLocation(token);
		out.openBlock(); //{
		out.declareFinalVar(CodeGen.plaidObjectType, x.getName());
		exp.codegen(out, x, localVars);
		
		IDList newLocalVars = localVars.add(x);
		body.codegen(out, y, newLocalVars);
		
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
	public void accept(ASTVisitor visitor) {
//		x.accept(visitor);
//		exp.accept(visitor);
//		body.accept(visitor);
		visitor.visit(this);
	}

}
