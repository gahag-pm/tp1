package dcc.gahag.chess.util;


import java.util.Iterator;
import java.util.function.Function;


/**
 * An iterator to transform the values of other iterator.
 */
public class TransformIterator<T, U> implements Iterator<U> {
  protected final Iterator<? extends T> it;
  protected final Function<? super T, ? extends U> f;
  
  
  /**
   * Construct a TransformIterator from an iterator and a transform function.
   * @param  it the iterator to be transformed, mustn't be null
   * @param  f  the transformer function, mustn't be null
   */
  public TransformIterator(Iterator<? extends T> it, Function<? super T, ? extends U> f) {
    if (it == null)
      throw new IllegalArgumentException("Iterator mustn't be null");

    if (f == null)
      throw new IllegalArgumentException("Function mustn't be null");
    
    this.it = it;
    this.f = f;
  }
  
  
  public boolean hasNext() {
    return this.it.hasNext();
  }
  
  public U next() {
    return this.f.apply(this.it.next());
  }
  
  public void remove() {
    this.it.remove();
  }
}
