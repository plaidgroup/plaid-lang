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

public class DoubleLiteral implements Expression {

	private Token token;
	private double value;
	
	
	public DoubleLiteral(double value) {
		super();
		this.value = value;
	}

	public DoubleLiteral(Token t, double value) {
		super();
		this.token = t;
		this.value = value;
	}
	
	@Override
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(int integer) {
		this.value = integer;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return ""+value;
	}
}
