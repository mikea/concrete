package com.mikea.concrete.impl;

import com.mikea.concrete.PArray;
import com.mikea.concrete.PArrayRandomizedTestCase;

public class BinaryArrayRandomizedTest extends PArrayRandomizedTestCase {
  @Override
  protected PArray<Integer> newArray() {
    return PArray.newBinaryArray();
  }
}
