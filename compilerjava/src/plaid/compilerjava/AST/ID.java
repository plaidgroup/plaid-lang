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
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public class ID implements Expression{
	public static final ID DEFAULTPARAMID = new ID("_");
	
	private Token token;
	private String name;

	public ID(String name) {
		this.name = convertKeyword(name);
	}
	
	public ID(Token t, String name) {
		this.name = convertKeyword(name);
		this.token = t;
	}
	
	public String convertKeyword(String name) {
		if (Util.isKeyword(name))
			name += PlaidConstants.ID_SUFFIX;
		return name;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public String getName() { return name; }
	
	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		if (!localVars.contains(this) && stateVars.contains(this)) { //Might need to add 'this' in front
//			System.out.println("inserting 'this': " + name);
			Dereference newDeref = new Dereference(new ID("this$plaid"), this);
			newDeref.codegenExpr(out, y, localVars, new HashSet<ID>()); //know that it is 'this.ID' so no need for stateVars when generating
		} else {
			out.setLocation(token);
			String newName = CodeGen.convertOpNames(name);
			out.assignToLookup(y.getName(), newName, CodeGen.localScope);  // y = lookup(name,currentScope);
		}
	}
	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		// do nothing (no children)
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
	
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof ID) ? ((ID)o).getName().equals(this.name) : false; 
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
