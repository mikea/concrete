package com.mikea.concrete;

import junit.framework.TestCase;

import java.util.NoSuchElementException;

public class QueueTest extends TestCase {
  public void testEmptyQueue() throws Exception {
    Queue<String> queue = new Queue<String>();
    assertTrue(queue.isEmpty());
  }

  public void testPush() throws Exception {
    Queue<String> queue = new Queue<String>();
    queue = queue.push("a");
    assertFalse(queue.isEmpty());
    queue = queue.push("b");
    assertFalse(queue.isEmpty());
    queue = queue.push("c");
    assertFalse(queue.isEmpty());
    queue = queue.push("d");
    assertFalse(queue.isEmpty());
    queue = queue.push("e");
    assertFalse(queue.isEmpty());
  }

  public void testPushPopPeek() throws Exception {
    Queue<String> queue = new Queue<String>();

    queue = queue.push("a");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peek());

    queue = queue.push("b");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peek());

    queue = queue.push("c");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peek());

    queue = queue.push("d");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peek());

    queue = queue.push("e");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peek());

    queue = queue.pop();
    assertFalse(queue.isEmpty());
    assertEquals("b", queue.peek());

    queue = queue.pop();
    assertFalse(queue.isEmpty());
    assertEquals("c", queue.peek());

    queue = queue.pop();
    assertFalse(queue.isEmpty());
    assertEquals("d", queue.peek());

    queue = queue.pop();
    assertFalse(queue.isEmpty());
    assertEquals("e", queue.peek());

    queue = queue.pop();
    assertTrue(queue.isEmpty());

    try {
      queue.peek();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }

    try {
      queue.pop();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }
  }
}
