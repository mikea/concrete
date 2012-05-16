package com.mikea.concrete;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack storage. Consumes O(N) space.
 */
public class Stack<T> implements PStack<T, Stack<T>>, Iterable<T> {
  private final T value;
  private final Stack<T> next;
  private final int size;

  private Stack(T value, Stack<T> next, int size) {
    this.value = value;
    this.next = next;
    this.size = size;
  }

  /**
   * Creates an empty stack.
   */
  public Stack() {
    this(null, null, 0);
  }

  /**
   * Checks if the stack is empty. O(1).
   */
  @Override
  public boolean isEmpty() { return size == 0; }

  @Override
  public int size() {
    return size;
  }

  /**
   * Pushes new value on the top of the stack. O(1).
   */
  @Override
  public Stack<T> push(T value) {
    return new Stack<T>(value, this, size + 1);
  }

  /**
   * Gets value from the top of the stack. O(1).
   */
  @Override
  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return value;
  }

  /**
   * Pops(removes) value from the top of the stack. O(1).
   */
  @Override
  public Stack<T> pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty stack");
    }
    return next;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Stack<T> pointer = Stack.this;

      @Override
      public boolean hasNext() {
        return pointer.size != 0;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T value = pointer.value;
        pointer = pointer.next;
        return value;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("remove is not implemented in ");
      }
    };
  }

  /**
   * Reverses the stack. O(N).
   */
  @Override
  public Stack<T> reverse() {
    Stack<T> result = new Stack<T>();
    for (T t : this) {
      result = result.push(t);
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (T t : this) {
      if (result.length() != 0) {
        result.append(", ");
      }
      result.append(t.toString());
    }
    result.insert(0, "[");
    result.append("]");
    return result.toString();
  }
}
