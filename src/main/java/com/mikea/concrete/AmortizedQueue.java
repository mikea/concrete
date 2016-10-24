package com.mikea.concrete;

import java.util.NoSuchElementException;

import static com.mikea.concrete.Stack.newStack;

public class AmortizedQueue<T> implements PQueue<T> {
  private final PStack<T> head;
  private final PStack<T> tail;

  private AmortizedQueue() {
    this(PStack.newStack(), PStack.newStack());
  }

  private AmortizedQueue(PStack<T> head, PStack<T> tail) {
    if (!head.isEmpty()) {
      this.head = head;
      this.tail = tail;
    } else {
      this.head = tail.reverse();
      this.tail = newStack();
    }
  }

  public static <T> AmortizedQueue<T> newAmortizedQueue() {
    return new AmortizedQueue<>();
  }

  @Override
  public boolean isEmpty() {
    return head.isEmpty();
  }

  @Override
  public int size() {
    return head.size() + tail.size();
  }

  @Override
  public PQueue<T> clear() {
    return newAmortizedQueue();
  }

  /**
   * Push element to the back of the queue. Worst-case O(N), amortized O(1).
   */
  @Override
  public AmortizedQueue<T> pushBack(T t) {
    return new AmortizedQueue<>(head, tail.pushFront(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  @Override
  public AmortizedQueue<T> popFront() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return new AmortizedQueue<>(head.popFront(), tail);
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
}
