package com.mikea.concrete;

import java.util.NoSuchElementException;

public class AmortizedQueue<T> implements CQueue<T, AmortizedQueue<T>> {
  private final Stack<T> head;
  private final Stack<T> tail;

  private AmortizedQueue(Stack<T> head, Stack<T> tail) {
    if (head.isEmpty() && !tail.isEmpty()) {
      this.head = tail.reverse();
      this.tail = new Stack<T>();
    } else {
      this.head = head;
      this.tail = tail;
    }
  }

  public AmortizedQueue() {
    this(new Stack<T>(), new Stack<T>());
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
  public AmortizedQueue<T> push(T t) {
    return new AmortizedQueue<T>(head, tail.push(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  @Override
  public AmortizedQueue<T> pop() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return new AmortizedQueue<T>(head.pop(), tail);
  }

  /**
   * Peek at the head element. Null if empty. O(1).
   */
  @Override
  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return head.peek();
  }
}
