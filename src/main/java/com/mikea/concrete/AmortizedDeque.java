package com.mikea.concrete;

import com.google.common.collect.Iterables;

import java.util.NoSuchElementException;

import static com.mikea.concrete.PStack.newStack;

/**
 * Invariants:
 *   |head| <= 2 * |tail| + 1
 *   |tail| <= 2 * |head| + 1
 */
public class AmortizedDeque<T> implements PDeque<T> {
  private final PStack<T> head;
  private final PStack<T> tail;

  private AmortizedDeque(PStack<T> head, PStack<T> tail) {
    if (head.size() >= tail.size()) {
      this.head = head;
      this.tail = tail;
    } else {
      this.head = head.append(tail.reverse());
      this.tail = newStack();
    }
  }

  private AmortizedDeque() {
    this(Stack.newStack(), Stack.newStack());
  }

  @Override
  public AmortizedDeque<T> pushFront(T value) {
    return new AmortizedDeque<>(head.pushFront(value), tail);
  }

  @Override
  public AmortizedDeque<T> popBack() {
    if (!tail.isEmpty()) {
      return new AmortizedDeque<>(head, tail.popFront());
    } else if (head.isEmpty()) {
      throw new NoSuchElementException();
    } else {
      return new AmortizedDeque<>(head.popBack_slow(), tail);
    }
  }

  @Override
  public T peekBack() {
    if (tail.isEmpty()) {
      return head.peekBack_slow();
    } else {
      return tail.peekFront();
    }
  }

  public static <T> AmortizedDeque<T> newAmortizedDeque() {
    return new AmortizedDeque<>();
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
  public PDeque<T> clear() {
    return newAmortizedDeque();
  }

  /**
   * Push element to the back of the queue. Worst-case O(N), amortized O(1).
   */
  @Override
  public AmortizedDeque<T> pushBack(T t) {
    return new AmortizedDeque<>(head, tail.pushFront(t));
  }

  /**
   * Pop element from the head of the queue. O(1).
   */
  @Override
  public AmortizedDeque<T> popFront() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return new AmortizedDeque<>(head.popFront(), tail);
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
