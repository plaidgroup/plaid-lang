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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.FileGen;
import plaid.compilerjava.util.IdGen;
import plaid.compilerjava.util.QualifiedID;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public class StateDecl implements Decl {

	private Token token;
	private ID name;
	private State stateDef;

	public StateDecl(ID name, State stateDef) {
		super();
		this.setName(name);
		this.setStateDef(stateDef);
	}
	
	public StateDecl() {
	}
	
	public StateDecl(Token t, ID name, State stateDef) {
		super();
		this.token = t;
		this.setName(name);
		this.setStateDef(stateDef);
	}
	
	public StateDecl(Token t) {
		this.token = t;
	}

	public ID getName() {
		return name;
	}

	public void setName(ID name) {
		if (Util.isKeyword(name.getName()))
			name = new ID(name.getName() + PlaidConstants.ID_SUFFIX);
		this.name = name;
	}

	public State getStateDef() {
		return stateDef;
	}

	public void setStateDef(State r) {
		this.stateDef = r;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public File codegen(QualifiedID qid, Imports imports, CompilerConfiguration cc) {
		CodeGen out = new CodeGen(cc);	
		ID freshImports = IdGen.getId();
		
		//package and needed imports
		out.declarePackage(qid.toString()); //package qid;
		
		//annotation and class definition
		out.stateAnnotation(name.getName(), true);
		out.declarePublicClass(name.getName()); out.addBlock();  // public class f {
		
		//generate code to create the package scope with imports
		out.declarePublicStaticVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareTopScope(qid.toString(),freshImports.getName());
		
		out.stateAnnotation(name.getName(), false);
		out.declarePublicStaticVar(CodeGen.plaidObjectType, name.getName());
		
		out.addStaticBlock(); //static {
		stateDef.codegen(out, name, new ArrayList<ID>());//this is this declaration.  It will not have any members, but at runtime can forward to its enclosing (instantiated) state
		out.closeBlock(); // } (for static block)
		
		out.closeBlock(); // } (for class Def)
		
		return FileGen.createOutputFile(name.getName(), cc.getOutputDir(), out.formatFile(), qid);
		
	}

	@Override
	public void codegen(CodeGen out, ID y, List<ID> localVars) {
		out.setLocation(token);
		
		ID fresh = IdGen.getId();
		out.stateAnnotation(name.getName(), false);
		out.declareVar(CodeGen.plaidObjectType, fresh.getName());
		stateDef.codegen(out, fresh, localVars);
		out.addMember(y.getName(), name.getName(), fresh.getName()); //y.addMember(s,fresh)
	}

	@Override
	public void accept(ASTVisitor visitor) {
//		visitor.visit(name);
//		visitor.visit(stateDef);
		visitor.visit(this);
	}

}
