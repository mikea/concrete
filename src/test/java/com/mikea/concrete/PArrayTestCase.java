package com.mikea.concrete;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class PArrayTestCase extends PStackTestCase {
  protected abstract PArray<String> newArray();

  @Override
  protected PStack<String> newStack() {
    return newArray();
  }

  @Test
  public void testGetBeyond() {
    PArray<String> array = newArray();

    try {
      array.get(0);
      fail();
    } catch (IndexOutOfBoundsException ignored) {
    }

    array = array.pushFrontAll("a", "b", "c");

    try {
      array.get(3);
      fail();
    } catch (IndexOutOfBoundsException ignored) {
    }

    try {
      array.get(-1);
      fail();
    } catch (IndexOutOfBoundsException ignored) {
    }
  }

  @Test
  public void testSetBeyond() {
    PArray<String> array = newArray();

    try {
      array.set(0, "a");
      fail();
    } catch (IndexOutOfBoundsException ignored) {
    }

    array = array.pushFrontAll("a", "b", "c");

    try {
      array.set(3, " ");
      fail();
    } catch (IndexOutOfBoundsException ignored) {
    }

    try {
      array.set(-1, "a");
      fail();
    } catch (IndexOutOfBoundsException ignored) {
    }
  }

  @Test
  public void testGet() {
    PArray<String> array = newArray();
    array = array.pushFrontAll("1", "2", "3", "4", "5", "6");

    for (int i = 0; i < array.size(); ++i) {
      assertEquals(String.valueOf(i + 1), array.get(i));
    }
  }

  @Test
  public void testSet() {
    PArray<String> array = newArray();
    array = array.pushFrontAll("1", "2", "3", "4", "5", "6");

    for (int i = 0; i < array.size(); ++i) {
      array = array.set(i, String.valueOf(i + 1000));
    }

    assertEquals("[1000, 1001, 1002, 1003, 1004, 1005]", array.toString());
  }
}
