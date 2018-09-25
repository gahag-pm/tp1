package dcc.gahag.chess.util;


/**
 * A mutable boxed reference type.
 * This type allows mutation of values passed by parameter.
 */
public class Box<T> {
  public T value;
  

  /**
   * Construct a box, with a null value.
   */
  public Box() {
    this.value = null;
  }

  /**
   * Construct a box with the given value.
   */
  public Box(T value) {
    this.value = value;
  }
}
