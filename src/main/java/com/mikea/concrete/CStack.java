package com.mikea.concrete;

public interface CStack<T, Self extends CStack> extends CCollection<T, Self> {
  Self push(T value);
  T peek();
  Self pop();
  Self reverse();
}
