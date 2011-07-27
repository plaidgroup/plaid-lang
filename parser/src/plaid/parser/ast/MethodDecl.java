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
import java.util.Map;

import plaid.parser.Token;

public class MethodDecl extends Decl {
	private final Type type;
	private final List<MetaArgSpec> metaArgsSpec;
	private final List<Arg> arguments;
	private final Map<Identifier, ArgSpec> env;
	
	public MethodDecl(Token t, List<Modifier> modifiers, 
			Type type, Identifier name,
			List<MetaArgSpec> metaArgsSpec, List<Arg> arguments,
			Map<Identifier, ArgSpec> env) {
		super(t, modifiers, name);
		this.type = type;
		this.metaArgsSpec = metaArgsSpec;
		this.arguments = arguments;
		this.env = env;
	}
	public Type getType() {
		return type;
	}
	public List<MetaArgSpec> getMetaArgsSpec() {
		return metaArgsSpec;
	}
	public List<Arg> getArguments() {
		return arguments;
	}
	public Map<Identifier, ArgSpec> getEnv() {
		return env;
	}
}
