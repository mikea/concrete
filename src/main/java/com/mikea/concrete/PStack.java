package com.mikea.concrete;

import com.mikea.concrete.impl.AppendedStack;
import com.mikea.concrete.impl.Stack;

public interface PStack<T> extends PCollection<T>, PIterable<T> {
  T peekFront();

  PStack<T> popFront();

  PStack<T> pushFront(T value);

  PStack<T> clear();

  static <T> PStack<T> newStack() {
    return Stack.newStack();
  }

  @SafeVarargs
  static <T> PStack<T> newStack(T... items) {
    PStack<T> stack = newStack();
    return stack.pushFrontAll(items);
  }

  @SuppressWarnings("unchecked")
  default PStack<T> pushFrontAll(T... items) {
    PStack<T> stack = this;
    for (int i = items.length - 1; i >= 0; i--) {
      T item = items[i];
      stack = stack.pushFront(item);
    }
    return stack;
  }


  /**
   * Appends second to first. Back of second will become back of result. O(1).
   */
  default PStack<T> append(final PStack<T> second) {
    return AppendedStack.append(this, second);
  }

  /**
   * Reverses the stack. O(N).
   */
  default PStack<T> reverse() {
    PStack<T> result = newStack();

    PStack<T> stack = this;
    while (!stack.isEmpty()) {
      result = result.pushFront(stack.peekFront());
      stack = stack.popFront();
    }
    return result;
  }
}
