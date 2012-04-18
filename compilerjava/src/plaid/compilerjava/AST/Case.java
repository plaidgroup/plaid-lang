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

public class Case implements ASTnode {
	private Token token;
	private QI qi;
	private ID x;
	private Expression e;
	private boolean defaultCase;
	private boolean boundVar;


	public Case(Token t, QI qi, ID x, Expression e) {
		super();
		this.token = t;
		this.qi = qi;
		this.x = x;
		this.e = e;
		boundVar = true;
		defaultCase = false;
	}
	
	public Case(Token t, QI qi, Expression e) {
		super();
		this.token = t;
		this.qi = qi;
		this.e = e;
		boundVar = false;
		defaultCase = false;
	}
	
	public Case(Token t, Expression e) {
		super();
		this.token = t;
		this.e = e;
		boundVar = false;
		defaultCase = true;
	}
	public boolean isDefaultCase() {
		return defaultCase;
	}

	public void setDefaultCase(boolean defaultCase) {
		this.defaultCase = defaultCase;
	}

	public boolean isBoundVar() {
		return boundVar;
	}

	public void setBoundVar(boolean boundVar) {
		this.boundVar = boundVar;
	}

	
	public QI getQi() {
		return qi;
	}

	public void setQi(QI qi) {
		this.qi = qi;
	}
	
	public boolean hasQi() {
		return qi != null;
	}

	public ID getX() {
		return x;
	}

	public void setX(ID x) {
		this.x = x;
	}
	
	public boolean hasX() {
		return x != null;
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
	
	public void codegen(CodeGen out, ID y, ID toMatch, IDList localVars, Set<ID> stateVars) {
		IDList newLocalVars = localVars;  //might have a bound variable
		
		out.setLocation(token);
		
		// if this is the default case
		if (defaultCase) {
			out.ifCondition("true");
			out.openBlock();
			e.codegenExpr(out, y, localVars, stateVars);
			out.closeBlock();
		} 
		else {
			// otherwise generate code execute associated code if this case matches
			ID potentialMatch = IdGen.getId();
			ID potentialMatchTag = IdGen.getId();
			
			//generate code to get the state to match against
			out.declareFinalVar(CodeGen.plaidStateType,potentialMatch.getName());
			qi.codegenState(out, potentialMatch, localVars, stateVars, null);
			
			out.declareFinalVar(CodeGen.plaidTagType, potentialMatchTag.getName());
			out.assignToStateTag(potentialMatchTag.getName(), potentialMatch.getName());
			out.ifCondition(potentialMatchTag.getName() + " == null");
			out.throwNewPlaidException("No Tag to match on for " + qi.toString());
			//out.ifCondition("!" + potentialMatchTag.getName() + ".hasSuperTag()");
			//out.throwNewPlaidException(qi.toString() + " is a root tag - matching not allowed");
			
			//test if the toMatch object has the tag 
			out.ifCondition(CodeGen.objectMatchesTag(toMatch.getName(),potentialMatchTag.getName()));  //if (toMatch.hasState(potentialMatch))
			out.openBlock(); // {
			if (boundVar) { //if there is a bound variable
				out.declareFinalVar(CodeGen.plaidObjectType, x.getName()); //PlaidObject x;
				out.assignToID(x.getName(),toMatch.getName()); // x = toMatch
				newLocalVars = localVars.add(x);
			}
			e.codegenExpr(out, y, newLocalVars, stateVars);
			out.closeBlock(); // }
		}
	}
	
	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		if (qi != null)
			qi.accept(visitor);
		if (x != null)
			x.accept(visitor);
		if (e != null)
			e.accept(visitor);
	}
	
	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}
}
