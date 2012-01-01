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


import java.util.List;

import plaid.parser.Token;

public abstract class MethodDecl extends Decl {
	private final Type returnType;
	private final List<StaticArg> StaticArgsSpec;
	private final List<Arg> arguments;
	private final List<Arg> environment;
	
	public MethodDecl(Token t, List<Annotation> annotations, List<Modifier> modifiers, 
			Type returnType, Identifier name,
			List<StaticArg> StaticArgsSpec, List<Arg> arguments,
			List<Arg> environment) {
		super(t, annotations, modifiers, name);
		this.returnType = returnType;
		this.StaticArgsSpec = StaticArgsSpec;
		this.arguments = arguments;
		this.environment = environment;
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<StaticArg> getStaticArgsSpec() {
		return StaticArgsSpec;
	}

	public List<Arg> getArguments() {
		return arguments;
	}
	public List<Arg> getEnvironment() {
		return environment;
	}
}
