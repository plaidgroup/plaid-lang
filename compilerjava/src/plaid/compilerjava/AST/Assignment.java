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
		ID assignTo = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, assignTo.getName());
		value.codegen(out, assignTo, localVars);
		
		// Generates (e.g.):
		// PlaidObject var$foo = local$c0pe.lookup('y')
		// local$c0pe.update('x', var$foo)
		if (this.target == null) {
			// need to make sure we've loaded it before
			out.lookupInCurrentScope(this.field.getName());
			out.updateVar(this.field, assignTo);
			out.assignToID(y.getName(), assignTo.getName());
		}
		// we have a target, so we need to check if that particular 
		// field of the target is mutable and if so assign the new value
		else {
			// evaluate the target
			ID temp = IdGen.getId();
			out.declareFinalVar(CodeGen.plaidObjectType, temp.getName());
			target.codegen(out, temp, localVars);
			out.updateMember(temp.getName(), this.field.getName(), assignTo.getName());
			out.assignToID(y.getName(), assignTo.getName());
		}

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
