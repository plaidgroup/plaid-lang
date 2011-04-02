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

package plaid.runtime;

import java.util.Set;

import plaid.runtime.types.PlaidPermission;

public interface PlaidState extends PlaidObject {
	/**
	 * Generate a new instance of this plaid object.
	 * 
	 * @return
	 * @throws PlaidException
	 */
	public PlaidObject instantiate(PlaidObject ...args) throws PlaidException;
	
	/**
	 * Generate a new instance of this plaid object with the specified permission.
	 * 
	 * @return
	 * @throws PlaidException
	 */
	public PlaidObject instantiate(PlaidPermission initPerm, PlaidObject ...args) throws PlaidException;
	
	/**
	 * Combines the current plaid object with the plaid objects
	 * provided. The newly constructed object will be returned.
	 *	
	 * @param objects   List of PlaidObject that should be merged with.
	 * @return
	 */
	public PlaidState with(PlaidState ...args) throws PlaidException;	
	
	/**
	 * Similar to With, but treating the parameter state as an initialization
	 * to the current state (syntax S { ... })
	 *	
	 * @param inti   the initialization state
	 * @return
	 */
	public PlaidState initState(PlaidState init) throws PlaidException;
	
	/**
	 * returns the state's prototype PlaidObject
	 *
	 * @return
	 */
	public PlaidObject getPrototype() throws PlaidException;
	
	public boolean hasTag() throws PlaidException;
	
	public PlaidTag getTag() throws PlaidException;
	
	public String getPath();
}
