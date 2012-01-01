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

public abstract class Decl extends ASTNode implements DeclOrStateOp{
	
	protected final Identifier name;
	protected final List<Modifier> modifiers;
	protected final List<Annotation> annotations;
	
	public Decl(Token t, List<Annotation> annotations, List<Modifier> modifiers, Identifier name) {
		super(t);
		this.annotations = annotations;
		this.modifiers = modifiers;
		this.name = name;
	}
	
	public Identifier getName() {
		return this.name;
	}

	public List<Modifier> getModifiers() {
		return modifiers;
	}
	
	public List<Annotation> getAnnotations() {
		return annotations;
	}
	
	public static String annotationsToString(List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();
		
		if ( annotations.size() > 0 ) {
			sb.append("@"+annotations.get(0).getName());
			for ( int i = 1; i < annotations.size(); i++ ) {
				sb.append(" ");
				sb.append("@"+annotations.get(i).getName());
			}
		}
		
		return sb.toString();
	}
	
	public static String modifiersToString(List<Modifier> modifiers) {
		StringBuilder sb = new StringBuilder();
		
		if ( modifiers.size() > 0 ) {
			sb.append(modifiers.get(0));
			for ( int i = 1; i < modifiers.size(); i++ ) {
				sb.append(" ");
				sb.append(modifiers.get(i));
			}
		}
		
		return sb.toString();
	}
	
	public static String argsToString(List<Arg> args) {
		StringBuilder sb = new StringBuilder();
		
		if ( args.size() > 0 ) {
			sb.append(args.get(0));
			for ( int i = 1; i < args.size(); i++ ) {
				sb.append(",");
				sb.append(args.get(i));
			}
		}
		
		return sb.toString();
	}
	
	public static String StaticArgSpecsToString(List<StaticArg> StaticArgsSpec) {
		StringBuilder sb = new StringBuilder();
		
		if ( StaticArgsSpec.size() > 0 ) {
			sb.append("<");
			sb.append(StaticArgsSpec.get(0));
			for ( int i = 1; i < StaticArgsSpec.size(); i++ ) {
				sb.append(",");
				sb.append(StaticArgsSpec.get(i));
			}
			sb.append(">");			
		}
		
		return sb.toString();
	}
}

