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

public class MethodDecl implements Decl {
	private Token token;
	private String name;
	private Expression body;
	private ID arg;
	private final boolean abstractMethod;
	
	public MethodDecl(Token t, String name, Expression exp, ID arg, boolean abstractMethod) {
		this.token = t;
		this.setName(name);
		this.setBody(exp);
		this.setArg(arg);
		this.abstractMethod = abstractMethod;
	}
	
	public boolean isAbstractMethod() {
		return abstractMethod;
	}

	public void setBody(Expression e) {
		this.body = e;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (Util.isKeyword(name))
			name += PlaidConstants.ID_SUFFIX;
		this.name = name;
	}

	public ID getArg() {
		return arg;
	}

	public void setArg(ID arg) {
		this.arg = arg;
	}

	public Expression getBody() {
		return body;
	}

	public Token getToken() {
		return token;
	}
	
	public File codegen(QualifiedID qid, ImportList imports, CompilerConfiguration cc) {
		
		ID freshReturn = IdGen.getId();
		ID freshImports = IdGen.getId();
		CodeGen out = new CodeGen(cc);
		IDList localVars = new IDList();
		ID thisMethod = new ID(name + "_func");
		
		//package and needed imports
		out.declarePackage(qid.toString());
		
		//annotation and class definition
		out.methodAnnotation(name, true);
		out.declarePublicClass(name); out.openBlock(); //public class name {

		//generate code to create the package scope with imports
		out.declarePublicStaticFinalVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareTopScope(qid.toString(),freshImports.getName());
		
		if (arg == null) {
			out.topLevelMain(name + "_func");
			arg = IdGen.getId();  //fresh ID for arg which will always be null/unit and never used
		} else {
			localVars = localVars.add(arg);
		}
		
		out.methodAnnotation(name, false);
		out.declarePublicStaticFinalVar(CodeGen.plaidMethodType, thisMethod.getName());
		out.openStaticBlock(); // static {
		out.assignToNewLambda(thisMethod.getName(),arg.getName());
		
		out.declareFinalVar(CodeGen.plaidObjectType,freshReturn.getName());
		body.codegen(out, freshReturn,localVars); //top level functions loopup with unit
		out.ret(freshReturn.getName());
		out.closeAnonymousDeclaration(); // }});
		
		out.closeBlock(); // }  (for static block)
		out.closeBlock(); // }  (for class declaration)
		
		return FileGen.createOutputFile(name, cc.getOutputDir(), out.formatFile(), qid);
	}

	@Override
	public void codegen(CodeGen out, ID y, IDList localVars) {
		String newName = CodeGen.convertOpNames(name);
		out.setLocation(token);
		ID freshMethName = IdGen.getId();
		ID freshID = IdGen.getId();
		if (arg == null) arg = IdGen.getId();
		IDList newLocalVars = localVars.add(arg);
		
		out.methodAnnotation(newName, false); //@representsMethod...
		out.declareFinalVar(CodeGen.plaidObjectType,freshMethName.getName());
		out.assignToProtoMethod(freshMethName.getName(),arg.getName());  //freshMethName = new protofield( ... { {
		
		//body of the protofield
		out.declareLambdaScope();
		out.declareFinalVar(CodeGen.plaidObjectType,freshID.getName());
		out.updateVar(arg.getName());
		body.codegen(out, freshID, newLocalVars);
		out.ret(freshID.getName() );  //return freshID;
		out.closeAnonymousDeclaration();  //}});
		
		out.addMember(y.getName(), newName, freshMethName.getName());  //y.addMember(name,freshMethName)
		
	}

	@Override
	public void accept(ASTVisitor visitor) {
//		body.accept(visitor);
//		arg.accept(visitor);
		visitor.visit(this);
	}
}
