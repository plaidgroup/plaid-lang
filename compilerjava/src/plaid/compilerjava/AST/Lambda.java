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
import plaid.compilerjava.types.LambdaType;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public final class Lambda implements Expression {
	
	private final Token token;
	private final ID var;
	private final Expression body;
	private final LambdaType type;
	
	
	
	// TODO: I'm not sure that this is a very good constructor...
//	public Lambda(ID var, Expression body) {
//		this(null, var, body, new MethodTypeDecl(null, null, null, null, null));
//	}

	public Lambda(Token token, ID var, Expression body, LambdaType type) {
		super();
		
		this.token = token;
		
		// if var is unit, generate a fresh ID that won't get used in the body
		if (var == null)
			this.var = IdGen.getId();
		else
			this.var = var;
		
		this.body = body;
		this.type = type;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public ID getVar() {
		return var;
	}

	public Expression getBody() {
		return body;
	}

	public LambdaType getType() {
		return type;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		
		ID freshID = IdGen.getId();
		
		out.assignToNewLambda(y.getName(),var.getName());  //y = new lambda(...{ {
		
		out.declareVar(CodeGen.plaidObjectType,freshID.getName());
		IDList newLocalVars = localVars.add(var);
		body.codegenExpr(out, freshID, newLocalVars, stateVars);  //lambda body
		out.ret(freshID.getName());
		
		out.closeAnonymousDeclaration(); //}});");
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		var.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
