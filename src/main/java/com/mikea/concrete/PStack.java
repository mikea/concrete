package com.mikea.concrete;

public interface PStack<T, Self extends PStack> extends PCollection<T, Self> {
  Self push(T value);
  T peek();
  Self pop();
  Self reverse();
}
