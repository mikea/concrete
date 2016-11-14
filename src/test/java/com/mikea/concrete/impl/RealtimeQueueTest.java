package com.mikea.concrete.impl;

import com.mikea.concrete.PQueueTestCase;

import static com.mikea.concrete.impl.RealtimeQueue.newRealtimeQueue;

public class RealtimeQueueTest extends PQueueTestCase {

  @Override
  protected RealtimeQueue<String> newQueue() {
    return newRealtimeQueue();
  }
}
