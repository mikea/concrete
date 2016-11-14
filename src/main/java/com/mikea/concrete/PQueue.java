package com.mikea.concrete;

import com.mikea.concrete.impl.AmortizedQueue;
import com.mikea.concrete.impl.RealtimeQueue;

import java.util.NoSuchElementException;

public interface PQueue<T> extends PCollection<T>, PIterable<T> {
  @Override
  PQueue<T> clear();

  @Override
  PQueue<T> popFront() throws NoSuchElementException;

  PQueue<T> pushBack(T value);


  static <T> PQueue<T> newAmortizedQueue() {
    return AmortizedQueue.newAmortizedQueue();
  }

  static <T> PQueue<T> newRealtimeQueue() {
    return RealtimeQueue.newRealtimeQueue();
  }

  @SuppressWarnings("unchecked")
  default PQueue<T> pushBackAll(T... args) {
    PQueue<T> queue = this;
    for (T t : args) {
      queue = queue.pushBack(t);
    }
    return queue;
  }
}
