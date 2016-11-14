package com.mikea.concrete;

import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PQueueRandomizedTestCase extends RandomizedTestCase<PQueue<Integer>> {
  protected abstract PQueue<Integer> newQueue();

  public PQueueRandomizedTestCase() {
    super(
        (queue, s) -> queue.popFront(),
        PQueue::pushBack
    );
  }

  @Override
  protected PQueue<Integer> newTestContainer() {
    return newQueue();
  }

  @Override
  protected PQueue<Integer> newGoldenContainer() {
    return new ArrayListQueue<>();
  }

  @Override
  protected void assertContainersAreEqual(PQueue<Integer> golden, PQueue<Integer> test) {
    assertEquals(golden.isEmpty(), test.isEmpty());
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());
  }
}
