/**
 * Copyright (c) 2010 The PlaidGroup (see AUTHORS file)
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
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.FileGen;
import plaid.compilerjava.util.IdGen;
import plaid.compilerjava.util.QualifiedID;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public class FieldDecl implements Decl{
	private Token token;
	private ID f;
	private Expression e;
	
	public FieldDecl(ID f, Expression e) {
		super();
		this.setF(f);
		this.setE(e);
	}
	
	public FieldDecl(ID f) {
		super();
		this.setF(f);
	}
	
	public FieldDecl(Token t, ID f, Expression e) {
		super();
		this.token = t;
		this.setF(f);
		this.setE(e);
	}
	
	public FieldDecl(Token t, ID f) {
		super();
		this.token = t;
		this.setF(f);
	}

	public ID getF() {
		return f;
	}

	public void setF(ID f) {
		if (Util.isKeyword(f.getName()))
			f = new ID(f.getName() + PlaidConstants.ID_SUFFIX);
		this.f = f;
	}

	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}
	
	public Token getToken() {
		return token;
	}

	//Top Level Field Decl
	@Override
	public File codegen(QualifiedID qid, Imports imports, CompilerConfiguration cc) {
		CodeGen out = new CodeGen(cc);	
		List<ID> localVars = new ArrayList<ID>();
		ID freshImports = IdGen.getId();
		
		//package and needed imports
		out.declarePackage(qid.toString()); //package qid;
		
		//annotation and class definition
		out.fieldAnnotation(f.getName(), true);
		out.declarePublicClass(f.getName()); out.addBlock();  // public class f {
		
		//generate code to create the package scope with imports
		out.declarePublicStaticVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareTopScope(qid.toString(),freshImports.getName());
		
		//generate code to represent the field as a java field
		out.fieldAnnotation(f.getName(), false);
		out.declarePublicStaticVar(CodeGen.plaidObjectType, f.getName());
		out.addStaticBlock(); //static {
		e.codegen(out, f, localVars);  //initialization code
		out.closeBlock(); out.closeBlock(); //}}
		
		return FileGen.createOutputFile(f.getName(), cc.getOutputDir(), out.formatFile(), qid);
		
	}

	//Normal Field Decl
	@Override
	public void codegen(CodeGen out, ID y, List<ID> localVars) {

		out.setLocation(token);
		
		ID fresh1 = IdGen.getId();
		ID freshFieldName = IdGen.getId();
		ID x = IdGen.getId();
		
		out.fieldAnnotation(f.getName(), false);  //@representsField...
		out.declareVar(CodeGen.plaidObjectType,freshFieldName.getName());
		
		out.assignToProtoField(freshFieldName.getName(), x.getName()); // freshFieldName = new protoField... {
		
		//protofield body
		out.declareLambdaScope();
		out.declareVar(CodeGen.plaidObjectType, fresh1.getName()); //Public PlaidObect fresh1;
		e.codegen(out, fresh1, localVars);  //field initializer code
		out.ret(fresh1.getName()); // return fresh1;
		out.closeAnonymousDeclaration(); // }});
		out.addMember(y.getName(), f.getName(), freshFieldName.getName());  //y.addMember(f,freshFieldName)
		
	}

}
