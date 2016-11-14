package com.mikea.concrete.impl;

import com.mikea.concrete.PQueueTestCase;
import com.mikea.concrete.impl.AmortizedQueue;

import static com.mikea.concrete.impl.AmortizedQueue.newAmortizedQueue;

public class AmortizedQueueTest extends PQueueTestCase<AmortizedQueue<String>> {

  @Override
  protected AmortizedQueue<String> newQueue() {
    return newAmortizedQueue();
  }
}
