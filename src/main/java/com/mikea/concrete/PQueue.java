package com.mikea.concrete;

public interface PQueue<T, Self extends PQueue> extends PCollection<T, Self> {
  Self push(T value);
  Self pop();

  T peek();
}
