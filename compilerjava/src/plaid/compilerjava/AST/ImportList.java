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
import plaid.compilerjava.util.QualifiedID;

public class ImportList {

	private List<QualifiedID> imports;
	private Token token;
	
	public ImportList(Token t, List<QualifiedID> imports) {
		super();
		token = t;
		this.imports = imports;
	}
	
	public ImportList(List<QualifiedID> imports) {
		super();
		this.imports = imports;
	}

	public ImportList() {
		imports = new ArrayList<QualifiedID>();
	}
	
	public ImportList(Token t) {
		imports = new ArrayList<QualifiedID>();
		token = t;
	}
	
	public Token getToken() {
		return token;
	}
	
	public void codegen(CodeGen out, ID y) {
		
		out.openStaticBlock();  // static {
		out.assignToNewJavaObject(y.getName(),"java.util.ArrayList<plaid.runtime.utils.Import>"); //y = new java..();
		
		for (QualifiedID qi : imports) {
			out.append(y.getName() + ".add(new plaid.runtime.utils.Import(\"" + qi.toString() + "\"));");
		}

		out.append("}");
	}

}
