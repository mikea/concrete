package com.mikea.concrete;

import com.mikea.concrete.impl.AmortizedDeque;

public interface PDeque<T> extends PQueue<T>, PStack<T> {
  PDeque<T> pushFront(T value);

  PDeque<T> popBack();

  T peekBack();

  @Override
  PDeque<T> pushBack(T value);

  @Override
  PDeque<T> popFront();

  @Override
  PDeque<T> clear();

  static <T> PDeque<T> newAmortizedDeque() {
    return AmortizedDeque.newAmortizedDeque();
  }

  default PDeque<T> pushBackAll(T... args) {
    PDeque<T> deque = this;
    for (T t : args) {
      deque = deque.pushBack(t);
    }
    return deque;
  }
}
