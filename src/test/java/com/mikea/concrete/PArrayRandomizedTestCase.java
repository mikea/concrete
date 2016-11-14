package com.mikea.concrete;

import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PArrayRandomizedTestCase extends RandomizedTestCase<PArray<String>> {
  protected abstract PArray<String> newArray();

  public PArrayRandomizedTestCase() {
    // todo: add moe operations
    super(
        (stack, s) -> stack.popFront(),
        PArray::pushFront
    );
  }

  @Override
  protected PArray<String> newTestContainer() {
    return newArray();
  }

  @Override
  protected PArray<String> newGoldenContainer() {
    return new ArrayListArray();
  }

  @Override
  protected void assertContainersAreEqual(PArray<String> golden, PArray<String> test) {
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());

    // todo: verify gets as well.
  }
}
