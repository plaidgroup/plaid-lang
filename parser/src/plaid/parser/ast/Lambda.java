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

public final class Lambda extends Expr {
	
	private final List<Arg> arguments;
	private final List<StaticArg> StaticArgsSpec;
	private final List<Arg> environment ;
	private final Expr body;
	
	public Lambda(Token token, 
			List<StaticArg> StaticArgsSpec,
			List<Arg> arguments,
			List<Arg> environment, Expr body) {
		super(token);
		this.arguments = arguments;
		this.StaticArgsSpec = StaticArgsSpec;
		this.environment = environment;
		this.body = body;
	}
	
	public List<Arg> getArguments() {
		return arguments;
	}
	
	public List<StaticArg> getStaticArgsSpec() {
		return StaticArgsSpec;
	}
	
	public List<Arg> getEnvironment() {
		return environment;
	}
	
	public Expr getBody() {
		return body;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("fn ");
		if ( getStaticArgsSpec().size() > 0) sb.append(Decl.StaticArgSpecsToString(getStaticArgsSpec()));
		sb.append("(");
		if ( getArguments().size() > 0 ) sb.append(Decl.argsToString(getArguments()));
		sb.append(")");
		if ( getEnvironment().size() > 0 ) {
			sb.append("[");
			sb.append(Decl.argsToString(getEnvironment()));
			sb.append("]");
		}
		sb.append("=>");
		sb.append(body.toString());
		return sb.toString();
	}
}
