package com.mikea.concrete;

public interface PQueue<T, Self extends PQueue> extends PCollection<T, Self> {
  Self pushBack(T value);
  Self popFront();
  T peekFront();
}
