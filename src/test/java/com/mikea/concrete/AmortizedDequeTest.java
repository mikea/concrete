package com.mikea.concrete;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static com.mikea.concrete.AmortizedDeque.newAmortizedDeque;

public class AmortizedDequeTest extends QueueTestCase<AmortizedDeque<String>>  {

  public void testBackOperations() throws Exception {
    AmortizedDeque<String> deque = newDeque();
  }

  @Override
  protected AmortizedDeque<String> newQueue() {
    return newDeque();
  }

  protected AmortizedDeque<String> newDeque() {
    return newAmortizedDeque();
  }

  @Override
  protected int getNumberOfOps() {
    return super.getNumberOfOps() + 2;
  }

  @Override
  protected Op createRandomOp(int rnd, final int iteration) {
    switch (rnd) {
      case 2: {
        return new Op() {
          @Override
          public PQueue<String> run(PQueue<String> queue1, LinkedList<String> queue2) {
            queue2.addFirst(String.valueOf(iteration));
            return ((PDeque<String>) queue1).pushFront(String.valueOf(iteration));
          }

          @Override
          public String toString() {
            return "pushFront(" + iteration + ");";
          }
        };
      }
      case 3: {
        return new Op() {
          @Override
          public PQueue<String> run(PQueue<String> queue1, LinkedList<String> queue2) {
            boolean noSuchElement1 = false;
            boolean noSuchElement2 = false;
            try {
              queue2.removeLast();
            } catch (NoSuchElementException e) {
              noSuchElement2 = true;
            }

            try {
              queue1 = ((PDeque<String>) queue1).popBack();
            } catch (Exception e) {
              noSuchElement1 = true;
            }

            assertEquals(noSuchElement1, noSuchElement2);

            return queue1;
          }

          @Override
          public String toString() {
            return "popBack";
          }
        };
      }
      default: return super.createRandomOp(rnd, iteration);
    }
  }

  @Override
  protected void assertSameState(PQueue<String> queue1, LinkedList<String> queue2) {
    super.assertSameState(queue1, queue2);
    assertEquals(queue2.peekLast(), ((PDeque<String>)queue1).peekBack());
  }
}
