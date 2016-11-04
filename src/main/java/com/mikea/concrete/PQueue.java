package com.mikea.concrete;

public interface PQueue<T> extends PCollection<T> {
  PQueue<T> pushBack(T value);
  PQueue<T> popFront();
  T peekFront();

  @Override
  PQueue<T> clear();

  default PQueue<T> pushBackAll(T...args) {
    PQueue<T> queue = this;
    for (T t : args) {
      queue = queue.pushBack(t);
    }
    return queue;
  }
}
