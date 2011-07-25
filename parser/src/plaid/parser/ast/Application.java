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
import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class Application extends Expression {
	private final Expression f;
	private final List<Expression> arguments = new ArrayList<Expression>();
	private final boolean hasArgs;


	public Application(Token t, Expression function, Expression argument) {
		super(t);
		this.f = function;
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
	}
	
	public Expression getFunction() {
		return f;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public boolean hasArgs() {
		return this.hasArgs;
	}

	@Override
	public boolean equivalent(ASTNode other) {
		if (other instanceof Application) {
			Application otherApp = (Application) other;
			for (int i = 0; i < this.arguments.size(); i++) {
				if(!arguments.get(i).equivalent(otherApp.arguments.get(i))) {
					return false;
				}
			}
			return 
				this.f.equivalent(otherApp.f) &&
				this.hasArgs == otherApp.hasArgs;
		} else {
			return false;
		}
	}
}
