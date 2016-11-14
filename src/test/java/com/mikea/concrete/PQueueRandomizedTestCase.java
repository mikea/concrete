package com.mikea.concrete;

import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PQueueRandomizedTestCase extends RandomizedTestCase<PQueue<String>> {
  protected abstract PQueue<String> newQueue();

  public PQueueRandomizedTestCase() {
    super(
        (queue, s) -> queue.popFront(),
        PQueue::pushBack
    );
  }

  @Override
  protected PQueue<String> newTestContainer() {
    return newQueue();
  }

  @Override
  protected PQueue<String> newGoldenContainer() {
    return new ArrayListQueue();
  }

  @Override
  protected void assertContainersAreEqual(PQueue<String> golden, PQueue<String> test) {
    assertEquals(golden.isEmpty(), test.isEmpty());
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());
  }
}
