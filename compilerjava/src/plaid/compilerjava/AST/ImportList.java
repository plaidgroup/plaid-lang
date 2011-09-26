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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.QualifiedID;

public class ImportList implements ASTnode {
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
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public List<QualifiedID> getImports() {
		return imports;
	}

	public void setImports(List<QualifiedID> imports) {
		this.imports = imports;
	}
	
	private void addStdLibrary() {
		List<String> globals = new ArrayList<String>();
		globals.add("plaid");
		globals.add("lang");
		globals.add("globals");
		globals.add("*");
		
		List<String> stdLib = new ArrayList<String>();
		stdLib.add("plaid");
		stdLib.add("lang");
		stdLib.add("*");
		
		/*kbn - not working as I hoped */
//		List<String> makeLinkedList = new ArrayList<String>();
//		makeLinkedList.add("plaid");
//		makeLinkedList.add("collections");
//		makeLinkedList.add("makeLinkedList");
		
		// We don't want to have the same import twice
		boolean haveStdLib = false;
		boolean haveGlobals = false;
		boolean haveMakeLL = true;
		for (QualifiedID qi : imports) {
			if (qi.toString().equals("plaid.lang.*")) {
				haveStdLib = true;
				if (haveGlobals && haveMakeLL) break;
			}
			if(qi.toString().equals("plaid.lang.globals.*")) {
				haveGlobals = true;
				if (haveStdLib && haveMakeLL) break;
			}
//			if(qi.toString().equals("plaid.collections.makeLinkedList")) {
//				haveMakeLL = true;
//				if (haveStdLib && haveGlobals) break;
//			}
		}
		if (!haveStdLib)
			imports.add(new QualifiedID(stdLib));
		if (!haveGlobals)
			imports.add(new QualifiedID(globals));
//		if (!haveMakeLL)
//			imports.add(new QualifiedID(makeLinkedList));
	}

	public void checkAndExpandImports(PackageRep plaidpath, List<String> declMembers, String thePackage) {
		//first make sure the std library is there
		addStdLibrary();
		
		//make sure we don't have multiple imports with the same name
		//also give precedence to explicitly named imports over .* imports (even if this is first - same as Java)
		Map<String, Boolean> fromStar = new HashMap<String, Boolean>();
		Map<String, String> fromPackage = new HashMap<String, String>();	
		List<String> duplicateStarMembers = new ArrayList<String>();
		List<String> javaPackages = new ArrayList<String>();
		
		//then get rid of all .* imports (efficiency during runtime)
		for (QualifiedID qid : imports) {
			String theImport = qid.toString();
			
			if (!theImport.contains(".") || theImport.endsWith(".")) throw new RuntimeException("malformed import " + theImport);
			
			String packageName = theImport.substring(0,theImport.lastIndexOf("."));
			String memberName = theImport.substring(theImport.lastIndexOf(".") + 1);
			if (memberName.equals("*")) {
				List<MemberRep> pMembers = plaidpath.getPackageMembers(theImport.substring(0, theImport.length() - 2));
				if (pMembers == null) { // 
					if (theImport.startsWith("java")) //TODO:handle java better
						javaPackages.add(theImport);
					else
						throw new RuntimeException("Cannot find package " + packageName + ".");
				} else {
					for (MemberRep m : pMembers) {
						memberName = m.getName();
						if (declMembers.contains(memberName)) {
							//do nothing - do not import members defined in this decl except for from the current package (below)
						} else if (fromPackage.containsKey(memberName)) {
							if (fromStar.get(memberName) == true) { //indicate that we have duplicate star imports
								duplicateStarMembers.add(memberName);
								fromPackage.put(memberName,fromPackage.get(memberName) + ";" + packageName);
							} else {
								//do nothing - explicit import takes precedence
							}
						} else {
							fromPackage.put(memberName, packageName);
							fromStar.put(memberName, true); //could be overriden by explicit import later
						}
					}
				}
			} else {
				if (declMembers.contains(memberName))
					throw new RuntimeException("Member " + memberName + " explicitly imported into a compilation unit where it is also defined.");
				if (fromPackage.containsKey(memberName) && fromStar.get(memberName) == false) {
					throw new RuntimeException("Member " + memberName + " explicitly imported from both " + 
							packageName + " and " + fromPackage.get(memberName) + ".");
					
				}
				
				fromPackage.put(memberName, packageName);
				fromStar.put(memberName, false);
				duplicateStarMembers.remove(memberName); //made explicit - remove if looked ambiguous earlier

			}
		}
		
		//import packagemembers including those declared in this compilation unit
		for (MemberRep m : plaidpath.getPackageMembers(thePackage)) {
			String member = m.getName();
			if (fromPackage.containsKey(member)) { //imported somewhere else
				if (fromStar.get(member) == true) { //package defined overrides a star import
					fromPackage.put(member, thePackage);
				} else {
					//Do nothing - explicit import does override package declaration
				}
			} else {
				fromPackage.put(member, thePackage);
			}
		}
		
		// check we don't have any ambiguous * imports
		if (!duplicateStarMembers.isEmpty()) {
			StringBuilder errMsg = new StringBuilder("Must make following ambiguous imports explicit");
			String nextChar = ":";
			for (String member : duplicateStarMembers) {
				errMsg.append(nextChar + " member " + member + " from packages: ");
				
				for (String pack : fromPackage.get(member).split(";")) {
					errMsg.append(pack + ", ");
				}
				errMsg.delete(errMsg.length() - 2, errMsg.length());
				
				nextChar = ";";
			}
			
			throw new RuntimeException(errMsg.toString());
		}
		
		//create new import list, order doesn't matter since all of the imported members have unique names
		imports = new ArrayList<QualifiedID>();
		for (String member : fromPackage.keySet()) imports.add(new QualifiedID(fromPackage.get(member) + "." + member, "."));
		for (String javaPackage : javaPackages) imports.add(new QualifiedID(javaPackage, "."));
		
	}
	
	public void codegen(CodeGen out, ID y) {
		// We want to include plaid.lang.* by default, so add it if it's missing
		//addStdLibrary(); do this in a prior step
		
		out.openStaticBlock();  // static {
		out.assignToNewJavaObject(y.getName(),"java.util.ArrayList<plaid.runtime.utils.Import>"); //y = new java..();
		
		for (QualifiedID qi : imports) {
			out.append(y.getName() + ".add(new plaid.runtime.utils.Import(\"" + qi.toString() + "\"));");
		}

		out.closeBlock();
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		// do nothing (no children)
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public String toString() {
		return "ImportList" + imports;
	}
}
