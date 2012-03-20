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

public class With extends StateExpr {

	private final StateExpr r1,r2;

	public With(Token t, StateExpr r1, StateExpr r2) {
		super(t);
		this.r1 = r1;
		this.r2 = r2;
	}
	
	public StateExpr getR1() {
		return r1;
	}

	public StateExpr getR2() {
		return r2;
	}

	@Override
	public String toString() {
		return r1.toString() + " with " + r2.toString();
	}
}
