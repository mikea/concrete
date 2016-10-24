package com.mikea.concrete;

public interface PStack<T> extends PCollection<T>, Iterable<T> {
  PStack<T> pushFront(T value);
  T peekFront();
  PStack<T> popFront();

  static <T> PStack<T> newStack() { return Stack.newStack(); }

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

  /**
   * Appends second to first. Back of second will become back of result. O(1).
   */
  default PStack<T> append(final PStack<T> second) {
    if (isEmpty()) return second;
    if (second.isEmpty()) return this;
    return new AppendedStack<>(this, second);
  }

  /**
   * Peeks the back of the stack. O(|N|).
   */
  default T peekBack() {
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
  default PStack<T> popBack() {
    return reverse().popFront().reverse();
  }
}
