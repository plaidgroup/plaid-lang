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

public class ChangeState implements Expression {
	
	private Token token;
	private Expression e;
	private State st;
	private boolean wipe;

	public ChangeState(Expression e, State st, boolean wipe) {
		super();
		this.e = e;
		this.st = st;
		this.wipe = wipe;
	}

	public ChangeState(Token t, Expression e, State st, boolean wipe) {
		super();
		this.token = t;
		this.e = e;
		this.st = st;
		this.wipe = wipe;
	}
	
	@Override
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

	public State getSt() {
		return st;
	}

	public void setSt(State st) {
		this.st = st;
	}
	
	public boolean wipeSemantics() {
		return wipe;
	}
}
