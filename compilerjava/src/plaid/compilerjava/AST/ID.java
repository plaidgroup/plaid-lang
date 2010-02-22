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
import plaid.compilerjava.util.CodeGen;

public class ID implements Expression{
	private Token token;
	private String name;
	
	public ID(String name) {
		this.name = name;
	}
	
	public ID(Token t, String name) {
		this.name = name;
		this.token = t;
	}
	
	public Token getToken() {
		return token;
	}
	
	public String getName() { return name; }
	
	public void codegen(CodeGen out, ID y, List<ID> localVars) {
		boolean isLocal = false; 
		out.setLocation(token);
		for (ID var : localVars) {
			if (var.getName().equals(name)) { 
				out.assignToID(y.getName(),name);  // y = name
				isLocal = true;
				break;
			}
		}
		if (!isLocal) {
			out.assignToLookup(y.getName(), name, CodeGen.currentScope);  // y = lookup(name,currentScope);
		}
			
	}
}
