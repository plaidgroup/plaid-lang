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

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class Assignment implements Expression {

	private Token token;
	private Expression target;
	private ID field;
	private Expression value;
	
	public Assignment(Expression target, ID field, Expression value) {
		super();
		this.target = target;
		this.field = field;
		this.value = value;
	}

	public Assignment(Token t, Expression target, ID field, Expression value) {
		super();
		this.token = t;
		this.target = target;
		this.field = field;
		this.value = value;
	}
	
	public Token getToken() {
		return token;
	}
	
	public Expression getTarget() {
		return target;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

	public ID getField() {
		return field;
	}

	public void setField(ID field) {
		this.field = field;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
	@Override
	public void codegen(CodeGen out, ID y, IDList localVars) {
		out.setLocation(token);
		String exceptionText = "Object does not have member " + field.getName() + ".  Assignment failed.";
		ID assignTo = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, assignTo.getName());
		value.codegen(out, assignTo, localVars);
		
		if (target == null ) { // ID is in this scope
			if (localVars.contains(field))
				out.assignToID(field.getName(),assignTo.getName()); // field = assignTo
			else if (localVars.contains(CodeGen.thisVar)) { // Find member in this object
				out.ifCondition(CodeGen.containsMember(CodeGen.thisVar,field.getName())); // if (this.containsField(field))
				out.addMember(CodeGen.thisVar, field.getName(), assignTo.getName()); // this.addMember(field,assignTo)
				out.elseCase(); // else
				out.throwNewPlaidException(exceptionText); // throw new PlaidException(...)
				out.updateVar("this", CodeGen.thisVar);
			}
			else { // There is no "this" available => it must be a write to a global variable
				// TODO: Maybe create a separate method in CodeGen for doing this?
				out.assign(field.getName() + "." + field.getName());
				out.append(assignTo.getName());
				out.append(";");
				out.updateVar(field.getName(), assignTo.getName());
			}
		}
		else { //find member in the target object
			
			//generate code for the target
			ID targetObject = IdGen.getId();
			out.declareFinalVar(CodeGen.plaidObjectType, targetObject.getName()); //Plaid
			target.codegen(out, targetObject, localVars);
			
			//generate code to return the member or throw an exception
			out.ifCondition(CodeGen.containsMember(targetObject.getName(),field.getName()));  //if (target.containsField(field))
			out.addMember(targetObject.getName(), field.getName(), assignTo.getName()); //target.addMemeber(field,assignTo)
			out.elseCase(); //else
			out.throwNewPlaidException(exceptionText);//throw new PlaidException(...)
			out.updateVar(targetObject.getName());
		}
		out.assignToUnit(y.getName());
		out.updateVar(assignTo.getName());
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		if (target != null)
			target.accept(visitor);
		if (field != null)
			field.accept(visitor);
		if (value != null)
			value.accept(visitor);
	}
	
	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

}
