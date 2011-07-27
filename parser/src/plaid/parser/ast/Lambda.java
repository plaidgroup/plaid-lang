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

public final class Lambda extends Expression {
	
	private final List<Arg> argList;
	private final List<MetaArgSpec> metaArgsSpec;
	private final Map<Identifier, ArgSpec> env ;
	private final Expression body;
	public Lambda(Token token, 
			List<MetaArgSpec> metaArgsSpec,
			List<Arg> argList,
			Map<Identifier, ArgSpec> env, Expression body) {
		super(token);
		this.argList = argList;
		this.metaArgsSpec = metaArgsSpec;
		this.env = env;
		this.body = body;
	}
	public List<Arg> getArgList() {
		return argList;
	}
	public List<MetaArgSpec> getMetaArgsSpec() {
		return metaArgsSpec;
	}
	public Map<Identifier, ArgSpec> getEnv() {
		return env;
	}
	public Expression getBody() {
		return body;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("fn ");
		if ( metaArgsSpec.size() > 0) sb.append("<IMPLEMENTME>");
		return sb.toString();
	}	
}
