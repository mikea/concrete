package com.mikea.concrete.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.mikea.concrete.PStack;

import java.util.Iterator;

/**
 * Stack implementation that appends to other stacks together.
 * <p>
 * Invariant: |left| > 0.
 */
public class AppendedStack<T> implements PStack<T> {
  private final PStack<T> left;
  private final PStack<T> right;

  private AppendedStack(PStack<T> left, PStack<T> right) {
    assert !left.isEmpty();
    this.left = left;
    this.right = right;
  }

  public static <T> PStack<T> append(PStack<T> left, PStack<T> right) {
    if (left.isEmpty()) return right;
    if (right.isEmpty()) return left;
    return new AppendedStack<>(left, right);
  }

  @Override
  public PStack<T> pushFront(T value) {
    return new AppendedStack<>(left.pushFront(value), right);
  }

  @Override
  public T peekFront() {
    return left.peekFront();
  }

  @Override
  public PStack<T> popFront() {
    PStack<T> newLeft = left.popFront();
    if (!newLeft.isEmpty()) {
      return new AppendedStack<>(newLeft, right);
    } else {
      return right;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return Iterators.concat(left.iterator(), right.iterator());
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int size() {
    return left.size() + right.size();
  }

  @Override
  public PStack<T> clear() {
    return left.clear();
  }

  @Override
  public String toString() {
    return Iterables.toString(this);
  }
}
