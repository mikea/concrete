package com.mikea.concrete;

import com.mikea.concrete.impl.AmortizedQueue;
import com.mikea.concrete.impl.RealtimeQueue;

public interface PQueue<T> extends PCollection<T>, PIterable<T> {
  @Override
  PQueue<T> clear();

  @Override
  PQueue<T> popFront();

  PQueue<T> pushBack(T value);


  static PQueue<String> newAmortizedQueue() {
    return AmortizedQueue.newAmortizedQueue();
  }

  static PQueue<String> newRealtimeQueue() {
    return RealtimeQueue.newRealtimeQueue();
  }

  default PQueue<T> pushBackAll(T... args) {
    PQueue<T> queue = this;
    for (T t : args) {
      queue = queue.pushBack(t);
    }
    return queue;
  }
}
