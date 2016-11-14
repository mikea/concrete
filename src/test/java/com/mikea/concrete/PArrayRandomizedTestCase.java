package com.mikea.concrete;

public abstract class PArrayRandomizedTestCase extends PStackRandomizedTestCase {
  protected abstract PArray<String> newArray();

  @Override
  protected PStack<String> newStack() {
    return newArray();
  }
}
