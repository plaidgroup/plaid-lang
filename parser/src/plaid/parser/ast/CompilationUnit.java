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
 
package plaid.parser.ast;


import java.io.File;
import java.util.List;

public class CompilationUnit extends ASTNode {
	private final List<Decl> decls;
	private final List<String> packageName;
	private final List<Import> imports;
	private final File sourceFile;

	public CompilationUnit(List<Decl> decls, File sourceFile, List<Import> imports, List<String> packageName) {
		super(DEFAULT_TOKEN);
		this.decls=decls;
		this.sourceFile = sourceFile;
		this.imports = imports;
		this.packageName = packageName;
	}
	
	public File getSourceFile() {
		return sourceFile;
	}
	
	public List<String> getPackageName() { 
		return packageName; 
	}
	
	public List<Import> getImports() {
		return imports;
	}

	public List<Decl> getDecls() {
		return decls;
	}

	@Override
	public boolean equivalent(ASTNode other) {
		if(other instanceof CompilationUnit) {
			CompilationUnit otherCU = (CompilationUnit) other;
			if(!sourceFile.equals(otherCU.sourceFile)) {
				return false;
			}
			for(int i=0; i<packageName.size(); i++) {
				if(!packageName.get(i).equals(otherCU.packageName.get(i)))
					return false;
			}
			for(int i=0; i<imports.size(); i++) {
				if(!imports.get(i).equivalent(otherCU.imports.get(i)))
					return false;
			}
			for(int i=0; i<decls.size(); i++) {
				if(!decls.get(i).equivalent(otherCU.decls.get(i)))
					return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
}
