package com.mikea.concrete;

import com.mikea.concrete.impl.AmortizedQueue;

import static com.mikea.concrete.impl.AmortizedQueue.newAmortizedQueue;

public class AmortizedQueueTest extends QueueTestCase<AmortizedQueue<String>> {

  @Override
  protected AmortizedQueue<String> newQueue() {
    return newAmortizedQueue();
  }
}
