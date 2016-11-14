package com.mikea.concrete.impl;

import com.mikea.concrete.PQueue;
import com.mikea.concrete.PQueueRandomizedTestCase;

public class AmortizedQueueRandomizedTest extends PQueueRandomizedTestCase {
  @Override
  protected PQueue<Integer> newQueue() {
    return PQueue.newAmortizedQueue();
  }
}
