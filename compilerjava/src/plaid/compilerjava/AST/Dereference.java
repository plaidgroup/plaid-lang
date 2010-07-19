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

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class Dereference implements Expression {
	private final Token token;
	private final ID left;
	private final ID right;
	
	public Dereference(ID left, ID right) {
		this(null, left, right);
	}
	
	public Dereference(Token t, ID left, ID right) {
		this.token = t;
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public ID getRight() {
		return right;
	}
	
	public Token getToken() {
		return token;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {

		
		out.setLocation(token);
		
		//generate code for the object to lookup the right ID in
		ID x = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, x.getName());

		// if the left-hand side is an ID it means we're at the end of the deref chain
//		if (left instanceof ID && !localVars.contains((ID)left) && stateVars.contains((ID)left)) {
//			ID oldLeft = (ID)left;
//			stateVars.remove(left);
//			left = new Dereference(new ID("this$plaid"), (ID)left);
//			this.left.codegenExpr(out, x, localVars, stateVars);
//			stateVars.add(oldLeft);
//		}
//		else {
			this.left.codegenExpr(out, x, localVars, stateVars);
//		}

		out.setLocation(left.getToken());
		
		//code for the lookup
		out.setLocation(right.getToken());
		out.setLocation(y.getToken());
		out.assignToLookup(y.getName(), CodeGen.convertOpNames(right.getName()), x.getName());
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		left.accept(visitor);
		right.accept(visitor);
	}
	
	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

}
