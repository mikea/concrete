package com.mikea.concrete;

import java.util.NoSuchElementException;

import static com.mikea.concrete.Stack.newStack;
import static com.mikea.concrete.Stacks.reverse;

/**
 *
 */
public class AmortizedQueue2<T> implements PQueue<T, AmortizedQueue2<T>> {
  final Stack<T> head;
  final Stack<T> tail;

  protected AmortizedQueue2(Stack<T> head, Stack<T> tail) {
    if (!head.isEmpty()) {
      this.head = head;
      this.tail = tail;
    } else {
      this.head = reverse(tail);
      this.tail = newStack();
    }
  }

  protected AmortizedQueue2() {
    this(Stack.<T>newStack(), Stack.<T>newStack());
  }

  @Override
  public boolean isEmpty() {
    return head.isEmpty();
  }

  @Override
  public int size() {
    return head.size() + tail.size();
  }

  /**
   * Push element to the back of the queue. Worst-case O(N), amortized O(1).
   */
  @Override
  public AmortizedQueue2<T> pushBack(T t) {
    return newSelf(head, tail.pushFront(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  @Override
  public AmortizedQueue2<T> popFront() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return newSelf(head.popFront(), tail);
  }

  protected AmortizedQueue2<T> newSelf(Stack<T> head, Stack<T> tail) {
    return new AmortizedQueue2<T>(head, tail);
  }

  /**
   * Peek at the head element. Null if empty. O(1).
   */
  @Override
  public T peekFront() {
    if (isEmpty()) {
      return null;
    }
    return head.peekFront();
  }

  public static <T> AmortizedQueue2<T> newAmortizedQueue2() {
    return new AmortizedQueue2<T>();
  }

}
