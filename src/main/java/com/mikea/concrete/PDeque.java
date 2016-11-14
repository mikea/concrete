package com.mikea.concrete;

import com.mikea.concrete.impl.AmortizedDeque;

import java.util.NoSuchElementException;

public interface PDeque<T> extends PQueue<T>, PStack<T> {
  PDeque<T> pushFront(T value);

  PDeque<T> popBack() throws NoSuchElementException;

  T peekBack();

  @Override
  PDeque<T> pushBack(T value);

  @Override
  PDeque<T> popFront() throws NoSuchElementException;

  @Override
  PDeque<T> clear();

  static <T> PDeque<T> newAmortizedDeque() {
    return AmortizedDeque.newAmortizedDeque();
  }

  @SuppressWarnings("unchecked")
  default PDeque<T> pushBackAll(T... args) {
    PDeque<T> deque = this;
    for (T t : args) {
      deque = deque.pushBack(t);
    }
    return deque;
  }
}
