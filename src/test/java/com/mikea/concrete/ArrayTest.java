package com.mikea.concrete;

public class ArrayTest extends StackTestCase {
  @Override
  protected PStack<String> newStack() {
    return PArray.newBinaryArray();
  }
}
