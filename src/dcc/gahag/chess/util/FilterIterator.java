package dcc.gahag.chess.util;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


/**
 * An iterator to filter the values of other iterator.
 * Event if the specified iterator supports removing, the FilterIterator doesn't.
 */
public class FilterIterator<T> implements Iterator<T> {
  protected final Iterator<? extends T> it;
  protected final Predicate<? super T> predicate;
  
  protected T _next;
  protected boolean _nextSet = false;
  
  /**
   * Construct a FilterIterator from an iterator and a predicate.
   * @param  it the iterator to be filtered, mustn't be null
   * @param  f  the predicate, possibly null
   */
  public FilterIterator(final Iterator<? extends T> it, final Predicate<? super T> p) {
    if (it == null)
      throw new IllegalArgumentException("Iterator mustn't be null");
    
    this.it = it;
    this.predicate = p;
  }
  
  
  public boolean hasNext() {
    return this._nextSet || this.setNext();
  }
  
  public T next() {
    if (!this._nextSet && !this.setNext())
      throw new NoSuchElementException();
    
    this._nextSet = false;
    return this._next;
  }
  
  
  protected boolean setNext() {
    while (this.it.hasNext()) {
      final T n = this.it.next();
      
      if (this.predicate != null && this.predicate.test(n)) {
        this._next = n;
        this._nextSet = true;
        return true;
      }
    }
    
    return false;
  }
}
