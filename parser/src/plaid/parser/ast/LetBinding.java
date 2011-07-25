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

public class LetBinding extends Expression {

	private Identifier x;
	private Expression exp, body;
	private boolean mutable;
	private final Type type;
	private final boolean annotated;
	private final boolean take;

	public LetBinding(Token t, Identifier x, Expression e1, Expression e2, boolean mutable) {
		this(t, x, e1, e2, mutable, null, false);
	}
	
	public LetBinding(Token t, Identifier x, Expression e1, Expression e2, boolean mutable, Type type) {
		this(t, x, e1, e2, mutable, type, false);
	}
	
	public LetBinding(Token t, Identifier x, Expression e1, Expression e2, boolean mutable, Type type, boolean take) {
		super(t);
		this.x = x;
		this.exp = e1;
		this.body = e2;
		this.mutable = mutable;
		this.type = type;
		this.annotated = true;
		this.take = take;
	}

	public boolean isMutable() {
		return mutable;
	}
	
	public boolean isAnnotated() {
			return annotated;
	}
	
	public boolean isTaken() {
		return take;
	}
	
	public Type getType() {
		return type;
	}

	public Identifier getX() {
		return x;
	}

	public Expression getExp() {
		return exp;
	}

	public Expression getBody() {
		return body;
	}

	public void setBody(Expression body) {
		this.body = body;
	}
}
