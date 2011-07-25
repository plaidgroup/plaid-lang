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


import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public final class MethodDecl extends Decl {
	private final Expression body;
	private final List<Identifier> arguments;
	private final boolean hasArgs;
	private final boolean abstractMethod;
	private final Type methodType;
	private final boolean overrides;
	
	public boolean isOverrides() {
		return overrides;
	}

	public MethodDecl(Token token, Identifier name, Expression body, Identifier arg, boolean abstractMethod, Type methodType, boolean overrides) {
		this(token, name, body, Collections.singletonList(arg), abstractMethod, methodType, overrides);
	}
	
	public MethodDecl(Token token, Identifier name, Expression body, List<Identifier> args, boolean abstractMethod, Type methodType, boolean overrides) {
		super(token, name);
		this.body = body;
		this.arguments = args;
		this.hasArgs = this.arguments.size() > 0;
		if (methodType == null)
			throw new RuntimeException("Method type is not allowed to be null.");
		this.methodType = methodType;
		this.abstractMethod = abstractMethod;
		this.overrides = overrides;
	}

	public boolean isAbstractMethod() {
		return abstractMethod;
	}
	
	public Type getMethodType() {
		return this.methodType;
	}
	
	public boolean hasArg() {
		return hasArgs;
	}

	public List<Identifier> getArguments() {
		return Collections.unmodifiableList(this.arguments);
	}

	public Expression getBody() {
		return body;
	}

}
