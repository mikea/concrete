package com.mikea.concrete;

import junit.framework.TestCase;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public abstract class QueueTestCase extends TestCase {
  protected static <Q extends CQueue<String, Q>> void compareQueues(Q queue1, Queue<String> queue2) {
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
