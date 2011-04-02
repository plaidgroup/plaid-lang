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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.runtime.PlaidException;

public class DeclList implements State {

	private Token token;
	private List<Decl> decls;

	public DeclList(List<Decl> decls) {
		super();
		this.decls = decls;
	}

	public DeclList() {
	}

	public DeclList(Token t, List<Decl> decls) {
		super();
		this.token = t;
		this.decls = decls;
	}

	public DeclList(Token t) {
		this.token = t;
	}

	public List<Decl> getDecls() {
		return decls;
	}

	public void setDecls(List<Decl> decls) {
		this.decls = decls;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
//	// for top-level declarations
//	@Override
//	public void codegen(CodeGen out, ID y, IDList localVars) {
//	
//		out.setLocation(token);
//		
//		out.assignToNewStateObject(y.getName());  //y = util.newObject();
//		
//		Set<String> declNames = new HashSet<String>();
//		for (Decl decl : decls) {
//			declNames.add(decl.getName());
//			decl.codegen(out, y, localVars);
//		}
//		if (declNames.size() < decls.size()) {
//			throw new PlaidException("Cannot have field and method with the same name.");
//		}
//	}
	
	// for state declarations
	@Override
	public void codegenState(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {

		out.setLocation(token);
		
		out.assignToNewStateObject(y.getName());  //y = util.newObject();
		Set<String> declNames = new HashSet<String>();
		
		for (Decl decl : decls) {
			String name = decl.getName();
			if (declNames.contains(name)) {
				throw new PlaidException("Cannot have two members defined with the name \"" + name + "\".");
			} else { 
				declNames.add(decl.getName());
				decl.codegenNestedDecl(out, y, localVars, stateVars, tagContext);
			}
		}
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		for (Decl d : decls)
			d.accept(visitor);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
