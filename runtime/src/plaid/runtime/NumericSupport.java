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

import java.math.BigInteger;
import java.math.BigDecimal;

/**
 * Set of utility methods to help with interop between Plaid and Java numeric types,
 * specifically where the boxed numeric types lack method variants of the standard
 * arithmetic operators.
 */
public class NumericSupport {
	
	public static Byte    addInt8(Byte a, Byte b) { return (byte)(a + b); }
	public static Short   addInt16(Short a, Short b) { return (short)(a + b); }
	public static Integer addInt32(Integer a, Integer b) { return a + b; }
	public static Long    addInt64(Long a, Long b) { return a + b; }
	public static Float   addFloat32(Float a, Float b) { return a + b; }
	public static Double  addFloat64(Double a, Double b) { return a + b; }
	public static BigInteger addInteger(BigInteger a, BigInteger b) { return a.add(b); }
	public static BigDecimal addDecimal(BigDecimal a, BigDecimal b) { return a.add(b); }
	public static BigRational addRational(BigRational a, BigRational b) { return a.add(b); }

	public static Byte    subtractInt8(Byte a, Byte b) { return (byte)(a - b); }
	public static Short   subtractInt16(Short a, Short b) { return (short)(a - b); }
	public static Integer subtractInt32(Integer a, Integer b) { return a - b; }
	public static Long    subtractInt64(Long a, Long b) { return a - b; }
	public static Float   subtractFloat32(Float a, Float b) { return a - b; }
	public static Double  subtractFloat64(Double a, Double b) { return a - b; }
	public static BigInteger subtractInteger(BigInteger a, BigInteger b) { return a.subtract(b); }
	public static BigDecimal subtractDecimal(BigDecimal a, BigDecimal b) { return a.subtract(b); }
	public static BigRational subtractRational(BigRational a, BigRational b) { return a.subtract(b); }

	public static Byte    multiplyInt8(Byte a, Byte b) { return (byte)(a * b); }
	public static Short   multiplyInt16(Short a, Short b) { return (short)(a * b); }
	public static Integer multiplyInt32(Integer a, Integer b) { return a * b; }
	public static Long    multiplyInt64(Long a, Long b) { return a * b; }
	public static Float   multiplyFloat32(Float a, Float b) { return a * b; }
	public static Double  multiplyFloat64(Double a, Double b) { return a * b; }
	public static BigInteger multiplyInteger(BigInteger a, BigInteger b) { return a.multiply(b); }
	public static BigDecimal multiplyDecimal(BigDecimal a, BigDecimal b) { return a.multiply(b); }
	public static BigRational multiplyRational(BigRational a, BigRational b) { return a.multiply(b); }

	public static Byte    divideInt8(Byte a, Byte b) { return (byte)(a / b); }
	public static Short   divideInt16(Short a, Short b) { return (short)(a / b); }
	public static Integer divideInt32(Integer a, Integer b) { return a / b; }
	public static Long    divideInt64(Long a, Long b) { return a / b; }
	public static Float   divideFloat32(Float a, Float b) { return a / b; }
	public static Double  divideFloat64(Double a, Double b) { return a / b; }
	public static BigInteger divideInteger(BigInteger a, BigInteger b) { return a.divide(b); }
	public static BigDecimal divideDecimal(BigDecimal a, BigDecimal b) { return a.divide(b); }
	public static BigRational divideRational(BigRational a, BigRational b) { return a.divide(b); }

	public static Byte    modulusInt8(Byte a, Byte b) { return (byte)(a % b); }
	public static Short   modulusInt16(Short a, Short b) { return (short)(a % b); }
	public static Integer modulusInt32(Integer a, Integer b) { return a % b; }
	public static Long    modulusInt64(Long a, Long b) { return a % b; }
	public static BigInteger modulusInteger(BigInteger a, BigInteger b) { return a.mod(b); }
	public static BigRational modulusRational(BigRational a, BigRational b) { return a.mod(b); }
	// NOTE: modulus is not supported by BigDecimal

	public static boolean lessThanCharacter(Character a, Character b) { return a < b; }
	public static boolean lessThanInt8(Byte a, Byte b) { return a < b; }
	public static boolean lessThanInt16(Short a, Short b) { return a < b; }
	public static boolean lessThanInt32(Integer a, Integer b) { return a < b; }
	public static boolean lessThanInt64(Long a, Long b) { return a < b; }
	public static boolean lessThanFloat32(Float a, Float b) { return a < b; }
	public static boolean lessThanFloat64(Double a, Double b) { return a < b; }
	public static boolean lessThanInteger(BigInteger a, BigInteger b) { return a.compareTo(b) == -1; }
	public static boolean lessThanDecimal(BigDecimal a, BigDecimal b) { return a.compareTo(b) == -1; }
	public static boolean lessThanRational(BigRational a, BigRational b) { return a.compareTo(b) == -1; }

	public static Byte asInt8(Number x) { return x.byteValue(); }
	public static Short asInt16(Number x) { return x.shortValue(); }
	public static Integer asInt32(Number x) { return x.intValue(); }
	public static Long asInt64(Number x) { return x.longValue(); }
	public static Float asFloat32(Number x) { return x.floatValue(); }
	public static Double asFloat64(Number x) { return x.doubleValue(); }

	// XXX: these conversions are lossy just now - more logic required for lossless conversion
	public static BigInteger asInteger(Number x) { return BigInteger.valueOf(x.longValue()); }
	public static BigDecimal asDecimal(Number x) { return BigDecimal.valueOf(x.doubleValue()); }
	public static BigRational asBigRational(Number x) { return BigRational.valueOf(x.doubleValue()); }

	public static Integer checkedNarrowToInt32(BigInteger x) {
		if(   (x.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) == 1) 
		   || (x.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) == -1)) {
		   	throw new IllegalArgumentException("Attempt to convert a BigInteger to Int32 when outside representable range");
		}

		return x.intValue();
	}

	public static Integer characterToInt32(Character c) {
		return (int)(c.charValue());
	}
}