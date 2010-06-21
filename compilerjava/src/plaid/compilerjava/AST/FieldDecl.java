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

public class FieldDecl implements Decl{
	private Token token;
	private ID f;
	private Expression e;
	private final boolean abstractField;
	private final boolean immutable;
	private final FieldTypeDecl fieldType;
	
	public boolean isAbstractField() {
		return abstractField;
	}

	public FieldDecl(Token t, ID f, Expression e, boolean abstractField, boolean immutable, FieldTypeDecl fieldType) {
		super();
		this.token = t;
		this.setF(f);
		this.setE(e);
		this.abstractField = abstractField;
		this.fieldType = fieldType;
		this.immutable = immutable;
	}

	public FieldDecl(ID f, Expression e) {
		this(null, f, e, false, true, new FieldTypeDecl(null));
	}
	
	public ID getF() {
		return f;
	}
	
	public FieldTypeDecl getFieldType() {
		return this.fieldType;
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
	
	@Override
	public String getName() {
		return this.f.getName();
	}

	//Top Level Field Decl
	@Override
	public File codegen(QualifiedID qid, ImportList imports, CompilerConfiguration cc) {
		CodeGen out = new CodeGen(cc);	
		ID freshImports = IdGen.getId();
		
		//package and needed imports
		out.declarePackage(qid.toString()); //package qid;
		
		//annotation and class definition
		out.fieldAnnotation(f.getName(), true);
		out.declarePublicClass(f.getName()); out.openBlock();  // public class f {
		
		//generate code to create the package scope with imports
		out.declarePublicStaticFinalVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareTopScope(qid.toString(),freshImports.getName());
		
		//generate code to represent the field as a java field
		out.fieldAnnotation(f.getName(), false);
		out.declarePublicStaticVar(CodeGen.plaidObjectType, f.getName());
		out.openStaticBlock(); //static {
		// TODO: make this a function
		out.append("final plaid.runtime.PlaidLocalScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);");
		e.codegen(out, f, new IDList());  //initialization code
		// make this immutable if it should be
		// add this field to the global scope
		out.insertIntoScope(CodeGen.globalScope, f.getName(), this.immutable);
		out.closeBlock(); out.closeBlock(); //}}
		
		return FileGen.createOutputFile(f.getName(), cc.getOutputDir(), out.formatFile(), qid);
		
	}

	//Normal Field Decl
	@Override
	public void codegen(CodeGen out, ID y, IDList localVars) {

		out.setLocation(token);
		
		ID fresh1 = IdGen.getId();
		ID freshFieldName = IdGen.getId();
		ID x = IdGen.getId();
		
		out.fieldAnnotation(f.getName(), false);  //@representsField...
		out.declareFinalVar(CodeGen.plaidObjectType,freshFieldName.getName());
		
		out.assignToProtoField(freshFieldName.getName(), x.getName()); // freshFieldName = new protoField... {
		
		//protofield body
		out.declareLambdaScope();
		out.declareFinalVar(CodeGen.plaidObjectType, fresh1.getName()); //Public PlaidObect fresh1;
		e.codegen(out, fresh1, localVars);  //field initializer code
		out.ret(fresh1.getName()); // return fresh1;
		out.closeAnonymousDeclaration(); // }});
		out.addMember(y.getName(), f.getName(), freshFieldName.getName(), this.immutable);  //y.addMember(f,freshFieldName)
		
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		this.f.accept(visitor);
		this.fieldType.accept(visitor);
		this.e.accept(visitor);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

	public boolean getImmutable() {
		return this.immutable;
	}
}
