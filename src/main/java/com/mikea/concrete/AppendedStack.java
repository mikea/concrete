package com.mikea.concrete;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;

import java.util.Iterator;

/**
 * Stack implementation that appends to other stacks together.
 *
 * Invariant: |left| > 0.
 */
class AppendedStack<T> implements PStack<T> {
  private final PStack<T> left;
  private final PStack<T> right;

  AppendedStack(PStack<T> left, PStack<T> right) {
    Preconditions.checkArgument(!left.isEmpty());
    this.left = left;
    this.right = right;
  }

  @Override
  public PStack<T> pushFront(T value) {
    return new AppendedStack<T>(left.pushFront(value), right);
  }

  @Override
  public T peekFront() {
    return left.peekFront();
  }

  @Override
  public PStack<T> popFront() {
    PStack<T> newLeft = left.popFront();
    if (!newLeft.isEmpty()) {
      return new AppendedStack<T>(newLeft, right);
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
  public PCollection<T> clear() {
    return left.clear();
  }
}
