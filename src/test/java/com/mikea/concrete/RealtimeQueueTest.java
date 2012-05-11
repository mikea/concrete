package com.mikea.concrete;

public class RealtimeQueueTest extends QueueTestCase<RealtimeQueue<String>> {

  @Override
  protected RealtimeQueue<String> createQueue() {
    return new RealtimeQueue<String>();
  }
}
