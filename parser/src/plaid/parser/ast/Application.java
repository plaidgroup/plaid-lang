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

import java.util.ArrayList;
import java.util.List;

import plaid.parser.Token;

public class Application extends Expr {
	private final Expr function;
	private final Expr argument;
	private final List<Expr> staticArgs; 


	public Application(Token t, Expr function, List<Expr> StaticArgs, Expr argument) {
		super(t);
		this.function = function;
		if ( StaticArgs == null ) {
			this.staticArgs = new ArrayList<Expr>();
		} else {
			this.staticArgs = StaticArgs;			
		}
		this.argument = argument;
	}
	
	public Expr getFunction() {
		return function;
	}

	public Expr getArgument() {
		return argument;
	}
	
	public List<Expr> getStaticArgs() {
		return staticArgs;
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (function != null) sb.append(function.toString());
		if (staticArgs.size() > 0) sb.append("<" + exprListToString(staticArgs) + ">");
		if (argument != null ) sb.append(argument.toString());
		return sb.toString();
	}
}
