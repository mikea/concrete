package com.mikea.concrete.impl;

import com.mikea.concrete.PDeque;
import com.mikea.concrete.PDequeRandomizedTestCase;

public class DequeRandomizedTest extends PDequeRandomizedTestCase {
  @Override
  protected PDeque<String> newDeque() {
    return PDeque.newAmortizedDeque();
  }
}
