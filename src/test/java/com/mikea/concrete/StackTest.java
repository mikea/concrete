package com.mikea.concrete;

import com.google.common.collect.Iterables;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackTest extends TestCase {

  public void testEmptyStack() throws Exception {
    Stack<String> stack = new Stack<String>();
    assertTrue(stack.isEmpty());
    assertEquals("[]", Iterables.toString(stack));

    assertNull(stack.peek());
/*
    try {
      stack.peek();
      fail();
    } catch (NoSuchElementException e) {
      // expected;
    }
*/

    try {
      stack.pop();
      fail();
    } catch (NoSuchElementException e) {
      // expected;
    }
  }

  public void testIterator() throws Exception {
    Stack<String> stack = new Stack<String>();
    stack = stack.push("a");
    stack = stack.push("b");
    stack = stack.push("c");
    stack = stack.push("d");
    stack = stack.push("e");

    assertFalse(stack.isEmpty());
    assertEquals("[e, d, c, b, a]", Iterables.toString(stack));
  }

  public void testPopAndPeek() throws Exception {
    Stack<String> stack = new Stack<String>();
    assertTrue(stack.isEmpty());

    stack = stack.push("a");
    assertEquals("a", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.push("b");
    assertEquals("b", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.push("c");
    assertEquals("c", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.push("d");
    assertEquals("d", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.push("e");
    assertEquals("e", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.pop();
    assertEquals("d", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.pop();
    assertEquals("c", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.pop();
    assertEquals("b", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.pop();
    assertEquals("a", stack.peek());
    assertFalse(stack.isEmpty());

    stack = stack.pop();
    assertTrue(stack.isEmpty());
  }

  public void testEmptyStackIterator() throws Exception {
    Stack<String> stack = new Stack<String>();
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

  public void testReverse() throws Exception {
    Stack<String> stack = new Stack<String>();
    stack = stack.push("a");
    stack = stack.push("b");
    stack = stack.push("c");
    stack = stack.push("d");
    stack = stack.push("e");

    assertEquals("[e, d, c, b, a]", Iterables.toString(stack));
    assertEquals("[a, b, c, d, e]", Iterables.toString(stack.reverse()));
  }
}
