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
	private final boolean hasArgs;
	private final List<MetaArgument> metaArgs;
	
	private boolean isTailCall = false;

	public Application(Token t, Expression function, Expression argument) {
		super();
		this.token = t;
		this.f = function;
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.metaArgs = new ArrayList<MetaArgument>();
	}
	
	public Application(Token t, Expression function, List<MetaArgument>  metaArgs, Expression argument) {
		super();
		this.token = t;
		this.f = function;
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.metaArgs = metaArgs;
	}
	
	public Application(Token t, Expression function, List<Expression> arguments) {
		super();
		this.token = t;
		this.f = function;
		this.arguments.addAll(arguments);
		if (this.arguments.size() == 1 && this.arguments.get(0) instanceof UnitLiteral) {
			this.hasArgs = false;
			this.arguments.clear();
		} else {
			this.hasArgs = true;
		}
		this.metaArgs = new ArrayList<MetaArgument>();
	}
	
	public Application(Token t, Expression function, List<MetaArgument> metaArgs, List<Expression> arguments) {
		super();
		this.token = t;
		this.f = function;
		this.arguments.addAll(arguments);
		if (this.arguments.size() == 1 && this.arguments.get(0) instanceof UnitLiteral) {
			this.hasArgs = false;
			this.arguments.clear();
		} else {
			this.hasArgs = true;
		}
		this.metaArgs = metaArgs;
	}
	
	public Expression getFunction() {
		return f;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public boolean hasArgs() {
		return this.hasArgs;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	List<MetaArgument> getMetaArgs() {
		return metaArgs;
	}
	
	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		ID x = IdGen.getId();
		ID z = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, x.getName()); //public PlaidObject x
		out.declareFinalVar(CodeGen.plaidObjectType, z.getName()); //public PlaidObject z

		f.codegenExpr(out, x, localVars, stateVars);
		Expression arg;
		if (hasArgs) {
			arg = plaid.compilerjava.coreparser.PlaidCoreParser.foldToPairs(arguments);
		} else {
			arg = new UnitLiteral();
		}
		arg.codegenExpr(out, z, localVars, stateVars);

		out.setLocation(token);
		if (isTailCall)
			out.assignToTailCall(y.getName(),x.getName(), z.getName());
		else 
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

	public void setTailCall(boolean isTailCall) {
		this.isTailCall = isTailCall;
	}

	public boolean isTailCall() {
		return isTailCall;
	}
}
