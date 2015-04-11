Plaid now has a base abstract numeric type like Java, called Number. This has the following cases:

  * Int8, equivalent to java.lang.Byte
  * Int16, equivalent to a java.lang.Short
  * Int32, equivalent to a java.lang.Integer
  * Int64, equivalent to a java.lang.Long
  * Float32, equivalent to a java.lang.Float
  * Float64, equivalent to a java.lang.Double
  * Integer, equivalent to a java.math.BigInteger
  * Decimal, equivalent to a java.math.BigDecimal
  * Rational, equivalent to a new type plaid.lang.BigRational, which is a BigInteger numerator divided by a BigInteger denominator.

By default, all integer literals (like 123456789) are of type Integer, and all float literals (like 123.456789) are of type Rational. All numeric types have conversion methods of form asX() where X is the name of the target conversion type. Truncation or loss of precision will occur when converting to a smaller type. So, to get an Int32 from a literal, type `(12345).asInt32()`.

All numeric types can also be ordered relative to other numbers of any numeric type. This is performed by converting both operands of the comparison to a compatible type in a similar fashion to the way Java converts values to a more "precise" type. For the types above, this conversion lattice is

```
Int8 -> Int16 -> Int32 -> Int64 -> Integer \
                                            -> Decimal -> Rational                
Float32 -> Float64 ------------------------/
```

So, by comparing an Int32 to a Float32, both are first converted to a Decimal (which should be a lossless conversion) and then directly compared using the nativeEquals and nativeLessThan operations on Decimal. These conversions are actually implemented directly in Number.

Arithmetic operations such as +, -, **, / etc. are defined on each specific Number types as taking an argument which is of the same type, and return a result of the same type. So, if one wished to add 2.5 (a Decimal) to 3 (an Integer), one would have to convert either (or both) to a compatible type, e.g.**

```
val intResult = (2.5).asInt32() + 3
val decResult = 2.5 + ((3).asDecimal())
```

The one awkward special case here is for division on Decimal, which has a return type of Rational as Decimal only allows division by a divisor that is a multiple of 2 or 5. As such, Rational is the more general and reliable return type.

## Interop with Java ##

When a parameter of a Java method is of an integer type (byte, short, int, long, and boxed versions) then any Plaid integer of smaller size may be passed in a similar way to automatic widening in Java. As a special case, an Integer may be passed as a argument for any parameter which is of an integer type - the runtime will attempt to "narrow" the value into the appropriate type and if the number cannot fit in the legal range for that type, a runtime exception will be thrown.

When a method returns a value, the value is converted into the corresponding Plaid type. So, if a method returns a short, this will be converted into an Int16 in the Plaid view of the world. This does mean that explicit conversion will be likely be required if that result is to be used as part of an arithmetic expression.