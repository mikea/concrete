package com.mikea.concrete.impl;

import com.mikea.concrete.PStack;
import com.mikea.concrete.PStackTestCase;

public class StackTest extends PStackTestCase {
  @Override
  protected PStack<String> newStack() {
    return PStack.newStack();
  }
}
