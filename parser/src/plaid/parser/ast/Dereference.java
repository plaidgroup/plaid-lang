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

public class Dereference implements Expression {
	private Token token;
	private Expression left;
	private ID right;
	
	public Dereference() {}

	public Dereference(Token t) {
		this.token = t;
	}
	
	public Dereference(Expression left, ID right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	public Dereference(Token t, Expression left, ID right) {
		super();
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
	
	public void setLeft(Expression e) {
		this.left = e;
	}

	public void setRight(ID e) {
		this.right = e;
	}
	
	@Override
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}

}
