package com.mikea.concrete;

public interface PStack<T, Self extends PStack<T, Self>> extends PCollection<T, Self> {
  Self pushFront(T value);
  T peekFront();
  Self popFront();
}
