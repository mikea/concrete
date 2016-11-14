package com.mikea.concrete.impl;

import com.mikea.concrete.PArray;
import com.mikea.concrete.PStack;
import com.mikea.concrete.PStackTestCase;

public class BinaryArrayTest extends PStackTestCase {
  @Override
  protected PStack<String> newStack() {
    return PArray.newBinaryArray();
  }
}
