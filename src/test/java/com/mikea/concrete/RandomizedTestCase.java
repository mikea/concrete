package com.mikea.concrete;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

public abstract class RandomizedTestCase<Container> {
  interface Mutator<T> extends BiFunction<T, Integer, T> {
  }

  private final List<Mutator<Container>> mutators;

  @SafeVarargs
  RandomizedTestCase(Mutator<Container>... mutators) {
    if (mutators.length == 0) {
      throw new IllegalArgumentException();
    }

    this.mutators = Arrays.asList(mutators);
  }

  protected abstract Container newTestContainer();

  protected abstract Container newGoldenContainer();

  protected abstract void assertContainersAreEqual(Container golden, Container test);

  @Test
  public void testMain() {
    Container test = newTestContainer();
    Container golden = newGoldenContainer();

    Random random = new Random();

    assertContainersAreEqual(golden, test);
    System.out.println(golden + " " + test);
    for (int i = 0; i < 1000; ++i) {
      Mutator<Container> op = mutators.get(random.nextInt(mutators.size()));
      int rnd = random.nextInt();

      Exception goldenException = null;
      try {
        golden = op.apply(golden, rnd);
      } catch (Exception e) {
        goldenException = e;
      }

      Exception testException = null;
      try {
        test = op.apply(test, rnd);
      } catch (Exception e) {
        testException = e;
      }

      System.out.println(golden + " " + test);
      assertContainersAreEqual(golden, test);

      if (goldenException == null) {
        assertNull(testException);
      } else {
        assertNotNull(testException);
        assertEquals(goldenException.getClass(), testException.getClass());
      }
    }
  }
}
