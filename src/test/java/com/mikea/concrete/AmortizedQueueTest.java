package com.mikea.concrete;

import static com.mikea.concrete.AmortizedQueue.newAmortizedQueue;

public class AmortizedQueueTest extends QueueTestCase<AmortizedQueue<String>> {

  @Override
  protected AmortizedQueue<String> newQueue() {
    return newAmortizedQueue();
  }
}
