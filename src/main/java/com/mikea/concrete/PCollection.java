package com.mikea.concrete;

@SuppressWarnings("UnusedDeclaration")
public interface PCollection<T, Self extends PCollection> {
  boolean isEmpty();
  int size();
}
