package com.mikea.concrete;


import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PStackRandomizedTestCase extends RandomizedTestCase<PStack<String>> {
  protected abstract PStack<String> newStack();

  public PStackRandomizedTestCase() {
    super(
        (stack, s) -> stack.popFront(),
        PStack::pushFront
    );
  }

  @Override
  protected PStack<String> newTestContainer() {
    return newStack();
  }

  @Override
  protected PStack<String> newGoldenContainer() {
    return new ArrayListStack();
  }

  @Override
  protected void assertContainersAreEqual(PStack<String> golden, PStack<String> test) {
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());
  }
}
