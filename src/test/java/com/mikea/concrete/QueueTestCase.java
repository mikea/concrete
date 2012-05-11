package com.mikea.concrete;

import static com.google.common.collect.Lists.newArrayList;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public abstract class QueueTestCase<Q extends CQueue<String, Q>> extends TestCase {

  protected abstract Q createQueue();

  public void testEmptyQueue() throws Exception {
    Q queue = createQueue();
    assertTrue(queue.isEmpty());
  }

  public void testPush() throws Exception {
    Q queue = createQueue();
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
    Q queue = createQueue();

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

  public void testSequence1() throws Exception {
    Q queue = createQueue();

    queue = queue.push("0");
    assertEquals("0", queue.peek());
    queue = queue.push("1");
    assertEquals("0", queue.peek());
    queue = queue.push("2");
    assertEquals("0", queue.peek());
    queue = queue.push("3");
    assertEquals("0", queue.peek());
    queue = queue.pop();
    assertEquals("1", queue.peek());
    queue = queue.push("4");
    assertEquals("1", queue.peek());
    queue = queue.push("5");
    assertEquals("1", queue.peek());
    queue = queue.pop();
    assertEquals("2", queue.peek());
    queue = queue.pop();
    assertEquals("3", queue.peek());
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
          queue1 = queue1.push(String.valueOf(iteration));
          queue2.add(String.valueOf(iteration));
          ops.add("push(" + iteration + ")");
          break;
        }
        case 1: {
          ops.add("pop");
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

          assertEquals(ops.toString(), noSuchElementException1, noSuchElementException2);
          break;
        }
      }

      assertEquals(ops.toString(), queue2.peek(), queue1.peek());
      assertEquals(ops.toString(), queue2.isEmpty(), queue1.isEmpty());
    }
  }

}
