package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListStack implements PStack<String> {
  private final ArrayList<String> data;

  private ArrayListStack(ArrayList<String> data) {
    this.data = data;
  }

  ArrayListStack() {
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
  public PStack<String> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<String> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListStack(newData);
  }

  @Override
  public PStack<String> pushFront(String value) {
    ArrayList<String> newData = new ArrayList<>(data);
    newData.add(0, value);
    return new ArrayListStack(newData);
  }

  @Override
  public PStack<String> clear() {
    return new ArrayListStack();
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
