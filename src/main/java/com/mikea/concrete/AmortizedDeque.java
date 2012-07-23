package com.mikea.concrete;

import java.util.NoSuchElementException;

/**
 *
 */
public class AmortizedDeque<T> extends AmortizedQueueImpl<T, AmortizedDeque<T>> implements PDeque<T, AmortizedDeque<T>> {
  private static final int C = 2;

  private AmortizedDeque(Stack<T> head, Stack<T> tail) {
    super(head, tail);
  }

  private AmortizedDeque() {
  }

  @Override
  protected AmortizedDeque<T> newSelf(Stack<T> head, Stack<T> tail) {
    return new AmortizedDeque<T>(head, tail);
  }

  @Override
  public AmortizedDeque<T> pushFront(T value) {
    return newSelf(head.pushFront(value), tail);
  }

  @Override
  public AmortizedDeque<T> popBack() {
    if (!tail.isEmpty()) {
      return newSelf(head, tail.popFront());
    } else if (head.isEmpty()) {
      throw new NoSuchElementException();
    } else {
      return newSelf(Stacks.popBack(head), tail);
    }
  }

  @Override
  public T peekBack() {
    if (tail.isEmpty()) {
      return Stacks.peekBack(head);
    } else {
      return tail.peekFront();
    }
  }

  public static <T> AmortizedDeque<T> newAmortizedDeque() {
    return new AmortizedDeque<T>();
  }


}
