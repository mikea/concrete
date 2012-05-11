package com.mikea.concrete;

public interface CQueue<T, Self extends CQueue> extends CCollection<T, Self> {
  Self push(T value);
  Self pop();

  T peek();
}
