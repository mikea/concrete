package com.mikea.concrete;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public abstract class RandomizedTestCase<Container> {
  private final List<BiFunction<Container, String, Container>> operations;

  @SafeVarargs
  RandomizedTestCase(BiFunction<Container, String, Container>... operations) {
    if (operations.length == 0) {
      throw new IllegalArgumentException();
    }

    this.operations = Arrays.asList(operations);
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
      BiFunction<Container, String, Container> op = operations.get(random.nextInt(operations.size()));
      String s = String.valueOf(i);

      boolean goldenNsee = false;
      try {
        golden = op.apply(golden, s);
      } catch (NoSuchElementException e) {
        goldenNsee = true;
      }

      boolean testNsee = false;
      try {
        test = op.apply(test, s);
      } catch (NoSuchElementException e) {
        testNsee = true;
      }

      System.out.println(golden + " " + test);
      assertEquals(goldenNsee, testNsee);
      assertContainersAreEqual(golden, test);
    }
  }
}
