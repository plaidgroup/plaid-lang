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

/**
 * The PlaidMethod is used as a common interface to dynamically created closures.
 * On execution the 'invoke' method will be called. The pthis object represents 
 * the receiver object and the args object the parameters. Multiple args are handled
 * via tuples.
 */
public interface PlaidMethod extends PlaidObject {
	/**
	 * This method is invoked to execute the closure body. 
	 * 
	 * @param pthis The receiver object.
	 * @param args  The parameter(s).
	 * @return      The return value of the method call.
	 */
	public PlaidObject invoke(PlaidObject args) throws PlaidException;
}
