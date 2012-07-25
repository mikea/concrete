package com.mikea.concrete;

public interface PStack<T> extends PCollection<T>, Iterable<T> {
  PStack<T> pushFront(T value);
  T peekFront();
  PStack<T> popFront();
}
