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
import java.util.*;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.FileGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;
import plaid.compilerjava.util.QualifiedID;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public final class MethodDecl implements Decl {
	private final Token token;
	private final String name;
	private final Expression body;
	private final ID arg;
	private final boolean abstractMethod;
	private final MethodTypeDecl methodType;
	
	public MethodDecl(Token t, String name, Expression body, ID arg, boolean abstractMethod, MethodTypeDecl methodType) {
		this.token = t;
		if (Util.isKeyword(name))
			this.name = name + PlaidConstants.ID_SUFFIX;
		else
			this.name = name;
		this.body = body;
		if (arg == null) {
			// fresh ID for arg which will always be unit and never used
			this.arg = IdGen.getId();
		}
		else {
			this.arg = arg;
		}
		if (methodType == null)
			throw new RuntimeException("Method type is not allowed to be null.");
		this.methodType = methodType;
		this.abstractMethod = abstractMethod;
	}

	public boolean isAbstractMethod() {
		return abstractMethod;
	}
	
	public MethodTypeDecl getMethodType() {
		return this.methodType;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasArg() {
		return arg != null;
	}

	public ID getArg() {
		return arg;
	}

	public Expression getBody() {
		return body;
	}

	public Token getToken() {
		return token;
	}

	// Top-level method declaration

	public File codegenTopDecl(QualifiedID qid, ImportList imports, CompilerConfiguration cc, Set<ID> globalVars) {
		String newName = CodeGen.convertOpNames(this.name);
		ID freshReturn = IdGen.getId();
		ID freshImports = IdGen.getId();
		CodeGen out = new CodeGen(cc);
		IDList localVars = new IDList(globalVars);
		ID thisMethod = new ID(newName + "_func");
		
		//package and needed imports
		out.declarePackage(qid.toString());
		
		//annotation and class definition
		out.methodAnnotation(newName, true);
		out.declarePublicClass(newName); out.openBlock(); //public class newName {

		//generate code to create the package scope with imports
		out.declarePublicStaticFinalVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareGlobalScope(qid.toString(),freshImports.getName());
		
		if (newName.equals("main") && this.methodType.getArgTypes().get(0) == PermType.UNIT && this.methodType.getArgTypes().size() == 1) {
			out.topLevelMain(newName + "_func");
		} else {
			localVars = localVars.add(arg);
		}
		
		out.methodAnnotation(newName, false);
		out.declarePublicStaticFinalVar(CodeGen.plaidMethodType, thisMethod.getName());
		out.openStaticBlock(); // static {
		// add local scope so that the lambda creation works properly
		//out.append("final " + CodeGen.plaidScopeType + " local$c0pe = new plaid.runtime.PlaidLocalScope(" + CodeGen.globalScope + ");");
		out.declareLocalScope(CodeGen.globalScope);
		out.assignToNewLambda(thisMethod.getName(),arg.getName());
		
		out.declareVar(CodeGen.plaidObjectType,freshReturn.getName());
		//top level functions lookup with unit
		body.codegenExpr(out, freshReturn, localVars, new HashSet<ID>());

		out.ret(freshReturn.getName());
		out.closeAnonymousDeclaration(); // }});
		
		out.closeBlock(); // }  (for static block)
		out.closeBlock(); // }  (for class declaration)
		
		return FileGen.createOutputFile(newName, cc.getOutputDir(), out.formatFile(), qid);
	}

	@Override
	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, String stateContext) {
		if (abstractMethod) return; //do nothing for abstract methods
		
		String newName = CodeGen.convertOpNames(name);
		out.setLocation(token);
		ID freshMethName = IdGen.getId();
		ID freshID = IdGen.getId();
		IDList newLocalVars = localVars.add(arg);
		
		out.methodAnnotation(newName, false); //@representsMethod...
		out.declareFinalVar(CodeGen.plaidObjectType,freshMethName.getName());
		
		//if (abstractMethod) { //if abstract it will just be unit - won't be added to initialized object
		//	body.codegenExpr(out, freshMethName, newLocalVars, stateVars);
		//} else { //otherwise create a protomethod
			out.assignToProtoMethod(freshMethName.getName(), arg.getName(), stateContext + "." + name);  //freshMethName = new protofield( ... { {
			
			//body of the protomethod
			//out.declareLambdaScope();
			out.declareVar(CodeGen.plaidObjectType,freshID.getName());
			
			// update var for the debugger
			out.updateVarDebugInfo(arg.getName());
			
			body.codegenExpr(out, freshID, newLocalVars, stateVars);
			out.ret(freshID.getName() );  //return freshID;
			out.closeAnonymousDeclaration();  //}});
		//}
		
		//define the PlaidMemberDef
		// TODO: methods are immutable by default?
		ID memberDef = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidMemberDefType, memberDef.getName());
//		String definedIn;
//		if (stateContext != null)
//			definedIn = stateContext;
//		else
//			definedIn = "<Anonymous>";
		out.assignToNewMemberDef(memberDef.getName(), newName, stateContext, false);
	
		out.addMember(y.getName(), memberDef.getName(), freshMethName.getName());  //y.addMember(memberDef,freshMethName)
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		this.body.accept(visitor);
		this.arg.accept(visitor);
		this.methodType.accept(visitor);
	}
	
	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

//	@Override
//	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars,
//			String stateContext) {
//		// TODO Auto-generated method stub
//		
//	}
}
