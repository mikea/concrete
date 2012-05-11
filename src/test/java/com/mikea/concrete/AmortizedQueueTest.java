package com.mikea.concrete;

public class AmortizedQueueTest extends QueueTestCase<AmortizedQueue<String>> {

  @Override
  protected AmortizedQueue<String> createQueue() {
    return new AmortizedQueue<String>();
  }
}
