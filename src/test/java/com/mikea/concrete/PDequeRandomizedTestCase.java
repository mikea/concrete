package com.mikea.concrete;

import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PDequeRandomizedTestCase extends RandomizedTestCase<PDeque<Integer>> {
  protected abstract PDeque<Integer> newDeque();

  public PDequeRandomizedTestCase() {
    super(
        (stack, s) -> stack.popFront(),
        (stack, s) -> stack.popBack(),
        PDeque::pushFront,
        PDeque::pushBack
    );
  }

  @Override
  protected PDeque<Integer> newTestContainer() {
    return newDeque();
  }

  @Override
  protected PDeque<Integer> newGoldenContainer() {
    return new ArrayListDeque<>();
  }

  @Override
  protected void assertContainersAreEqual(PDeque<Integer> golden, PDeque<Integer> test) {
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());
    assertEquals(golden.peekBack(), test.peekBack());
  }
}
