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
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.QualifiedID;

public class CompilationUnit implements ASTnode {
	private List<Decl> decls;
	private List<String> packageName;
	private String packageString;
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
	
	@Override
	public boolean hasToken() {
		return false;
	}
	
	public String getPackageString() {
		if (packageString == null){
			String temp = "";
			for (String s : packageName) 
				temp += s + ".";
			packageString = temp.substring(0,temp.length() - 1);
		}
		return packageString;
	}
	
	public ImportList getImports() {
		return imports;
	}
	
	public void addDecl(Decl d) {
		decls.add(d);
	}
	
	public List<Decl> getDecls() { return this.decls; }
	
	public List<File> codegen(CompilerConfiguration cc, PackageRep plaidpath) {
		CompilerConfiguration ccu = new CompilerConfiguration(cc, this);

		List<File> fileList = new ArrayList<File>();
		// Not sure if this is supposed to go here.  If a compilation unit is responsible for 
		// multiple physical Plaid files across packages (perhaps even just across the individual files themselves 
		// depending on the semantics we want), then it shouldn't b/c this will bridge the global 
		// namespaces of everything this CompilationUnit is responsible for compiling.
		// It also must be a Set and not the functional-style equivalent IDList because we 
		// need for the declarations made in one method to be seen in all of the others.
		
		//move this stuff inside the decls and give it the plaidpath instead
		
		Set<ID> globalVars = new HashSet<ID>();
		// this loop *must* come before the second loop that does the actual code generation
		// because we need to add all of the top-level declarations to the set of global IDs so 
		// that the order in which the top-level declarations are compiled don't matter
		for (MemberRep m : plaidpath.getPackageMembers(getPackageString())) {  //TODO : current the global vars are everything in this package - is this right?
			globalVars.add(new ID(m.getName()));
		}
		//Declarations
		for (Decl d : decls) {
			fileList.add(d.codegenTopDecl(new QualifiedID(packageName), imports, ccu, globalVars, plaidpath));
		}
		
		return fileList;

	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		for (Decl d : decls)
			d.accept(visitor);
		imports.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
	
	@Override
	public String toString() {
		StringBuilder toRet = new StringBuilder("package: ");
		toRet.append(getPackageString());
		toRet.append("\n declares:\n");
		for(Decl d : decls) toRet.append("\t" + d.getName() + "\n");
		
		return toRet.toString();
	}
}
