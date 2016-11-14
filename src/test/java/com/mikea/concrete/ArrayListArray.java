package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListArray implements PArray<String> {
  private final ArrayList<String> data;

  private ArrayListArray(ArrayList<String> data) {
    this.data = data;
  }

  ArrayListArray() {
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
  public PArray<String> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<String> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListArray(newData);
  }

  @Override
  public PArray<String> pushFront(String value) {
    ArrayList<String> newData = new ArrayList<>(data);
    newData.add(0, value);
    return new ArrayListArray(newData);
  }

  @Override
  public PArray<String> clear() {
    return new ArrayListArray();
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
  public String get(int index) {
    return data.get(index);
  }

  @Override
  public PArray<String> set(int index, String s) {
    ArrayList<String> newData = new ArrayList<>(data);
    newData.set(index, s);
    return new ArrayListArray(newData);
  }
}
