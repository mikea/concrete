package com.mikea.concrete.impl;

import com.mikea.concrete.PDeque;
import com.mikea.concrete.PDequeTestCase;

public class AmortizedDequeTest extends PDequeTestCase {
  @Override
  protected PDeque<String> newDeque() {
    return PDeque.newAmortizedDeque();
  }
}
