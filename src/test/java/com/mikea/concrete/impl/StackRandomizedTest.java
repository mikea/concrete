package com.mikea.concrete.impl;

import com.mikea.concrete.PStack;
import com.mikea.concrete.PStackRandomizedTestCase;

public class StackRandomizedTest extends PStackRandomizedTestCase {
  @Override
  protected PStack<String> newStack() {
    return PStack.newStack();
  }
}
