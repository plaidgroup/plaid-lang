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

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class QI implements State {

	private Token token;
	private List<String> qid;
	
	public QI(List<String> qid) {
		super();
		this.qid = qid;
	}
	
	public QI(String qi) {
		qid = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			qid.add(f);
		}
	}
	
	public QI() {
	}

	public QI(Token t, List<String> qid) {
		super();
		this.token = t;
		this.qid = qid;
	}
	
	public QI(Token t, String qi) {
		this.token = t;
		qid = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			qid.add(f);
		}
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
	public void codegen(CodeGen out, ID y, IDList localVars) {
		out.setLocation(token);
		
		String scope = CodeGen.currentScope;
		ID fresh = null;
		
		for (String name : qid) {	
			fresh = IdGen.getId();
			out.declareFinalVar(CodeGen.plaidObjectType, fresh.getName());
			out.assignToLookup(fresh.getName(), name, scope);
			scope = fresh.getName();
		}
		
		out.assignCastedtoState(y.getName(),fresh.getName()); //y = (PlaidState)fresh
		
	}

	public String toString() {
		String strQid = "";
		for (String id : qid) {
			strQid = strQid + id + ".";
		}
		return strQid.substring(0,strQid.length() - 1);
	}

}
