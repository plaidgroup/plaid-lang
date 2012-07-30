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
 
package plaid.collections;

import java.math.BigInteger;
import plaid.runtime.NumericSupport;

public class JavaArrayWrapper {
	
	private Object[] array;

	public JavaArrayWrapper(int size) {
		this.array = new Object[size];
	}

	public static JavaArrayWrapper create(int size) {
		return new JavaArrayWrapper(size);
	}

	public static JavaArrayWrapper create(BigInteger size) {
		return new JavaArrayWrapper(NumericSupport.checkedNarrowToInt32(size));
	}

	public Object get(int index) {
		return array[index];
	}

	public Object get(BigInteger index) {
		return get(NumericSupport.checkedNarrowToInt32(index));
	}

	public Object set(int index, Object elem) {
		Object old = array[index];
		array[index] = elem;
		return old;
	}

	public Object set(BigInteger index, Object elem) {
		return set(NumericSupport.checkedNarrowToInt32(index), elem);
	}
}