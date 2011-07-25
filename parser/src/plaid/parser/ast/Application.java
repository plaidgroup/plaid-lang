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
	private final List<MetaArgument> metaArgs;


	public Application(Token t, Expression function, Expression argument) {
		super(t);
		this.f = function;
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.metaArgs = new ArrayList<MetaArgument>();
	}
	
	public Application(Token t, Expression function, List<MetaArgument>  metaArgs, Expression argument) {
		super(t);
		this.f = function;
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.metaArgs = metaArgs;
	}
	
	public Application(Token t, Expression function, List<Expression> arguments) {
		super(t);
		this.f = function;
		this.arguments.addAll(arguments);
		if (this.arguments.size() == 1 && this.arguments.get(0) instanceof UnitLiteral) {
			this.hasArgs = false;
			this.arguments.clear();
		} else {
			this.hasArgs = true;
		}
		this.metaArgs = new ArrayList<MetaArgument>();
	}
	
	public Application(Token t, Expression function, List<MetaArgument> metaArgs, List<Expression> arguments) {
		super(t);
		this.f = function;
		this.arguments.addAll(arguments);
		if (this.arguments.size() == 1 && this.arguments.get(0) instanceof UnitLiteral) {
			this.hasArgs = false;
			this.arguments.clear();
		} else {
			this.hasArgs = true;
		}
		this.metaArgs = metaArgs;
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
	
	List<MetaArgument> getMetaArgs() {
		return metaArgs;
	}
}
