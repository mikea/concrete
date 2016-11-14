package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListDeque<T> implements PDeque<T> {
  private final ArrayList<T> data;

  private ArrayListDeque(ArrayList<T> data) {
    this.data = data;
  }

  ArrayListDeque() {
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
  public PDeque<T> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<T> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListDeque<>(newData);
  }

  @Override
  public PDeque<T> pushFront(T value) {
    ArrayList<T> newData = new ArrayList<>(data);
    newData.add(0, value);
    return new ArrayListDeque<>(newData);
  }

  @Override
  public PDeque<T> popBack() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<T> newData = new ArrayList<>(data);
    newData.remove(data.size() - 1);
    return new ArrayListDeque<>(newData);
  }

  @Override
  public T peekBack() {
    if (isEmpty()) {
      return null;
    }
    return data.get(data.size() - 1);
  }

  @Override
  public PDeque<T> pushBack(T value) {
    ArrayList<T> newData = new ArrayList<>(data);
    newData.add(value);
    return new ArrayListDeque<>(newData);
  }

  @Override
  public PDeque<T> clear() {
    return new ArrayListDeque<>();
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
