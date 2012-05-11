package com.mikea.concrete;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AmortizedQueueTest extends QueueTestCase {
  public void testEmptyQueue() throws Exception {
    AmortizedQueue<String> queue = new AmortizedQueue<String>();
    assertTrue(queue.isEmpty());
  }

  public void testPush() throws Exception {
    AmortizedQueue<String> queue = new AmortizedQueue<String>();
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
    AmortizedQueue<String> queue = new AmortizedQueue<String>();

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

    assertNull(queue.peek());
/*
    try {
      queue.peek();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }
*/

    try {
      queue.pop();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }
  }

  public void testRandomizedTest() throws Exception {
    AmortizedQueue<String> queue1 = new AmortizedQueue<String>();
    java.util.Queue<String> queue2 = new LinkedList<String>();
    compareQueues(queue1, queue2);
  }
}
