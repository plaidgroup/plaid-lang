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

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidScope;

public final class PlaidLambdaScopeMap implements PlaidScope {
	private final PlaidObject thisVar;
	private final PlaidScope parent;
	
	public PlaidLambdaScopeMap(PlaidScope parent, PlaidObject thisVar) {
		super();
		this.thisVar = thisVar;
		this.parent = parent;
		PlaidRuntime.getRuntime().updateVar("this", thisVar);
	}
	
	public PlaidObject getThisVar() {
		return thisVar;
	}
	
	public PlaidScope getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return "PlaidLamdbaScopeMap(" + thisVar + ")";
	}
}
