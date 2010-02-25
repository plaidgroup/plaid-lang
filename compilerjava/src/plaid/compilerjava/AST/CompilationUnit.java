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
import plaid.compilerjava.util.QualifiedID;

public class CompilationUnit {
	private List<Decl> decls;
	private List<String> packageName;
	private Imports imports;
	private File sourceFile = new File(">>UNKNOWN<<");

	public CompilationUnit() {
		decls = new ArrayList<Decl>();
	}
	
	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void setPackageName(List<String> name) {
		this.packageName = name;
	}
	
	public void setImports(Imports imports) {
		this.imports = imports;
	}
	
	public List<String> getPackageName() { 
		return packageName; 
	}
	
	public Imports getImports() {
		return imports;
	}
	
	public void addDecl(Decl d) {
		decls.add(d);
	}
	
	public List<Decl> getDecls() { return this.decls; }

	public List<File> codegen(CompilerConfiguration cc) {
		CompilerConfiguration ccu = new CompilerConfiguration(cc, this);

		List<File> fileList = new ArrayList<File>();
		//Declarations
		for (Decl d : decls) {
			fileList.add(d.codegen(new QualifiedID(packageName), imports, ccu));
		}
		
		return fileList;

	}
}
