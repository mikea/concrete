package com.mikea.concrete;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public abstract class PQueueTestCase {

  protected abstract PQueue<String> newQueue();

  @Test
  public void testEmptyQueue() throws Exception {
    PQueue<String> queue = newQueue();
    assertTrue(queue.isEmpty());
    assertEquals(0, queue.size());
  }

  @Test
  public void testPush() throws Exception {
    PQueue<String> queue = newQueue();
    assertEquals(0, queue.size());
    queue = queue.pushBack("a");
    assertEquals(1, queue.size());
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("b");
    assertEquals(2, queue.size());
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("c");
    assertEquals(3, queue.size());
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("d");
    assertEquals(4, queue.size());
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("e");
    assertEquals(5, queue.size());
    assertFalse(queue.isEmpty());
  }

  @Test
  public void testClear() throws Exception {
    PQueue<String> queue = newQueue().pushBackAll("a", "b", "c");
    assertEquals(3, queue.size());
    queue = queue.clear();
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
  }

  @Test
  public void testPushPopPeek() throws Exception {
    PQueue<String> queue = newQueue();

    queue = queue.pushBack("a");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushBack("b");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushBack("c");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushBack("d");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushBack("e");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.popFront();
    assertFalse(queue.isEmpty());
    assertEquals("b", queue.peekFront());

    queue = queue.popFront();
    assertFalse(queue.isEmpty());
    assertEquals("c", queue.peekFront());

    queue = queue.popFront();
    assertFalse(queue.isEmpty());
    assertEquals("d", queue.peekFront());

    queue = queue.popFront();
    assertFalse(queue.isEmpty());
    assertEquals("e", queue.peekFront());

    queue = queue.popFront();
    assertTrue(queue.isEmpty());

    assertNull(queue.peekFront());
/*
    try {
      queue.peekFront();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }
*/

    try {
      queue.popFront();
      fail();
    } catch (NoSuchElementException e) {
      // expected
    }
  }

  @Test
  public void testSequence1() throws Exception {
    PQueue<String> queue = newQueue();

    queue = queue.pushBack("0");
    assertEquals("0", queue.peekFront());
    queue = queue.pushBack("1");
    assertEquals("0", queue.peekFront());
    queue = queue.pushBack("2");
    assertEquals("0", queue.peekFront());
    queue = queue.pushBack("3");
    assertEquals("0", queue.peekFront());
    queue = queue.popFront();
    assertEquals("1", queue.peekFront());
    queue = queue.pushBack("4");
    assertEquals("1", queue.peekFront());
    queue = queue.pushBack("5");
    assertEquals("1", queue.peekFront());
    queue = queue.popFront();
    assertEquals("2", queue.peekFront());
    queue = queue.popFront();
    assertEquals("3", queue.peekFront());
  }

  @Test
  public void testSize() throws Exception {
    PQueue<String> queue = newQueue();

    System.out.println(queue);
    for (int i = 0; i < 100; ++i) {
      assertEquals(i, queue.size());
      queue = queue.pushBack(String.valueOf(i));
      System.out.println(queue);
    }

    for (int i = 0; i < 100; ++i) {
      assertEquals(100 - i, queue.size());
      queue = queue.popFront();
    }
  }
}
