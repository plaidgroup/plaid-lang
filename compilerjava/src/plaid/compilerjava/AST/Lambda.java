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
import plaid.compilerjava.util.IdGen;

public class Lambda implements Expression {
	private Token token;
	private ID var;
	private Expression body;
	
	public Lambda(ID var, Expression body) {
		super();
		this.var = var;
		this.body = body;
	}

	public Lambda(Token t, ID var, Expression body) {
		super();
		this.token = t;
		this.var = var;
		this.body = body;
	}

	public Token getToken() {
		return token;
	}
	
	public ID getVar() {
		return var;
	}

	public void setVar(ID var) {
		this.var = var;
	}

	public Expression getBody() {
		return body;
	}

	public void setBody(Expression body) {
		this.body = body;
	}

	@Override
	public void codegen(CodeGen out, ID y, IDList localVars) {
		
		out.setLocation(token);
		
		ID freshID = IdGen.getId();
		
		out.assignToNewLambda(y.getName(),var.getName());  //y = new lambda(...{ {
		
		out.declareFinalVar(CodeGen.plaidObjectType,freshID.getName());
		IDList newLocalVars = localVars.add(var);
		body.codegen(out, freshID, newLocalVars);  //lambda body
		out.ret(freshID.getName());
		
		out.closeAnonymousDeclaration(); //}});");
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
//		visitor.visitEdge(this, var);
//		visitor.visitEdge(this, body);
//		visitor.visitChild(var);
//		visitor.visitChild(body);
		var.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}
}
