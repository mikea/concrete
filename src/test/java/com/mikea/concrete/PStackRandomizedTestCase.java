package com.mikea.concrete;


import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PStackRandomizedTestCase extends RandomizedTestCase<PStack<Integer>> {
  protected abstract PStack<Integer> newStack();

  public PStackRandomizedTestCase() {
    super(
        (stack, s) -> stack.popFront(),
        PStack::pushFront
    );
  }

  @Override
  protected PStack<Integer> newTestContainer() {
    return newStack();
  }

  @Override
  protected PStack<Integer> newGoldenContainer() {
    return new ArrayListStack<>();
  }

  @Override
  protected void assertContainersAreEqual(PStack<Integer> golden, PStack<Integer> test) {
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());
  }
}
