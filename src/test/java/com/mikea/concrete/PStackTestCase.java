package com.mikea.concrete;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public abstract class PStackTestCase {
  protected abstract PStack<String> newStack();

  @Test
  public void testNewStackIsEmpty() throws Exception {
    PStack<String> stack = newStack();
    assertTrue(stack.isEmpty());
  }

  @Test
  public void testEmptyStack() throws Exception {
    PStack<String> stack = newStack();
    assertTrue(stack.isEmpty());
    assertEquals("[]", Iterables.toString(stack));

    assertNull(stack.peekFront());

    try {
      stack.popFront();
      fail();
    } catch (NoSuchElementException e) {
      // expected;
    }
  }

  @Test
  public void testPushFront() throws Exception {
    PStack<String> stack = newStack();
    assertTrue(stack.isEmpty());
    stack = stack.pushFront("a");
    assertFalse(stack.isEmpty());
    stack = stack.pushFront("b");
    stack = stack.pushFront("c");
    stack = stack.pushFront("d");
    stack = stack.pushFront("e");
    stack = stack.pushFront("f");
    assertFalse(stack.isEmpty());
  }

  @Test
  public void testPushAndPeek() throws Exception {
    PStack<String> stack = newStack();
    assertTrue(stack.isEmpty());
    stack = stack.pushFront("a");
    assertFalse(stack.isEmpty());
    assertEquals("a", stack.peekFront());
    stack = stack.pushFront("b");
    assertEquals("b", stack.peekFront());
    stack = stack.pushFront("c");
    assertEquals("c", stack.peekFront());
    stack = stack.pushFront("d");
    assertEquals("d", stack.peekFront());
    stack = stack.pushFront("e");
    assertEquals("e", stack.peekFront());
    stack = stack.pushFront("f");
    assertEquals("f", stack.peekFront());
    assertFalse(stack.isEmpty());
  }

  @Test
  public void testIterator() throws Exception {
    PStack<String> stack = newStack();
    stack = stack.pushFront("a");
    stack = stack.pushFront("b");
    stack = stack.pushFront("c");
    stack = stack.pushFront("d");
    stack = stack.pushFront("e");

    assertFalse(stack.isEmpty());
    assertEquals("[e, d, c, b, a]", Iterables.toString(stack));
  }

  @Test
  public void testPopAndPeek() throws Exception {
    PStack<String> stack = newStack();
    assertTrue(stack.isEmpty());

    stack = stack.pushFront("a");
    assertEquals("a", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.pushFront("b");
    assertEquals("b", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.pushFront("c");
    assertEquals("c", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.pushFront("d");
    assertEquals("d", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.pushFront("e");
    assertEquals("e", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.popFront();
    assertEquals("d", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.popFront();
    assertEquals("c", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.popFront();
    assertEquals("b", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.popFront();
    assertEquals("a", stack.peekFront());
    assertFalse(stack.isEmpty());

    stack = stack.popFront();
    assertTrue(stack.isEmpty());
  }

  @Test
  public void testEmptyStackIterator() throws Exception {
    PStack<String> stack = newStack();
    Iterator<String> iterator = stack.iterator();
    assertFalse(iterator.hasNext());

    try {
      iterator.next();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }
    try {
      iterator.remove();
      fail();
    } catch (UnsupportedOperationException e) {
      // expected
    }
  }

  @Test
  public void testReverse() throws Exception {
    PStack<String> stack = newStack();
    stack = stack.pushFront("a");
    stack = stack.pushFront("b");
    stack = stack.pushFront("c");
    stack = stack.pushFront("d");
    stack = stack.pushFront("e");

    assertEquals("[e, d, c, b, a]", Iterables.toString(stack));
    assertEquals("[a, b, c, d, e]", Iterables.toString(stack.reverse()));
  }

  @Test
  public void testClear() throws Exception {
    PStack<String> stack = newStack();
    stack = stack.pushFront("a");
    assertFalse(stack.isEmpty());
    stack = stack.clear();
    assertTrue(stack.isEmpty());
  }

  @Test
  public void testSize() throws Exception {
    PStack<String> stack = newStack();

    for (int i = 0; i < 100; ++i) {
      assertEquals(i, stack.size());
      stack = stack.pushFront(String.valueOf(i));
    }

    assertEquals(100, stack.size());
    for (int i = 0; i < 100; ++i) {
      assertEquals(100 - i, stack.size());
      stack = stack.popFront();
    }

    stack = stack.clear();
    assertTrue(stack.isEmpty());
    assertEquals(0, stack.size());
  }

  @Test
  public void testToString() throws Exception {
    PStack<String> stack = newStack();
    stack = stack.pushFrontAll("a", "b", "c", "d", "e");
    assertEquals("[a, b, c, d, e]", stack.toString());
  }
}
