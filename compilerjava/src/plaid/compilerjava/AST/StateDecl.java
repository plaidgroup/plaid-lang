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
import java.util.Stack;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.types.Permission;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.FieldRep;
import plaid.compilerjava.util.FileGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.MethodRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.QualifiedID;
import plaid.compilerjava.util.StateRep;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public class StateDecl implements Decl {

	private Token token;
	private ID name;
	private State stateDef;
	private QI caseOf;
	private boolean isCaseOf;
	private boolean typedef; // typedefs do not have tags associated with them
	private final Permission defaultPerm;
	private final List<MetaParameter> metaParams;
	private final List<MetaArgument> metaCaseOfArgs;
	
	public StateDecl(Token t, ID name, List<MetaParameter> metaParams, List<MetaArgument> metaCaseOfArgs, State stateDef, Permission defaultPerm, boolean typedef) {
		this(t, name, metaParams, metaCaseOfArgs, stateDef, null, defaultPerm, typedef);
	}
	
	public StateDecl(Token t, ID name, List<MetaParameter> metaParams, List<MetaArgument> metaCaseOfArgs, State stateDef, QI caseOf, Permission defaultPerm, boolean typedef) {
		super();
		this.token = t;
		this.setName(name);
		this.metaParams = metaParams;
		this.setStateDef(stateDef);
		if (caseOf == null) {
			this.caseOf = null;
			this.metaCaseOfArgs = new ArrayList<MetaArgument>();
			isCaseOf = false;
		} else {
			this.caseOf = caseOf;
			this.metaCaseOfArgs = metaCaseOfArgs;
			isCaseOf = true;
		}
		this.defaultPerm = defaultPerm;
		this.typedef = typedef;
	}

	public ID getID() {
		return name;
	}
	
	public String getName() {
		return this.name.getName();
	}

	public void setName(ID name) {
		if (Util.isKeyword(name.getName()))
			name = new ID(name.getName() + PlaidConstants.ID_SUFFIX);
		this.name = name;
	}

	public State getStateDef() {
		return stateDef;
	}

	public void setStateDef(State r) {
		this.stateDef = r;
	}

	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public QI getCaseOf() {
		return caseOf;
	}

	public void setCaseOf(QI caseOf) {
		this.caseOf = caseOf;
	}

	public boolean getIsCaseOf() {
		return isCaseOf;
	}

	public void setIsCaseOf(boolean isCaseOf) {
		this.isCaseOf = isCaseOf;
	}
	
	public boolean isTypedef() {
		return this.typedef;
	}

	public List<MetaParameter> getMetaParameters() {
		return metaParams;
	}
	
	public List<MetaArgument> getMetaCaseOfArguments() {
		return metaCaseOfArgs;
	}
	
	@Override
	public MemberRep generateHeader(PackageRep plaidpath, ImportList imports, String inPackage) {
		StateRep toRet = new StateRep(name.getName()); 
		Stack<State> worklist = new Stack<State>();
		worklist.add(stateDef);
		while(!worklist.isEmpty()) {
			State s = worklist.pop();
			if (s instanceof With) {
				With w = (With) s;
				worklist.add(w.getR1());
				worklist.add(w.getR2());
			} else if (s instanceof QI) {
				String qid = ((QI) s).toString();
				if (qid.contains(".")) { //explicit lookup - don't use the package or imports
					if (plaidpath.memberExists(qid))
						toRet.addNeed(qid);
					else
						throw new RuntimeException("Cannot resolve name '" + qid + "'.");
				} else { //lookup first in package, then using imports
					String need = checkPath(qid, plaidpath, imports, inPackage);
					if (need != null) toRet.addNeed(need);
				}
			} else if (s instanceof DeclList) {
				List<Decl> decls = ((DeclList) s).getDecls();
				for (Decl d : decls) {
					String name = d.getName();
					if (d instanceof FieldDecl) {
						toRet.addMember(new FieldRep(name));
					} else if (d instanceof MethodDecl) {
						toRet.addMember(new MethodRep(name));
					} else if (d instanceof GroupDecl ) {
						// TODO: fixme 
					} else if (d instanceof StateDecl) {
						toRet.addMember(new StateRep(name)); //TODO : nested states and their members
					} else {
						throw new RuntimeException("Type of Decl not accounted for.");
					}
				}
			}
		}
		if (isCaseOf) {
			String need = checkPath(caseOf.toString(), plaidpath, imports, inPackage);
			if (need != null) toRet.addNeed(need);
		}

		return toRet;
	}
	
	private String checkPath(String qid, PackageRep plaidpath, ImportList imports, String inPackage) {
		String potentialNeed = inPackage + "." + qid;
		if (plaidpath.memberExists(potentialNeed))
			return potentialNeed;
		else { //try imports
			for (QualifiedID anImport : imports.getImports()) {
				potentialNeed = anImport.toString();
				//TODO : this will be a problem if you can compose with Java objects
				if ((qid.contains(".") && potentialNeed.equals(qid)) || //either contains a dot and is equal to the whole import
						potentialNeed.endsWith("." + qid)) {  //or is just the suffix (but must be the complete name)
					if (plaidpath.memberExists(potentialNeed)) {
						return potentialNeed;
					} else
						throw new RuntimeException("Name '" + qid + "' not found in the plaidpath.");
				}
			}
		}
		throw new RuntimeException("Name '" + qid + "' not declared or imported("+inPackage+")");
	}
	
	@Override
	public File codegenTopDecl(QualifiedID qid, ImportList imports, CompilerConfiguration cc, Set<ID> globalVars, PackageRep plaidpath) {

		CodeGen out = new CodeGen(cc);	
		ID freshImports = IdGen.getId();
		ID theState = IdGen.getId();
		String thePackage = qid.toString();
		
		//package and needed imports
		out.declarePackage(thePackage); //package qid;
		
		//determine what members this state has and add annotations
		String repString = "";
		Set<ID> stateVars = new HashSet<ID>();
		if (plaidpath.memberExists(thePackage, name.getName())) {
			MemberRep stateRep = plaidpath.lookupMember(thePackage, name.getName());
			if (!(stateRep instanceof StateRep)) {
				throw new RuntimeException("'" + thePackage + "." + name.getName() + "' is not a state.");
			}
			repString = MemberRep.escapeJSONString(stateRep.toJSONString());
			for (MemberRep member : ((StateRep)stateRep).getMembers()) {
				stateVars.add(new ID(member.getName()));
			}
		} else {
			throw new RuntimeException("Cannot find state '" + thePackage + "." + name.getName() + "'");
		}
		
		// state annotation and class definition
		out.topStateAnnotation(name.getName(), thePackage, repString);
		out.declarePublicClass(name.getName()); out.openBlock();  // public class f {
		
		//generate code to create the package scope with imports
		out.declarePublicStaticFinalVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareGlobalScope(qid.toString(),freshImports.getName());

		
		//Tag
		//Declare variable to hold the tag
		ID tag = new ID(name.getName() + "$Tag" + PlaidConstants.ID_SUFFIX);
		out.tagAnnotation(qid.toString() + "." + name.getName());
		out.declarePublicStaticFinalVar(CodeGen.plaidTagType, tag.getName());
		
		out.openStaticBlock(); //static {	
		
		if (!typedef) { //if we have a tag	
			ID caseOfTag = IdGen.getId();
			out.declareFinalVar(CodeGen.plaidTagType, caseOfTag.getName());
			if (this.isCaseOf) {
				ID caseOfState = IdGen.getId();
				out.declareFinalVar(CodeGen.plaidStateType, caseOfState.getName());
				caseOf.codegenState(out, caseOfState, new IDList(), stateVars, null);
				out.assignToStateTag(caseOfTag.getName(), caseOfState.getName());
			} else {
				out.assignToNull(caseOfTag.getName());
			}
				
			out.assignToNewTag(tag.getName(), name.getName(), qid.toString(), caseOfTag.getName());  //tag = new PlaidTag(caseOfState)
		} else {
			out.assignToNull(tag.getName());
		}
		out.closeBlock(); // } (for static block to init tag)
	
		
		//annotation for the prototype object representing the state
		out.stateAnnotation(name.getName());
		out.declarePublicStaticFinalVar(CodeGen.plaidObjectType, name.getName());
		
		out.openStaticBlock(); //static {
		out.declareLocalScope(CodeGen.globalScope);
		out.declareFinalVar(CodeGen.plaidStateType, theState.getName());

		IDList idList = new IDList(globalVars).add(new ID(CodeGen.thisVar)); // "this" should be visible during field initializations
		
		// with caseOf State
		if (!isCaseOf) {
			stateDef.codegenState(out, theState, idList, stateVars, tag);//this is this declaration.  It will not have any members, but at runtime can forward to its enclosing (instantiated) state
		} else {
			ID caseOfState = IdGen.getId();
			ID declaredState = IdGen.getId();
			out.declareFinalVar(CodeGen.plaidStateType, caseOfState.getName());
			caseOf.codegenState(out, caseOfState, new IDList(), stateVars, null);
			out.declareFinalVar(CodeGen.plaidStateType, declaredState.getName());
			stateDef.codegenState(out, declaredState, idList, stateVars, tag);//this is this declaration.  It will not have any members, but at runtime can forward to its enclosing (instantiated) state
			out.assignToWith(theState.getName(),caseOfState.getName(),declaredState.getName());  //y = fresh1.with(fresh2); 
		}
		
		if (!typedef) 
			out.nest(tag.getName(),theState.getName());
		
		out.assignToPrototype(name.getName(), theState.getName());
		out.closeBlock(); // } (for static block to init prototype)
		
		//out.openStaticBlock(); //static {	 (to add tag)
		//out.addTopTag(name.getName(), tag.getName());
		//out.updateVarDebugInfo(name.getName());
		//out.closeBlock(); // } (for adding tag)
		
		out.closeBlock(); // } (for class Def)
		
		return FileGen.createOutputFile(name.getName(), cc.getTempDir(), out.formatFile(), qid);
		
	}

	/**
	 * This is called for nested state declarations which are mentioned in the
	 * language spec but currently not supported.
	 */
	@Override
	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {
		throw new RuntimeException("Nested states are not supported yet.");
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		name.accept(visitor);
		stateDef.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

//	@Override
//	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars,
//			String tagContext) {
//		// TODO Auto-generated method stub
//		
//	}
	
	@Override
	public String toString() {
		StringBuilder toRet= new StringBuilder();
	
		toRet.append("state " + name);
		return toRet.toString();
	}
}
