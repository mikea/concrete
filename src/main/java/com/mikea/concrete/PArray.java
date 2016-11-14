package com.mikea.concrete;

import com.mikea.concrete.impl.BinaryArray;

public interface PArray<T> extends PStack<T> {
  @Override
  PArray<T> clear();

  @Override
  PArray<T> pushFront(T t);
  @Override
  PArray<T> popFront();

  T get(int index);
  PArray<T> set(int index, T t);

  static PArray<String> newBinaryArray() {
    return BinaryArray.newBinaryArray();
  }
}
