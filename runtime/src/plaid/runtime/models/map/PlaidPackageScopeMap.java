/**
 * Copyright (c) 2010 The PlaidGroup (see AUTHORS file)
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
 
package plaid.runtime.models.map;

import java.util.Collections;
import java.util.List;

import plaid.runtime.PlaidScope;
import plaid.runtime.utils.Import;
import plaid.runtime.utils.QualifiedIdentifier;

public final class PlaidPackageScopeMap implements PlaidScope {
	private final QualifiedIdentifier qi;
	private final List<Import> imports;
	
	public PlaidPackageScopeMap (String qi, List<Import> imports) {
		this.qi = new QualifiedIdentifier(qi);
		this.imports = imports;
	}
	
	public QualifiedIdentifier getQi() {
		 return qi;
	}
	
	public List<Import> getImports() {
		return Collections.unmodifiableList(imports);
	}

	@Override
	public String toString() {
		return "PlaidPackageScopeMap("+ qi +")";
	}
}
