package com.mikea.concrete;

public abstract class PDequeRandomizedTestCase extends PStackRandomizedTestCase {
  protected abstract PDeque<String> newDeque();

  @Override
  protected PStack<String> newStack() {
    return newDeque();
  }
}
