package com.mikea.concrete;

public class StackTest extends StackTestCase {
  @Override
  protected PStack<String> newStack() {
    return PStack.newStack();
  }
}
