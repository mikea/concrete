package com.mikea.concrete.impl;

import com.mikea.concrete.PArray;
import com.mikea.concrete.PArrayTestCase;

public class BinaryArrayTest extends PArrayTestCase {
  @Override
  protected PArray<String> newArray() {
    return PArray.newBinaryArray();
  }
}
