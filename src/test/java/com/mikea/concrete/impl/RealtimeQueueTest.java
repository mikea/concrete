package com.mikea.concrete.impl;

import com.mikea.concrete.PQueueTestCase;
import com.mikea.concrete.impl.RealtimeQueue;

import static com.mikea.concrete.impl.RealtimeQueue.newRealtimeQueue;

public class RealtimeQueueTest extends PQueueTestCase<RealtimeQueue<String>> {

  @Override
  protected RealtimeQueue<String> newQueue() {
    return newRealtimeQueue();
  }
}
