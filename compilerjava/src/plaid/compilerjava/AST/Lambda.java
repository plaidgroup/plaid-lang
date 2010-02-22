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

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.util.CodeGen;
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
	
	@Override
	public void codegen(CodeGen out, ID y, List<ID> localVars) {

		out.setLocation(token);
		
		ID freshID = IdGen.getId();
		
		out.assignToNewLambda(y.getName(),var.getName());  //y = new lambda(...{ {
		
		out.declareVar(CodeGen.plaidObjectType,freshID.getName());
		localVars.add(var);  //TODO: do we need to worry about shadowing?
		body.codegen(out, freshID, localVars);  //lambda body
		localVars.remove(var);
		out.ret(freshID.getName());
		
		out.closeAnonymousDeclaration(); //}});");
	}

}
