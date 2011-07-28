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

public final class Lambda extends Expression {
	
	private final List<Arg> arguments;
	private final List<MetaArgSpec> metaArgsSpec;
	private final List<Arg> env ;
	private final Expression body;
	
	public Lambda(Token token, 
			List<MetaArgSpec> metaArgsSpec,
			List<Arg> arguments,
			List<Arg> env, Expression body) {
		super(token);
		this.arguments = arguments;
		this.metaArgsSpec = metaArgsSpec;
		this.env = env;
		this.body = body;
	}
	
	public List<Arg> getArguments() {
		return arguments;
	}
	
	public List<MetaArgSpec> getMetaArgsSpec() {
		return metaArgsSpec;
	}
	
	public List<Arg> getEnv() {
		return env;
	}
	
	public Expression getBody() {
		return body;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("fn ");
		if ( getMetaArgsSpec().size() > 0) sb.append(Decl.metaArgSpecsToString(getMetaArgsSpec()));
		sb.append("(");
		if ( getArguments().size() > 0 ) sb.append(Decl.argsToString(getArguments()));
		sb.append(")");
		if ( getEnv().size() > 0 ) {
			sb.append("[");
			sb.append(Decl.argsToString(getEnv()));
			sb.append("]");
		}
		sb.append("=>");
		sb.append(body.toString());
		return sb.toString();
	}
}
