package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListStack<T> implements PStack<T> {
  private final ArrayList<T> data;

  private ArrayListStack(ArrayList<T> data) {
    this.data = data;
  }

  ArrayListStack() {
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
  public PStack<T> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<T> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListStack<>(newData);
  }

  @Override
  public PStack<T> pushFront(T value) {
    ArrayList<T> newData = new ArrayList<>(data);
    newData.add(0, value);
    return new ArrayListStack<>(newData);
  }

  @Override
  public PStack<T> clear() {
    return new ArrayListStack<>();
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
