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

import plaid.parser.Token;

public class FieldDecl implements Decl {
	private Token token;
	private ID f;
	private Expression e;
	private final boolean abstractField;
	private final boolean immutable;
	private final FieldType fieldType;
	private final boolean overrides;
	
	public boolean isOverrides() {
		return overrides;
	}

	public boolean isAbstractField() {
		return abstractField;
	}

	public FieldDecl(Token t, ID f, Expression e, boolean abstractField, boolean immutable, FieldType fieldType, boolean overrides) {
		super();
		this.token = t;
		this.setF(f);
		this.setE(e);
		this.abstractField = abstractField;
		this.fieldType = fieldType;
		this.immutable = immutable;
		this.overrides = overrides;
	}

	public FieldDecl(ID f, Expression e) {
		this(null, f, e, false, true, new FieldType(f, PermType.getDynPT(), true), false);
	}
	
	public ID getF() {
		return f;
	}
	
	public FieldType getFieldType() {
		return this.fieldType;
	}

	public void setF(ID f) {
		this.f = f;
	}

	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}
	
	@Override
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean hasToken() {
		return token != null;
	}
	
	@Override
	public String getName() {
		return this.f.getName();
	}
	

	public boolean getImmutable() {
		return this.immutable;
	}
	
	@Override
	public String toString() {
		StringBuilder toRet= new StringBuilder();
		if (abstractField) toRet.append("abstract ");
		toRet.append("field " + f.getName());
		return toRet.toString();
	}


}
