package com.mikea.concrete;

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
   * Pushes all to target's back. Back of all will become back of result. O(|target|).
   */
  public static <T> PStack<T> pushAllToBack(PStack<T> target, PStack<T> all) {
    PStack<T> rtarget = reverse(target);
    return pushAllToFront(all, rtarget);
  }

  /**
   * Pushes all to target's front. Back of all will become front of result. O(|all|).
   */
  public static <T> PStack<T> pushAllToFront(PStack<T> target, PStack<T> all) {
    while (!all.isEmpty()) {
      target = target.pushFront(all.peekFront());
      all = all.popFront();
    }

    return target;
  }

  /**
   * Peeks the back of the stack. O(|stack|).
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

  public static <T> PStack<T> popBack(PStack<T> stack) {
    PStack<T> rstack = reverse(stack);
    rstack = rstack.popFront();
    return reverse(rstack);
  }
}
