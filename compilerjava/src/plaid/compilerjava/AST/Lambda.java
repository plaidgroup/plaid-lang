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
import plaid.compilerjava.types.LambdaType;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;
import plaid.runtime.PlaidConstants;

public final class Lambda implements Expression {
	
	private final Token token;
	private final List<ID> arguments = new ArrayList<ID>();
	private final boolean hasArgs;
	private Expression body;
	private final LambdaType type;
	private List<MetaParameter> metaParams = new ArrayList<MetaParameter>();
	private boolean isNamed = false;
	private ID recursiveName = null;
	
	
	// TODO: I'm not sure that this is a very good constructor...
//	public Lambda(ID var, Expression body) {
//		this(null, var, body, new MethodTypeDecl(null, null, null, null, null));
//	}

	public Lambda(Token token, Expression body, LambdaType type) {
		super();
		
		this.token = token;
		this.body = body;
		this.type = type;
		this.hasArgs = false;
	
	}
	
	public Lambda(Token token, Expression body,  List<MetaParameter> metaParams, LambdaType type) {
		super();
		
		this.token = token;
		this.body = body;
		this.type = type;
		this.hasArgs = false;
		this.metaParams = metaParams;
	
	}
	
	public Lambda(Token token, ID var, Expression body, LambdaType type) {
		super();
		
		this.token = token;
		
		// if var is unit, generate a fresh ID that won't get used in the body
		if (var == null)
			this.hasArgs = false;
		else {
			this.hasArgs = true;
			this.arguments.add(var);
		
		}
		
		this.body = body;
		this.type = type;
	}
	
	
	public Lambda(Token token, ID var, Expression body, List<MetaParameter> metaParams, LambdaType type) {
		super();
		
		this.token = token;
		
		// if var is unit, generate a fresh ID that won't get used in the body
		if (var == null)
			this.hasArgs = false;
		else {
			this.hasArgs = true;
			this.arguments.add(var);
		
		}
		
		this.body = body;
		this.type = type;
		this.metaParams = metaParams;
	}
	
	public Lambda(Token token, List<ID> boundVars, Expression body, LambdaType type) {
		super();
		
		this.token = token;
		
		this.arguments.addAll(boundVars);
		this.hasArgs = boundVars.size() > 0;
		
		this.body = body;
		this.type = type;
		
	}
	
	public Lambda(Token token, List<ID> boundVars, Expression body, List<MetaParameter> metaParams, LambdaType type) {
		super();
		
		this.token = token;
		
		this.arguments.addAll(boundVars);
		this.hasArgs = boundVars.size() > 0;
		
		this.body = body;
		this.type = type;
		this.metaParams = metaParams;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public boolean hasArg() {
		return this.hasArgs;
	}
	
	public List<ID> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public List<MetaParameter> getMetaParamaters() {
		return metaParams;
	}
	
	public Expression getBody() {
		return body;
	}

	public LambdaType getType() {
		return type;
	}

	public ID getRecursiveName() {
		return recursiveName;
	}

	public void setRecursiveName(ID recursiveName) {
		this.recursiveName = recursiveName;
		this.isNamed = true;
	}

	public boolean isNamed() {
		return isNamed;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		
		ID freshID = IdGen.getId();
		
		ID argID;
		if (!this.hasArgs) { 
			argID = ID.DEFAULTPARAMID;
		} else if (arguments.size() == 1) {
			argID = arguments.get(0);
		} else {
			argID = new ID("pA1R"+ PlaidConstants.ID_SUFFIX);
			body = RewriteUtil.getBodyWithPairExtractions(arguments, argID, 1, body);
		}
		
		out.assignToNewLambda(y.getName(),argID.getName());  //y = new lambda(...{ {
		
		out.declareVar(CodeGen.plaidObjectType,freshID.getName());
		IDList newLocalVars = localVars.add(argID);
		body.codegenExpr(out, freshID, newLocalVars, stateVars);  //lambda body
		out.ret(freshID.getName());
		
		out.closeAnonymousDeclaration(); //}});");
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		for (Expression var: arguments) var.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
