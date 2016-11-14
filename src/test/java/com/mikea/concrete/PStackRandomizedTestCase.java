package com.mikea.concrete;


import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public abstract class PStackRandomizedTestCase {
  protected abstract PStack<String> newStack();

  @Test
  public void testMain() {
    PStack<String> stackUnderTest = newStack();
    PStack<String> goldenStack = newGoldenStack();

    List<BiFunction<PStack<String>, String, PStack<String>>> operations = Arrays.asList(
        (stack, s) -> stack.popFront(),
        PStack::pushFront
    );

    Random random = new Random();

    assertStacksAreEqual(stackUnderTest, goldenStack);
    for (int i = 0; i < 1000; ++i) {
      BiFunction<PStack<String>, String, PStack<String>> op = operations.get(random.nextInt(operations.size()));
      String s = String.valueOf(i);

      boolean goldenNsee = false;
      try {
        goldenStack = op.apply(goldenStack, s);
      } catch (NoSuchElementException e) {
        goldenNsee = true;
      }

      boolean testNsee = false;
      try {
        stackUnderTest = op.apply(stackUnderTest, s);
      } catch (NoSuchElementException e) {
        testNsee = true;
      }

      assertEquals(goldenNsee, testNsee);
      assertStacksAreEqual(goldenStack, stackUnderTest);
    }
  }

  private void assertStacksAreEqual(PStack<String> goldenStack, PStack<String> stackUnderTest) {
    assertEquals(goldenStack.size(), stackUnderTest.size());
    assertEquals(goldenStack.toString(), stackUnderTest.toString());
    assertEquals(Iterables.toString(goldenStack), Iterables.toString(stackUnderTest));
  }

  private PStack<String> newGoldenStack() {
    return new ArrayListStack();
  }

  private static class ArrayListStack implements PStack<String> {
    private final ArrayList<String> data;

    private ArrayListStack(ArrayList<String> data) {
      this.data = data;
    }

    private ArrayListStack() {
      this(new ArrayList<>());
    }

    @Override
    public String peekFront() {
      if (isEmpty()) {
        throw new NoSuchElementException();
      }
      return data.get(0);
    }

    @Override
    public PStack<String> popFront() {
      if (isEmpty()) {
        throw new NoSuchElementException();
      }
      ArrayList<String> newData = new ArrayList<>(data);
      newData.remove(0);
      return new ArrayListStack(newData);
    }

    @Override
    public PStack<String> pushFront(String value) {
      ArrayList<String> newData = new ArrayList<>(data);
      newData.add(0, value);
      return new ArrayListStack(newData);
    }

    @Override
    public PStack<String> clear() {
      return new ArrayListStack();
    }

    @Override
    public boolean isEmpty() {
      return data.isEmpty();
    }

    @Override
    public int size() {
      return data.size();
    }

    @Override
    public String toString() {
      return data.toString();
    }
  }
}
