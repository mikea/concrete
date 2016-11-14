package com.mikea.concrete;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface PIterable<T> extends Iterable<T> {
  boolean isEmpty();

  T peekFront();

  PIterable<T> popFront();

  @Override
  default Iterator<T> iterator() {
    return new Iterator<T>() {
      PIterable<T> ptr = PIterable.this;

      @Override
      public boolean hasNext() {
        return !ptr.isEmpty();
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T value = ptr.peekFront();
        ptr = ptr.popFront();
        return value;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("remove is not implemented.");
      }
    };
  }

  /**
   * Peeks the back of the stack. O(|N|).
   */
  default T peekBack() {
    PIterable<T> ptr = this;
    while (!ptr.isEmpty()) {
      PIterable<T> next = ptr.popFront();
      if (next.isEmpty()) {
        return ptr.peekFront();
      }
      ptr = next;
    }

    return peekFront();
  }
}
