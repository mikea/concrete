package com.mikea.concrete;

import static com.google.common.collect.Lists.newArrayList;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public abstract class QueueTestCase<Q extends PQueue<String, Q>> extends TestCase {

  protected abstract Q createQueue();

  public void testEmptyQueue() throws Exception {
    Q queue = createQueue();
    assertTrue(queue.isEmpty());
  }

  public void testPush() throws Exception {
    Q queue = createQueue();
    queue = queue.pushFront("a");
    assertFalse(queue.isEmpty());
    queue = queue.pushFront("b");
    assertFalse(queue.isEmpty());
    queue = queue.pushFront("c");
    assertFalse(queue.isEmpty());
    queue = queue.pushFront("d");
    assertFalse(queue.isEmpty());
    queue = queue.pushFront("e");
    assertFalse(queue.isEmpty());
  }

  public void testPushPopPeek() throws Exception {
    Q queue = createQueue();

    queue = queue.pushFront("a");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushFront("b");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushFront("c");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushFront("d");
    assertFalse(queue.isEmpty());
    assertEquals("a", queue.peekFront());

    queue = queue.pushFront("e");
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

  public void testSequence1() throws Exception {
    Q queue = createQueue();

    queue = queue.pushFront("0");
    assertEquals("0", queue.peekFront());
    queue = queue.pushFront("1");
    assertEquals("0", queue.peekFront());
    queue = queue.pushFront("2");
    assertEquals("0", queue.peekFront());
    queue = queue.pushFront("3");
    assertEquals("0", queue.peekFront());
    queue = queue.popFront();
    assertEquals("1", queue.peekFront());
    queue = queue.pushFront("4");
    assertEquals("1", queue.peekFront());
    queue = queue.pushFront("5");
    assertEquals("1", queue.peekFront());
    queue = queue.popFront();
    assertEquals("2", queue.peekFront());
    queue = queue.popFront();
    assertEquals("3", queue.peekFront());
  }

  public void testRandomOperations() throws Exception {
    compareQueues(createQueue(), new LinkedList<String>());
  }

  protected void compareQueues(Q queue1, Queue<String> queue2) {
    boolean trackOps = false;
    List<String> ops = newArrayList();
    Random random = new Random();

    for (int iteration = 0; iteration < 10000; ++iteration) {
      if (!trackOps) {
        ops.clear();
      }
      switch (random.nextInt(2)) {
        case 0: {
          queue1 = queue1.pushFront(String.valueOf(iteration));
          queue2.add(String.valueOf(iteration));
          ops.add("pushFront(" + iteration + ")");
          break;
        }
        case 1: {
          ops.add("popFront");
          boolean noSuchElementException1 = false;
          boolean noSuchElementException2 = false;

          try {
            queue1 = queue1.popFront();
          } catch (NoSuchElementException e) {
            noSuchElementException1 = true;
          }
          try {
            queue2.remove();
          } catch (NoSuchElementException e) {
            noSuchElementException2 = true;
          }

          assertEquals(ops.toString(), noSuchElementException1, noSuchElementException2);
          break;
        }
      }

      assertEquals(ops.toString(), queue2.peek(), queue1.peekFront());
      assertEquals(ops.toString(), queue2.isEmpty(), queue1.isEmpty());
    }
  }

}
