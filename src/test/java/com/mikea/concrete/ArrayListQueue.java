package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListQueue implements PQueue<String> {
  private final ArrayList<String> data;

  private ArrayListQueue(ArrayList<String> data) {
    this.data = data;
  }

  ArrayListQueue() {
    this(new ArrayList<>());
  }

  @Override
  public String peekFront() {
    if (isEmpty()) {
      return null;
    }
    return data.get(0);
  }

  @Override
  public PQueue<String> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<String> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListQueue(newData);
  }

  @Override
  public PQueue<String> pushBack(String value) {
    ArrayList<String> newData = new ArrayList<>(data);
    newData.add(value);
    return new ArrayListQueue(newData);
  }

  @Override
  public PQueue<String> clear() {
    return new ArrayListQueue();
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
