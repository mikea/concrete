package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListQueue<T> implements PQueue<T> {
  private final ArrayList<T> data;

  private ArrayListQueue(ArrayList<T> data) {
    this.data = data;
  }

  ArrayListQueue() {
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
  public PQueue<T> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<T> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListQueue<>(newData);
  }

  @Override
  public PQueue<T> pushBack(T value) {
    ArrayList<T> newData = new ArrayList<>(data);
    newData.add(value);
    return new ArrayListQueue<>(newData);
  }

  @Override
  public PQueue<T> clear() {
    return new ArrayListQueue<>();
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
}
