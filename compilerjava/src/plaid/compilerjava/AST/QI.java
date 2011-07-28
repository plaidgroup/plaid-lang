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
import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class QI implements State {

	private Token token;
	private List<String> qid;
	private boolean hasInit = false;
	private DeclList initState = null;
	private List<MetaArgument> metaArgs = new ArrayList<MetaArgument>();
	
	public QI(Token t, List<String> qid, DeclList initState) {
		this.token = t;
		this.qid = qid;
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
	}
	
	public QI(Token t, List<String> qid, List<MetaArgument> metaArgs, DeclList initState) {
		this.token = t;
		this.qid = qid;
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
		this.metaArgs = metaArgs;
	}
	
	public QI(Token t, String qi, DeclList initState) {
		this.token = t;
		qid = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			qid.add(f);
		}
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
	}
	
	public QI(Token t, String qi, List<MetaArgument> metaArgs, DeclList initState) {
		this.token = t;
		qid = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			qid.add(f);
		}
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
		this.metaArgs = metaArgs;
	}
	
	public QI(List<String> qid) {
		this(null, qid, null);
	}
	
	public QI(List<String> qid, List<MetaArgument> metaArgs) {
		this(null, qid, metaArgs, null);
	}
	
	public QI(List<String> qid, DeclList init) {
		this(null, qid, init);
	}

	public QI(List<String> qid, List<MetaArgument> metaArgs, DeclList init) {
		this(null, qid, metaArgs, init);
	}
	
	
	public QI(String qi) {
		this(null, qi, null);
	}
	
	public QI(String qi, DeclList init) {
		this(null, qi, init);
	}
	
	public QI() {
	}

	public QI(Token t, List<String> qid) {
		this(t, qid, null);
	}
	
	public QI(Token t, String qi) {
		this(t, qi, null);
	}
	
	public QI(Token t) {
		this.token = t;
	}

	public List<String> getQid() {
		return qid;
	}

	public void setQid(List<String> qid) {
		this.qid = qid;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}

	public boolean hasInit() {
		return this.hasInit;
	}

	public DeclList getInitState() {
		return initState;
	}

	public List<MetaArgument> getMetaArguments() {
		return metaArgs;
	}
	
	@Override
	public void codegenState(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {

		out.setLocation(token);
		
		String scope = CodeGen.globalScope;
		ID fresh = null;
		
		for (String name : qid) {	
			fresh = IdGen.getId();
			out.declareFinalVar(CodeGen.plaidObjectType, fresh.getName());
			out.assignToLookup(fresh.getName(), name, scope);
			scope = fresh.getName();
		}
		
		if (hasInit) {
			ID initialization = IdGen.getId();
			ID toInit = IdGen.getId();
			ID tag = IdGen.getId();
			
			//make sure we got a state out of the lookup
			out.declareFinalVar(CodeGen.plaidStateType, toInit.getName());
			out.assignCastedtoState(toInit.getName(),fresh.getName()); //toInit = (PlaidState)fresh
			
			//generate code for the initialization state
			out.declareFinalVar(CodeGen.plaidTagType, tag.getName());
			out.assignToStateTag(tag.getName(),toInit.getName());
			out.declareFinalVar(CodeGen.plaidStateType, initialization.getName());
			initState.codegenState(out, initialization, localVars, stateVars, tag);
		
			//initialize the state
			out.assignToStateInitialization(y.getName(), toInit.getName(), initialization.getName());	
		
		} else {
			out.assignCastedtoState(y.getName(),fresh.getName()); //y = (PlaidState)fresh
		}
		
	}

	public String toString() {
		String strQid = "";
		for (String id : qid) {
			strQid = strQid + id + ".";
		}
		return strQid.substring(0,strQid.length() - 1);
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		// do nothing (no children)
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

//	@Override
//	public void codegen(CodeGen out, ID y, IDList localVars) {
//		// TODO Auto-generated method stub
//		
//	}

}
