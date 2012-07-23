package com.mikea.concrete;

import static com.mikea.concrete.Stack.newStack;

public class Stacks {
  /**
   * Reverses the stack. O(N).
   */
  public static <T, S extends PStack<T, S>> Stack<T> reverse(PStack<T, S> stack) {
    Stack<T> result = newStack();

    while (!stack.isEmpty()) {
      result = result.pushFront(stack.peekFront());
      stack = stack.popFront();
    }
    return result;
  }

  /**
   * Pushes all to target's back. Back of all will become back of result. O(|target|).
   */
  public static <T> Stack<T> pushAllToBack(Stack<T> target, Stack<T> all) {
    Stack<T> rtarget = reverse(target);
    return pushAllToFront(all, rtarget);
  }

  /**
   * Pushes all to target's front. Back of all will become front of result. O(|all|).
   */
  public static <T> Stack<T> pushAllToFront(Stack<T> target, Stack<T> all) {
    while (!all.isEmpty()) {
      target = target.pushFront(all.peekFront());
      all = all.popFront();
    }

    return target;
  }

  /**
   * Peeks the back of the stack. O(|stack|).
   */
  public static <T> T peekBack(Stack<T> stack) {
    if (stack.isEmpty()) return null;

    Stack<T> stackMinusOne = stack.popFront();

    while (!stackMinusOne.isEmpty()) {
      stack = stackMinusOne;
      stackMinusOne = stackMinusOne.popFront();
    }

    return stack.peekFront();
  }

  public static <T> Stack<T> popBack(Stack<T> stack) {
    Stack<T> rstack = reverse(stack);
    rstack = rstack.popFront();
    return reverse(rstack);
  }
}
