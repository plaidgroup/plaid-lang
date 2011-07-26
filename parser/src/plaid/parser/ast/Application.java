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

public class Application extends Expression {
	private final Expression f;
	private final Expression argument;
	private final List<Expression> metaArgs; 


	public Application(Token t, Expression function, List<Expression> metaArgs, Expression argument) {
		super(t);
		this.f = function;
		if ( metaArgs == null ) {
			this.metaArgs = new ArrayList<Expression>();
		} else {
			this.metaArgs = metaArgs;			
		}
		this.argument = argument;
	}
	
	public Expression getFunction() {
		return f;
	}

	public Expression getArgument() {
		return argument;
	}
	
	public List<Expression> getMetaArguments() {
		return metaArgs;
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (f != null) sb.append(f.toString());
		if (metaArgs.size() > 0) sb.append("<" + exprListToString(metaArgs) + ">");
		if (argument != null ) sb.append(argument.toString());
		return sb.toString();
	}
}
