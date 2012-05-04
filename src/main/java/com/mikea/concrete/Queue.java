package com.mikea.concrete;

import java.util.NoSuchElementException;

public class Queue<T> {
  private final Stack<T> front;
  private final Stack<T> rear;

  private Queue(Stack<T> front, Stack<T> rear) {
    if (front.isEmpty() && !rear.isEmpty()) {
      this.front = rear.reverse();
      this.rear = new Stack<T>();
    } else {
      this.front = front;
      this.rear = rear;
    }
  }

  public Queue() {
    this(new Stack<T>(), new Stack<T>());
  }

  public boolean isEmpty() {
    return front.isEmpty();
  }

  /**
   * Push element to the back of the queue. Worst-case O(N), amortized O(1).
   */
  public Queue<T> push(T t) {
    return new Queue<T>(front, rear.push(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  public Queue<T> pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return new Queue<T>(front.pop(), rear);
  }

  /**
   * Peek at the head element. O(1).
   */
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return front.peek();
  }
}
