package com.mikea.concrete;

@SuppressWarnings("UnusedDeclaration")
public interface PCollection<T> extends Iterable<T> {
  boolean isEmpty();
  int size();
  PCollection<T> clear();
}
