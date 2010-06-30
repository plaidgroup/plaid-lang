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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.QualifiedID;

public class CompilationUnit implements ASTnode {
	private List<Decl> decls;
	private List<String> packageName;
	private ImportList imports;
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
	
	public void setImports(ImportList imports) {
		this.imports = imports;
	}
	
	public List<String> getPackageName() { 
		return packageName; 
	}
	
	public ImportList getImports() {
		return imports;
	}
	
	public void addDecl(Decl d) {
		decls.add(d);
	}
	
	public List<Decl> getDecls() { return this.decls; }

	public List<File> codegen(CompilerConfiguration cc) {
		CompilerConfiguration ccu = new CompilerConfiguration(cc, this);

		List<File> fileList = new ArrayList<File>();
		// Not sure if this is supposed to go here.  If a compilation unit is responsible for 
		// multiple physical Plaid files across packages (perhaps even just across the individual files themselves 
		// depending on the semantics we want), then it shouldn't b/c this will bridge the global 
		// namespaces of everything this CompilationUnit is responsible for compiling.
		// It also must be a Set and not the functional-style equivalent IDList because we 
		// need for the declarations made in one method to be seen in all of the others.
		Set<ID> globalVars = new HashSet<ID>();
		// this loop *must* come before the second loop that does the actual code generation
		// because we need to add all of the top-level declarations to the set of global IDs so 
		// that the order in which the top-level declarations are compiled don't matter
		for (Decl d : decls) {
			globalVars.add(new ID(d.getName()));
		}
		//Declarations
		for (Decl d : decls) {
			fileList.add(d.codegen(new QualifiedID(packageName), imports, ccu, globalVars));
		}
		
		return fileList;

	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		for (Decl d : decls)
			d.accept(visitor);
		imports.accept(visitor);
	}
	
	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}
}
