package com.mikea.concrete;

import com.mikea.concrete.impl.RealtimeQueue;

import static com.mikea.concrete.impl.RealtimeQueue.newRealtimeQueue;

public class RealtimeQueueTest extends QueueTestCase<RealtimeQueue<String>> {

  @Override
  protected RealtimeQueue<String> newQueue() {
    return newRealtimeQueue();
  }
}
