package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListArray<T> implements PArray<T> {
  private final ArrayList<T> data;

  private ArrayListArray(ArrayList<T> data) {
    this.data = data;
  }

  ArrayListArray() {
    this(new ArrayList<>());
  }

  @Override
  public T peekFront() {
    if (isEmpty()) {
      return null;
    }
    return data.get(0);
  }

  @Override
  public PArray<T> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<T> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListArray<>(newData);
  }

  @Override
  public PArray<T> pushFront(T value) {
    ArrayList<T> newData = new ArrayList<>(data);
    newData.add(0, value);
    return new ArrayListArray<>(newData);
  }

  @Override
  public PArray<T> clear() {
    return new ArrayListArray<>();
  }

  @Override
  public boolean isEmpty() {
    return data.isEmpty();
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public String toString() {
    return data.toString();
  }

  @Override
  public T get(int index) {
    if (index >= size() || index < 0) {
      return null;
    }
    return data.get(index);
  }

  @Override
  public PArray<T> set(int index, T s) {
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    ArrayList<T> newData = new ArrayList<>(data);
    newData.set(index, s);
    return new ArrayListArray<>(newData);
  }
}
