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

package plaid.lang;

public class JavaInteropTestHelper {
	
	public static String addNewLine(String a) {
		return a + "\n";
	}

	public static String concatenate(String a, String b) {
		return a + b;
	}

	public static int mulDiv(int a, int b, int c) {
		return (a * b) / c;
	}

	public static long mulDivLong(long a, long b, long c) {
		return (a * b) / c;
	}

	public static long longIdent(long x) { return x; }
	public static int intIdent(int x) { return x; }
	public static short shortIdent(short x) { return x; }
	public static byte byteIdent(byte x) { return x; }

	public static boolean select(long x) { return true; }
	public static boolean select(short x) { return false; }

	public static int selectMulti(long x, long y, short z) { return 1; }
	public static int selectMulti(int x, int y, int z) { return 2; }
	public static int selectMulti(long x, byte y, short z) { return 3; }
}