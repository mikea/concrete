package com.mikea.concrete;

import junit.framework.TestCase;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

public abstract class QueueTestCase<Q extends PQueue<String, Q>> extends TestCase {

  protected abstract Q newQueue();

  public void testEmptyQueue() throws Exception {
    Q queue = newQueue();
    assertTrue(queue.isEmpty());
  }

  public void testPush() throws Exception {
    Q queue = newQueue();
    queue = queue.pushBack("a");
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("b");
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("c");
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("d");
    assertFalse(queue.isEmpty());
    queue = queue.pushBack("e");
    assertFalse(queue.isEmpty());
  }

  public void testPushPopPeek() throws Exception {
    Q queue = newQueue();

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

  public void testSequence1() throws Exception {
    Q queue = newQueue();

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

  public void testRandomOperations() throws Exception {
    compareQueues(newQueue(), new LinkedList<String>());
  }

  protected void compareQueues(Q queue1, LinkedList<String> queue2) {
    List<Op<Q>> ops = newArrayList();
    Random random = new Random();

    for (int iteration = 0; iteration < 10000; ++iteration) {
      Op<Q> op = createRandomOp(random.nextInt(getNumberOfOps()), iteration);
      ops.add(op);

      queue1 = op.run(queue1, queue2);
      assertSameState(queue1, queue2);
    }
  }

  protected int getNumberOfOps() {
    return 2;
  }

  protected void assertSameState(Q queue1, LinkedList<String> queue2) {
    assertEquals(queue2.isEmpty(), queue1.isEmpty());
    assertEquals(queue2.peekFirst(), queue1.peekFront());
  }

  protected Op<Q> createRandomOp(int rnd, final int iteration) {
    switch (rnd) {
      case 0: {
        return new Op<Q>() {
          @Override
          public String toString() {
            return "pushBack(" + iteration + ")";
          }

          @Override
          public Q run(Q queue1, LinkedList<String> queue2) {
            queue2.add(String.valueOf(iteration));
            return queue1.pushBack(String.valueOf(iteration));
          }
        };
      }

      case 1: {
        return new Op<Q>() {
          @Override
          public Q run(Q queue1, LinkedList<String> queue2) {
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

            assertEquals(noSuchElementException1, noSuchElementException2);
            return queue1;
          }

          @Override
          public String toString() {
            return "popFront";
          }
        };
      }
    }

    throw new IllegalStateException("Bad operation index: " + rnd);
  }

  protected interface Op<Q> {
    Q run(Q queue1, LinkedList<String> queue2);
  }
}
