package plaid.parser.ast.util;


/**
 * An optional value that may be none (no value) or some (a value). This type is a replacement for
 * the use of <code>null</code> with better type checks.
 */
public abstract class Option<A>{
  private Option() {

  }

  /**
   * Returns the value from this optional value, or fails if there is no value.
   *
   * @return The value from this optional value, or fails if there is no value.
   */
  public abstract A value();
  
  /**
   * Constructs an optional value that has a value of the given argument.
   *
   * @param t The value for the returned optional value.
   * @return An optional value that has a value of the given argument.
   */
  public static <T> Option<T> some(final T t) {
    return new Some<T>(t);
  }

  /**
   * Constructs an optional value that has no value.
   *
   * @return An optional value that has no value.
   */
  public static <T> Option<T> none() {
    return new None<T>();
  }
  
  /**
   * Turns an unsafe nullable value into a safe optional value. If <code>t == null</code> then
   * return none, otherwise, return the given value in some.
   *
   * @param t The unsafe nullable value.
   * @return If <code>t == null</code> then return it in some, otherwise, return none.
   */
  public static <T> Option<T> fromNull(final T t) {
    return t == null ? Option.<T>none() : some(t);
  }

  /**
   * Returns <code>true</code> if this optional value has a value, <code>false</code> otherwise.
   *
   * @return <code>true</code> if this optional value has a value, <code>false</code> otherwise.
   */
  public final boolean isSome() {
    return this instanceof Some;
  }

  /**
   * Returns <code>false</code> if this optional value has a value, <code>true</code> otherwise.
   *
   * @return <code>false</code> if this optional value has a value, <code>true</code> otherwise.
   */
  public final boolean isNone() {
    return this instanceof None;
  }

  private static final class None<A> extends Option<A> {
    public A value() {
      throw new RuntimeException("some on None");
    }
  }

  private static final class Some<A> extends Option<A> {
    private final A a;

    Some(final A a) {
      this.a = a;
    }

    public A value() {
      return a;
    }
  }
}
