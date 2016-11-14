package com.mikea.concrete;

public abstract class PDequeTestCase extends PStackTestCase {
  protected abstract PDeque<String> newDeque();

  @Override
  protected PStack<String> newStack() {
    return newDeque();
  }
}
