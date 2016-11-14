package com.mikea.concrete;

import com.google.common.collect.Iterables;

import static org.junit.Assert.assertEquals;

public abstract class PArrayRandomizedTestCase extends RandomizedTestCase<PArray<Integer>> {
  protected abstract PArray<Integer> newArray();

  public PArrayRandomizedTestCase() {
    super(
        (array, i) -> array.popFront(),
        PArray::pushFront,
        (array, i) -> array.set(i % (array.size() + 1 /* step beyond size from time to time */), i)
    );
  }

  @Override
  protected PArray<Integer> newTestContainer() {
    return newArray();
  }

  @Override
  protected PArray<Integer> newGoldenContainer() {
    return new ArrayListArray<>();
  }

  @Override
  protected void assertContainersAreEqual(PArray<Integer> golden, PArray<Integer> test) {
    assertEquals(golden.size(), test.size());
    assertEquals(golden.toString(), test.toString());
    assertEquals(Iterables.toString(golden), Iterables.toString(test));
    assertEquals(golden.peekFront(), test.peekFront());

    for (int i = 0; i < golden.size(); ++i) {
      assertEquals(golden.get(i), test.get(i));
    }
  }
}
