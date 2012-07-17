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
import java.util.HashSet;
import java.util.Set;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.types.FieldType;
import plaid.compilerjava.types.PermType;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.FieldRep;
import plaid.compilerjava.util.FileGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.QualifiedID;
import plaid.runtime.PlaidConstants;
import plaid.runtime.Util;

public class FieldDecl implements Decl {
	private Token token;
	private ID f;
	private Expression e;
	private final boolean abstractField;
	private final boolean immutable;
	private final FieldType fieldType;
	private final boolean overrides;
	
	public boolean isOverrides() {
		return overrides;
	}

	public boolean isAbstractField() {
		return abstractField;
	}

	public FieldDecl(Token t, ID f, Expression e, boolean abstractField, boolean immutable, FieldType fieldType, boolean overrides) {
		super();
		this.token = t;
		this.setF(f);
		this.setE(e);
		this.abstractField = abstractField;
		this.fieldType = fieldType;
		this.immutable = immutable;
		this.overrides = overrides;
	}

	public FieldDecl(ID f, Expression e) {
		this(null, f, e, false, true, new FieldType(f, PermType.getDynPT(), true), false);
	}
	
	public ID getF() {
		return f;
	}
	
	public FieldType getFieldType() {
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
	public boolean hasToken() {
		return token != null;
	}
	
	@Override
	public String getName() {
		return this.f.getName();
	}

	@Override
	public MemberRep generateHeader(PackageRep plaidpath, ImportList imports, String inPackage) {
		return new FieldRep(f.getName());
	}
	
	//Top Level Field Decl
	@Override
	public File codegenTopDecl(QualifiedID qid, ImportList imports, CompilerConfiguration cc, Set<ID> globalVars, PackageRep plaidpath) {
		CodeGen out = new CodeGen(cc);
		IDList localVars = new IDList(globalVars);
		ID freshImports = IdGen.getId();
		String thePackage = qid.toString();
		
		//package and needed imports
		out.declarePackage(thePackage); //package qid;
		
		//annotation and class definition
		out.topFieldAnnotation(f.getName(), thePackage);
		out.declarePublicClass(f.getName()); out.openBlock();  // public class f {
		
		//generate code to create the package scope with imports
		out.declarePublicStaticFinalVar("java.util.List<plaid.runtime.utils.Import>",freshImports.getName());
		imports.codegen(out, freshImports);
		out.declareGlobalScope(qid.toString(),freshImports.getName());
		
		//generate code to represent the field as a java field
		out.fieldAnnotation(f.getName());
		out.declarePublicStaticVar(CodeGen.plaidObjectType, f.getName());
		out.openStaticBlock(); //static {
		// TODO: make this a function
		//out.append("final plaid.runtime.PlaidLocalScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);");
		out.declareLocalScope(CodeGen.globalScope);
		
		e.codegenExpr(out, f, localVars, new HashSet<ID>());  //initialization code
		// make this immutable if it should be
		// add this field to the global scope
		out.insertIntoScope(CodeGen.globalScope, f.getName(), this.immutable);
		out.closeBlock(); out.closeBlock(); //}}
		
		return FileGen.createOutputFile(f.getName(), cc.getTempDir(), out.formatFile(), qid);
		
	}

	//Normal Field Decl
	@Override
	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars, Set<ID> stateVars, ID tagContext) {

		//if (abstractField) return;  //do nothing for abstract fields
		
		out.setLocation(token);
		
		ID fresh1 = IdGen.getId();
		ID freshFieldName = IdGen.getId();
		ID x = IdGen.getId();
		
		out.fieldAnnotation(f.getName());  //@representsField(... toplevel = false)
		out.declareFinalVar(CodeGen.plaidObjectType,freshFieldName.getName());
		
		if (abstractField) {
			e.codegenExpr(out, freshFieldName, localVars, stateVars);  //field will just have the unit value if it is abstract - won't be initialized 
		} else {
			out.assignToProtoField(freshFieldName.getName(), x.getName()); // freshFieldName = new protoField... {
			
			//protofield body
			out.declareFinalVar(CodeGen.plaidObjectType, fresh1.getName()); //Public PlaidObect fresh1;
			e.codegenExpr(out, fresh1, localVars, stateVars);  //field initializer code
			out.ret(fresh1.getName()); // return fresh1;
			out.closeAnonymousDeclaration(); // }});
		}
		//define the PlaidMemberDef
		ID memberDef = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidMemberDefType, memberDef.getName());
		if (tagContext == null)
			out.assignToAnonymousMemberDef(memberDef.getName(), f.getName(), !immutable, overrides);
		else
			out.assignToNewMemberDef(memberDef.getName(), f.getName(), tagContext.getName(), !immutable, overrides);
		
		out.addMember(y.getName(), memberDef.getName(), freshFieldName.getName());  //y.addMember(f,freshFieldName)
		
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		this.f.accept(visitor);
		//this.fieldType.accept(visitor); //visit type as part of this node
		this.e.accept(visitor);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	public boolean getImmutable() {
		return this.immutable;
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
		if (abstractField) toRet.append("abstract ");
		toRet.append("field " + f.getName());
		return toRet.toString();
	}


}
