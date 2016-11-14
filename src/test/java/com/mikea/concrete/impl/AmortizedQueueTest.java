package com.mikea.concrete.impl;

import com.mikea.concrete.PQueueTestCase;

import static com.mikea.concrete.impl.AmortizedQueue.newAmortizedQueue;

public class AmortizedQueueTest extends PQueueTestCase {

  @Override
  protected AmortizedQueue<String> newQueue() {
    return newAmortizedQueue();
  }
}
