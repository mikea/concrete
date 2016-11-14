package com.mikea.concrete;

import org.junit.Test;

import java.util.NoSuchElementException;

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
    } catch (NoSuchElementException ignored) {
    }

    array = array.pushFrontAll("a", "b", "c");

    try {
      array.get(3);
      fail();
    } catch (NoSuchElementException ignored) {
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
      assertEquals(String.valueOf(i + 1000), array.get(i));
    }

    assertEquals("[1000, 1001, 1002, 1003, 1004, 1005]", array.toString());
  }
}
