package com.mikea.concrete;

public class RealtimeQueueTest extends QueueTestCase<RealtimeQueue<String>> {

  @Override
  protected RealtimeQueue<String> newQueue() {
    return new RealtimeQueue<String>();
  }
}
