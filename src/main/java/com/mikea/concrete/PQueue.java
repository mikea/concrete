package com.mikea.concrete;

public interface PQueue<T, Self extends PQueue> extends PCollection<T, Self> {
  Self pushFront(T value);
  Self popFront();
  T peekFront();
}
