package com.mikea.concrete;

import static com.mikea.concrete.RealtimeQueue.newRealtimeQueue;

public class RealtimeQueueTest extends QueueTestCase<RealtimeQueue<String>> {

  @Override
  protected RealtimeQueue<String> newQueue() {
    return newRealtimeQueue();
  }
}
