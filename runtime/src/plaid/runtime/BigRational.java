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

/**
 * Arbitrary precision representation of rational real numbers, using two
 * Java BigIntegers to represent the numerator and denominator. This code
 * is largely based on the BigRational type by Robert Sedgewick and
 * Kevin Wayne.
 */
public class BigRational implements Comparable<BigRational> {

    /**
     * INVARIANTS:
     * 1. gcd(num, den) == 1
     * 2. den >= 1
     */
    private final BigInteger num;
    private final BigInteger den;

    public BigRational(BigInteger numerator, BigInteger denominator) {
        if(denominator.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero!");
        }

        BigInteger g = numerator.gcd(denominator);
        BigInteger tempNum = numerator.divide(g);
        BigInteger tempDen = denominator.divide(g);
        
        if(tempDen.compareTo(BigInteger.ZERO) == -1) {
            if(tempNum.compareTo(BigInteger.ZERO) == -1) {
                num = tempNum.abs();
                den = tempDen.abs();
                return;
            }
        }

        num = tempNum;
        den = tempDen;
    }

    public static BigRational valueOf(double d) {
        if(Double.isInfinite(d) || Double.isNaN(d)) {
            // conversion is impossible
            throw new IllegalArgumentException("Cannot convert +/- infinity or NaN into a BigRational");
        }

        long bits = Double.doubleToLongBits(3.25);

        long rawExponent      = (bits & 0x7ff0000000000000L) >> 52;
        boolean isExponentNegative = (bits & 0x400L) != 0;
        int unsignedExponent = (int)(rawExponent & 0x3ffL);
        int signedExponent = unsignedExponent * (isExponentNegative ? -1 : 1);

        boolean isMantissaNegative = (bits & 0x8000000000000000L) != 0;
        long signedMantissa      =  (bits & 0x000fffffffffffffL) * (isMantissaNegative ? -1 : 1);

        BigInteger bigMantissa = BigInteger.valueOf(signedMantissa);
        BigInteger bigExponent = BigInteger.ONE.shiftLeft(unsignedExponent);

        if(isExponentNegative) {
            // number is x * 2^-y, equivalent to x / 2^y
            return new BigRational(bigMantissa, bigExponent);
        } else {
            // number is x * 2^y, so denominator is 1
            return new BigRational(bigMantissa.multiply(bigExponent), BigInteger.ONE);
        }
    }

    public static BigRational valueOf(long l) {
        return new BigRational(BigInteger.valueOf(l), BigInteger.ONE);
    }

    public BigRational add(BigRational b) {
        BigInteger numerator   = this.num.multiply(b.den).add(this.den.multiply(b.num));
        BigInteger denominator = this.den.multiply(b.den);
        return new BigRational(numerator, denominator);
    }

    public BigRational subtract(BigRational b) {
        BigInteger numerator   = this.num.multiply(b.den).subtract(this.den.multiply(b.num));
        BigInteger denominator = this.den.multiply(b.den);
        return new BigRational(numerator, denominator);
    }

    public BigRational multiply(BigRational b) {
        BigInteger numerator   = this.num.multiply(b.num);
        BigInteger denominator = this.den.multiply(b.den);
        return new BigRational(numerator, denominator);
    }

    public BigRational reciprocal() { return new BigRational(den, num);  }

    public BigRational divide(BigRational b) {
        return this.multiply(b.reciprocal());
    }

    public BigRational mod(BigRational b) {
        return this.divide(b).fractionalPart();
    }

    public BigRational fractionalPart() {
        return new BigRational(num.mod(den), den);
    }

    public double doubleValue() {
        double numDouble = num.doubleValue();
        double denDouble = den.doubleValue();

        if(numDouble == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if(numDouble == Double.NEGATIVE_INFINITY) {
            return Double.NEGATIVE_INFINITY;
        } else if(denDouble == Double.POSITIVE_INFINITY) {
            return 0.0;
        } else {
            return numDouble / denDouble;
        }
    }

    public float floatValue() {
        return (float)doubleValue();
    }

    public String toString() { 
        if (den.equals(BigInteger.ONE)) return num.toString();
        else                            return num + "/" + den;
    }

    public boolean equals(Object other) {
        if(other instanceof BigRational) {
            BigRational otherRat = (BigRational) other;
            return this.num.equals(otherRat.num) 
                && this.den.equals(otherRat.den);
        }

        return false;
    }

    public int compareTo(BigRational other) {
        BigInteger unifiedDenominator = lcm(this.den, other.den);
        BigInteger thisNumerator = this.num.multiply(this.den.divide(unifiedDenominator));
        BigInteger otherNumerator = other.num.multiply(other.den.divide(unifiedDenominator));

        return thisNumerator.compareTo(otherNumerator);
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        // lcm(a,b) = (|a| / gcd(a,b)) * |b|
        return a.divide(a.gcd(b)).multiply(b).abs();
    }
}