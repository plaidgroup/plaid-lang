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
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IdGen;

public class Application implements Expression {
	private Token token;
	private Expression function;
	private Expression argument;
	
	public Application(Expression function, Expression argument) {
		super();
		this.function = function;
		this.argument = argument;
	}

	public Application() {
	}

	public Application(Token t, Expression function, Expression argument) {
		super();
		this.token = t;
		this.function = function;
		this.argument = argument;
	}

	public Application(Token t) {
		this.token = t;
	}

	
	public Expression getFunction() {
		return function;
	}

	public void setFunction(Expression function) {
		this.function = function;
	}

	public Expression getArgument() {
		return argument;
	}

	public void setArgument(Expression argument) {
		this.argument = argument;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public void codegen(CodeGen out, ID y, List<ID> localVars) {
		out.setLocation(token);
		ID x = IdGen.getId();
		ID z = IdGen.getId();
		out.declareVar(CodeGen.plaidObjectType, x.getName()); //public PlaidObject x
		out.declareVar(CodeGen.plaidObjectType, z.getName()); //public PlaidObject z
		function.codegen(out, x, localVars);
		argument.codegen(out, z, localVars);
		out.setLocation(token);
		out.assignToCall(y.getName(),x.getName(), z.getName());  // y = Util.call(x,z);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}



}
