package com.mikea.concrete.impl;

import com.google.common.collect.Iterables;
import com.mikea.concrete.PStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack storage. Consumes O(N) space.
 */
public class Stack<T> implements PStack<T>, Iterable<T> {
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
  private Stack() {
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

  @Override
  public PStack<T> clear() {
    return newStack();
  }

  /**
   * Pushes new value on the top of the stack. O(1).
   */
  @Override
  public Stack<T> pushFront(T value) {
    return new Stack<>(value, this, size + 1);
  }

  /**
   * Gets value from the top of the stack. O(1).
   */
  @Override
  public T peekFront() {
    if (isEmpty()) {
      return null;
    }
    return value;
  }

  /**
   * Pops(removes) value from the top of the stack. O(1).
   */
  @Override
  public Stack<T> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty stack");
    }
    return next;
  }

  @Override
  public String toString() {
    return Iterables.toString(this);
  }

  public static <T> PStack<T> newStack() {
    return new Stack<>();
  }
}
