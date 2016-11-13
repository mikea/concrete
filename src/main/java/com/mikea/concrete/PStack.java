package com.mikea.concrete;

import com.mikea.concrete.impl.AppendedStack;
import com.mikea.concrete.impl.Stack;

import java.util.Arrays;
import java.util.List;

public interface PStack<T> extends PCollection<T>, Iterable<T> {
  PStack<T> pushFront(T value);
  T peekFront();
  PStack<T> popFront();
  PStack<T> clear();

  static <T> PStack<T> newStack() { return Stack.newStack(); }

  @SafeVarargs
  static <T> PStack<T> newStack(T...items) {
    List<T> itemsList = Arrays.asList(items);
    return Stack.<T>newStack().pushAll(itemsList); }

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

  default PStack<T> pushAll(Iterable<T> i) {
    PStack<T> stack = this;
    for (T t : i) {
      stack = stack.pushFront(t);
    }

    return stack.reverse();
  }

  /**
   * Peeks the back of the stack. O(|N|).
   */
  default T peekBack_slow() {
    PStack<T> stack = this;
    while (!stack.isEmpty()) {
      PStack<T> withoutFront = stack.popFront();
      if (withoutFront.isEmpty()) {
        return stack.peekFront();
      }
      stack = withoutFront;
    }

    return peekFront();
  }

  /**
   * Pops one element from the back of the stack. O(|N|).
   */
  default PStack<T> popBack_slow() {
    return reverse().popFront().reverse();
  }
}
