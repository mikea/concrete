package com.mikea.concrete;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;

import java.util.Iterator;

import static com.mikea.concrete.Stack.newStack;

public class Stacks {
  /**
   * Reverses the stack. O(N).
   */
  public static <T> PStack<T> reverse(PStack<T> stack) {
    Stack<T> result = newStack();

    while (!stack.isEmpty()) {
      result = result.pushFront(stack.peekFront());
      stack = stack.popFront();
    }
    return result;
  }

  /**
   * Appends second to first. Back of second will become back of result. O(1).
   */
  public static <T> PStack<T> append(final PStack<T> first, final PStack<T> second) {
    if (first.isEmpty()) return second;
    return new AppendedStack<T>(first, second);
  }

  /**
   * Peeks the back of the stack. O(|N|).
   */
  public static <T> T peekBack(PStack<T> stack) {
    if (stack.isEmpty()) return null;

    PStack<T> stackMinusOne = stack.popFront();

    while (!stackMinusOne.isEmpty()) {
      stack = stackMinusOne;
      stackMinusOne = stackMinusOne.popFront();
    }

    return stack.peekFront();
  }

  /**
   * Pops one element from the back of the stack. O(|N|).
   */
  public static <T> PStack<T> popBack(PStack<T> stack) {
    PStack<T> rstack = reverse(stack);
    rstack = rstack.popFront();
    return reverse(rstack);
  }

  /**
   * Stack implementation that appends to other stacks together.
   *
   * Invariant: |left| > 0.
   */
  private static class AppendedStack<T> implements PStack<T> {
    private final PStack<T> left;
    private final PStack<T> right;

    public AppendedStack(PStack<T> left, PStack<T> right) {
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
}
