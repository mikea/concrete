package com.mikea.concrete;

import java.util.NoSuchElementException;

import static com.mikea.concrete.Stack.newStack;
import static com.mikea.concrete.Stacks.reverse;

/**
 * Invariants: |head| >= |tail|
 */
abstract class AmortizedQueueImpl<T, Self extends AmortizedQueueImpl<T, ?>> implements PQueue<T, Self> {
  final Stack<T> head;
  final Stack<T> tail;

  protected AmortizedQueueImpl(Stack<T> head, Stack<T> tail) {
    if (head.size() >= tail.size()) {
      this.head = head;
      this.tail = tail;
    } else {
      this.head = Stacks.pushAllToBack(head, reverse(tail));
      this.tail = newStack();
    }
  }

  protected AmortizedQueueImpl() {
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
  public Self pushBack(T t) {
    return newSelf(head, tail.pushFront(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  @Override
  public Self popFront() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return newSelf(head.popFront(), tail);
  }

  protected abstract Self newSelf(Stack<T> head, Stack<T> tail);

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
