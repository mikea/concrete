package com.mikea.concrete;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

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
    Queue<String> queue1 = new Queue<String>();
    java.util.Queue<String> queue2 = new LinkedList<String>();
    Random random = new Random();

    for (int iteration = 0; iteration < 10000; ++iteration) {
      switch (random.nextInt(2)) {
        case 0: {
          queue1 = queue1.push(String.valueOf(iteration));
          queue2.add(String.valueOf(iteration));
          break;
        }
        case 1: {
          boolean noSuchElementException1 = false;
          boolean noSuchElementException2 = false;

          try {
            queue1 = queue1.pop();
          } catch (NoSuchElementException e) {
            noSuchElementException1 = true;
          }
          try {
            queue2.remove();
          } catch (NoSuchElementException e) {
            noSuchElementException2 = true;
          }

          assertEquals(noSuchElementException1, noSuchElementException2);
          break;
        }
      }

      assertEquals(queue2.peek(), queue1.peek());
      assertEquals(queue2.isEmpty(), queue1.isEmpty());
    }
  }
}
