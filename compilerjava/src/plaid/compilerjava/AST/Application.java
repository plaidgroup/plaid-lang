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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class Application implements Expression {
	private final Token token;
	private final Expression f;
	private final List<Expression> arguments = new ArrayList<Expression>();

	public Application(Token t, Expression function, Expression argument) {
		super();
		this.token = t;
		this.f = function;
		this.arguments.add(argument);
	}
	
	public Application(Token t, Expression function, List<Expression> arguments) {
		super();
		this.token = t;
		this.f = function;
		this.arguments.addAll(arguments);	
	}
	
	public Expression getFunction() {
		return f;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	@Override

	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		ID x = IdGen.getId();
		ID z = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, x.getName()); //public PlaidObject x
		out.declareFinalVar(CodeGen.plaidObjectType, z.getName()); //public PlaidObject z

		f.codegenExpr(out, x, localVars, stateVars);
	
		Expression arg = plaid.compilerjava.coreparser.PlaidCoreParser.foldToPairs(arguments);
		arg.codegenExpr(out, z, localVars, stateVars);

		out.setLocation(token);
		out.assignToCall(y.getName(),x.getName(), z.getName());  // y = Util.call(x,z);
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		f.accept(visitor);
		for (Expression arg : arguments)
			arg.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

}
