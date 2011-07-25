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
 
package plaid.parser.ast;

import plaid.parser.Token;

public class Case extends ASTNode {
	private QI qi;
	private ID x;
	private Expression e;
	private boolean defaultCase;
	private boolean boundVar;


	public Case(Token t, QI qi, ID x, Expression e) {
		super(t);
		this.qi = qi;
		this.x = x;
		this.e = e;
		boundVar = true;
		defaultCase = false;
	}
	
	public Case(Token t, QI qi, Expression e) {
		super(t);
		this.qi = qi;
		this.e = e;
		boundVar = false;
		defaultCase = false;
	}
	
	public Case(Token t, Expression e) {
		super(t);
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
}
