package com.mikea.concrete;

import com.mikea.concrete.impl.BinaryArray;

import java.util.NoSuchElementException;

public interface PArray<T> extends PStack<T> {
  @Override
  PArray<T> clear();

  @Override
  PArray<T> pushFront(T t);
  @Override
  PArray<T> popFront() throws NoSuchElementException;

  T get(int index);

  PArray<T> set(int index, T t) throws IndexOutOfBoundsException;

  @SuppressWarnings("unchecked")
  default PArray<T> pushFrontAll(T... items) {
    PArray<T> array = this;
    for (int i = items.length - 1; i >= 0; i--) {
      T item = items[i];
      array = array.pushFront(item);
    }
    return array;
  }


  static <T> PArray<T> newBinaryArray() {
    return BinaryArray.newBinaryArray();
  }
}
