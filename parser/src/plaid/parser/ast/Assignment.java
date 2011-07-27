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

public class Assignment extends Expression {

	private final Expression target;
	private final Identifier field;
	private final Expression value;
	private final boolean hasTarget;
	

	public Assignment(Token t, Expression target, Identifier field, Expression value) {
		super(t);
		this.target = target;
		this.hasTarget = target != null;
		this.field = field;
		this.value = value;
	}
	
	public Expression getTarget() {
		return target;
	}
	
	public boolean hasTarget() {
		return this.hasTarget;
	}

	public Identifier getField() {
		return field;
	}

	public Expression getValue() {
		return value;
	}

	@Override
	public String toString() {
		if ( target == null ) {
			return field.toString() + "=" + value.toString();			
		} else {
			return target.toString() + "." + field.toString() + "=" + value.toString();
		}
	}
}
