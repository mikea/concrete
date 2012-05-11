package com.mikea.concrete;

import java.util.NoSuchElementException;

public class AmortizedQueue<T> implements CQueue<T, AmortizedQueue<T>> {
  private final Stack<T> front;
  private final Stack<T> rear;

  private AmortizedQueue(Stack<T> front, Stack<T> rear) {
    if (front.isEmpty() && !rear.isEmpty()) {
      this.front = rear.reverse();
      this.rear = new Stack<T>();
    } else {
      this.front = front;
      this.rear = rear;
    }
  }

  public AmortizedQueue() {
    this(new Stack<T>(), new Stack<T>());
  }

  @Override
  public boolean isEmpty() {
    return front.isEmpty();
  }

  /**
   * Push element to the back of the queue. Worst-case O(N), amortized O(1).
   */
  @Override
  public AmortizedQueue<T> push(T t) {
    return new AmortizedQueue<T>(front, rear.push(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  @Override
  public AmortizedQueue<T> pop() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return new AmortizedQueue<T>(front.pop(), rear);
  }

  /**
   * Peek at the head element. Null if empty. O(1).
   */
  @Override
  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return front.peek();
  }
}
