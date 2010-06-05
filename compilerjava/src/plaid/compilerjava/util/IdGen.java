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
 
package plaid.compilerjava.util;

import plaid.compilerjava.AST.ID;
import plaid.runtime.PlaidConstants;

public class IdGen {
	private static int counter = 0;
	private final static String prefix = "vAr";
	
	//called at the beginging of code generation to reset the counter
	public static void init() {
		counter = 0;
	}

	//Generate a new fresh ID for the compiler
	public static ID getId() {
		String idName = prefix + counter + PlaidConstants.ID_SUFFIX;
		counter++;
		return new ID(idName);
	}

}
