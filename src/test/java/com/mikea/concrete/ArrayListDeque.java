package com.mikea.concrete;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class ArrayListDeque implements PDeque<String> {
  private final ArrayList<String> data;

  private ArrayListDeque(ArrayList<String> data) {
    this.data = data;
  }

  ArrayListDeque() {
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
  public PDeque<String> popFront() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<String> newData = new ArrayList<>(data);
    newData.remove(0);
    return new ArrayListDeque(newData);
  }

  @Override
  public PDeque<String> pushFront(String value) {
    ArrayList<String> newData = new ArrayList<>(data);
    newData.add(0, value);
    return new ArrayListDeque(newData);
  }

  @Override
  public PDeque<String> popBack() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    ArrayList<String> newData = new ArrayList<>(data);
    newData.remove(data.size() - 1);
    return new ArrayListDeque(newData);
  }

  @Override
  public String peekBack() {
    if (isEmpty()) {
      return null;
    }
    return data.get(data.size() - 1);
  }

  @Override
  public PDeque<String> pushBack(String value) {
    ArrayList<String> newData = new ArrayList<>(data);
    newData.add(value);
    return new ArrayListDeque(newData);
  }

  @Override
  public PDeque<String> clear() {
    return new ArrayListDeque();
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
