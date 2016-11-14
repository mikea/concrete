package com.mikea.concrete;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface PIterable<T> extends Iterable<T> {
  boolean isEmpty();
  T peekFront();
  PIterable<T> popFront() throws NoSuchElementException;

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
}
