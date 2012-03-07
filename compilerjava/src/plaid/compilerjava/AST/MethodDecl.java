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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.types.MethodType;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.FileGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.MethodRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.QualifiedID;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public final class MethodDecl implements Decl {
	private final Token token;
	private final String name;
	private Expression body;
	private final List<ID> arguments = new ArrayList<ID>();
	private final boolean hasArgs;
	private final boolean abstractMethod;
	private final MethodType methodType;
	private final boolean overrides;
	
	public boolean isOverrides() {
		return overrides;
	}

	public MethodDecl(Token token, String name, Expression body, ID arg, boolean abstractMethod, MethodType methodType, boolean overrides) {
		this.token = token;
		if (Util.isKeyword(name))
			this.name = name + PlaidConstants.ID_SUFFIX;
		else
			this.name = name;
		this.body = body;
		if (arg == null) {
			this.hasArgs = false;
		} else {
			this.arguments.add(arg);
			this.hasArgs = true;
		}

		if (methodType == null)
			throw new RuntimeException("Method type is not allowed to be null.");
		this.methodType = methodType;
		this.abstractMethod = abstractMethod;
		this.overrides = overrides;
	}
	
	public MethodDecl(Token token, String name, Expression body, List<ID> args, boolean abstractMethod, MethodType methodType, boolean overrides) {
		this.token = token;
		if (Util.isKeyword(name))
			this.name = name + PlaidConstants.ID_SUFFIX;
		else
			this.name = name;
		this.body = body;
		
		this.arguments.addAll(args);
		this.hasArgs = this.arguments.size() > 0;

		if (methodType == null)
			throw new RuntimeException("Method type is not allowed to be null.");
		this.methodType = methodType;
		this.abstractMethod = abstractMethod;
		this.overrides = overrides;
	}

	public boolean isAbstractMethod() {
		return abstractMethod;
	}
	
	public MethodType getMethodType() {
		return this.methodType;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasArg() {
		return hasArgs;
	}

	public List<ID> getArguments() {
		return Collections.unmodifiableList(this.arguments);
	}

	public Expression getBody() {
		return body;
	}

	public MemberRep generateHeader(PackageRep plaidpath, ImportList imports, String inPackage) {
		return new MethodRep(name);
	}
	
	// Top-level method declaration
	public File codegenTopDecl(QualifiedID qid, ImportList imports, CompilerConfiguration cc, Set<ID> globalVars, PackageRep plaidpath) {
		String newName = CodeGen.convertOpNames(this.name);
		ID freshReturn = IdGen.getId();
		ID freshImports = IdGen.getId();
		CodeGen out = new CodeGen(cc);
		IDList localVars = new IDList(globalVars);
		ID thisMethod = new ID(newName + "_func");
		String thePackage = qid.toString();
		
		//package and needed imports
		out.declarePackage(thePackage);
		
		//annotation and class definition
		out.topMethodAnnotation(newName, thePackage);
		out.declarePublicClass(newName); out.openBlock(); //public class newName {

		//generate code to create the package scope with imports
		out.declarePublicStaticFinalVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareGlobalScope(qid.toString(),freshImports.getName());
		
		ID argID;
		if (!this.hasArgs) { 
			argID = ID.DEFAULTPARAMID;
		} else if (arguments.size() == 1) {
			argID = arguments.get(0);
		} else {
			argID = new ID("pA1R"+ PlaidConstants.ID_SUFFIX);
			if (!abstractMethod) body = RewriteUtil.getBodyWithPairExtractions(arguments, argID, 1, body);
		}
		
		if (newName.equals("main") /*&& !hasArg()*/) {
			out.topLevelMain(newName + "_func");
		} else {
			localVars = localVars.add(argID);
		}
		
		out.methodAnnotation(newName);
		out.declarePublicStaticFinalVar(CodeGen.plaidMethodType, thisMethod.getName());
		out.openStaticBlock(); // static {
		// add local scope so that the lambda creation works properly
		//out.append("final " + CodeGen.plaidScopeType + " local$c0pe = new plaid.runtime.PlaidLocalScope(" + CodeGen.globalScope + ");");
		out.declareLocalScope(CodeGen.globalScope);
		out.assignToNewLambda(thisMethod.getName(),argID.getName());
		
		out.declareVar(CodeGen.plaidObjectType,freshReturn.getName());
		//top level functions lookup with unit
		body.codegenExpr(out, freshReturn, localVars, new HashSet<ID>());

		out.ret(freshReturn.getName());
		out.closeAnonymousDeclaration(); // }});
		
		out.closeBlock(); // }  (for static block)
		out.closeBlock(); // }  (for class declaration)
		
		return FileGen.createOutputFile(newName, cc.getTempDir(), out.formatFile(), qid);
	}

	@Override
	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {
		//if (abstractMethod) return; //do nothing for abstract methods
		
		String newName = CodeGen.convertOpNames(name);
		out.setLocation(token);
		ID freshMethName = IdGen.getId();
		ID freshID = IdGen.getId();
		
		ID argID;
		if (!this.hasArgs) { 
			argID = ID.DEFAULTPARAMID;
		} else if (arguments.size() == 1) {
			argID = arguments.get(0);
		} else {
			argID = new ID("pA1R"+ PlaidConstants.ID_SUFFIX);
			if (!abstractMethod) body = RewriteUtil.getBodyWithPairExtractions(arguments, argID, 1, body);
		}
		
		IDList newLocalVars = localVars.add(argID);
		
		out.methodAnnotation(newName); //@representsMethod(... toplevel = false)
		out.declareFinalVar(CodeGen.plaidObjectType,freshMethName.getName());
		
		if (abstractMethod) { //if abstract it will just be unit - won't be added to initialized object
			body.codegenExpr(out, freshMethName, newLocalVars, stateVars);
		} else { //otherwise create a protomethod
			if ( tagContext != null) {
				out.assignToProtoMethod(freshMethName.getName(), argID.getName(), tagContext.getName() +".getPath()" + "+ \".\" + \"" + name + "\"");  //freshMethName = new protofield( ... { {
			} else {
				out.assignToProtoMethod(freshMethName.getName(), argID.getName(), "\""+name + "\"");  //freshMethName = new protofield( ... { {				
			}
			
			//body of the protomethod
			//out.declareLambdaScope();
			out.declareVar(CodeGen.plaidObjectType,freshID.getName());
			
			// update var for the debugger
			out.updateVarDebugInfo(argID.getName());
			
			body.codegenExpr(out, freshID, newLocalVars, stateVars);
			out.ret(freshID.getName() );  //return freshID;
			out.closeAnonymousDeclaration();  //}});
		}
		
		//define the PlaidMemberDef
		// TODO: methods are immutable by default?
		ID memberDef = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidMemberDefType, memberDef.getName());
		if (tagContext == null)
			out.assignToAnonymousMemberDef(memberDef.getName(), newName, false, overrides);
		else
			out.assignToNewMemberDef(memberDef.getName(), newName, tagContext.getName(), false, overrides);
		
		
		out.addMember(y.getName(), memberDef.getName(), freshMethName.getName());  //y.addMember(memberDef,freshMethName)
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		this.body.accept(visitor);
		for (Expression arg : arguments) arg.accept(visitor);
		//this.methodType.accept(visitor);  //TODO : visit types not as separate AST nodes
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}

//	@Override
//	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars,
//			String stateContext) {
//		// TODO Auto-generated method stub
//		
//	}
	
	@Override
	public String toString() {
		StringBuilder toRet= new StringBuilder();
		if (abstractMethod) toRet.append("abstract ");
		toRet.append("method " + name);
		return toRet.toString();
	}
}
