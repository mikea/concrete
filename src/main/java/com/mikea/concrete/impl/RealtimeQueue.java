package com.mikea.concrete.impl;

import com.google.common.collect.Iterables;
import com.mikea.concrete.PQueue;
import com.mikea.concrete.PStack;

/**
 * Real-time queue operation as described in "Real Time Queue Operations in Pure LISP" by
 * Hood, Robert T. & Melville, Robert C.
 */
public class RealtimeQueue<T> implements PQueue<T> {
  private final PStack<T> head;
  private final PStack<T> tail;

  private final PStack<T> tailReverseFrom;
  private final PStack<T> tailReverseTo;
  private final PStack<T> headReverseFrom;
  private final PStack<T> headReverseTo;
  private final long headCopied;

  private RealtimeQueue(PStack<T> head, PStack<T> tail, PStack<T> tailReverseFrom,
                        PStack<T> tailReverseTo, PStack<T> headReverseFrom, PStack<T> headReverseTo, long headCopied) {
    if (tail.size() > head.size()) {
      assertEmptyReverseStacks(headReverseFrom, headReverseTo, tailReverseFrom, tailReverseTo);
      if (tail.size() == 1) {
        this.head = tail;
        this.tail = PStack.newStack();
        this.tailReverseFrom = null;
        this.tailReverseTo = null;
        this.headReverseFrom = null;
        this.headReverseTo = null;
      } else {
        this.head = head;
        this.tail = Stack.newStack();

        this.tailReverseFrom = tail;
        this.tailReverseTo = PStack.newStack();
        this.headReverseFrom = head;
        this.headReverseTo = PStack.newStack();
      }
    } else {
      this.head = head;
      this.tail = tail;

      this.tailReverseFrom = tailReverseFrom;
      this.tailReverseTo = tailReverseTo;
      this.headReverseFrom = headReverseFrom;
      this.headReverseTo = headReverseTo;
    }

    this.headCopied = headCopied;
  }

  private RealtimeQueue() {
    this(Stack.newStack(), Stack.newStack(), null, null, null, null, 0);
  }

  @Override
  public RealtimeQueue<T> pushBack(T value) {
    return newQueue(head, tail.pushFront(value), tailReverseFrom, tailReverseTo, headReverseFrom,
        headReverseTo, headCopied);
  }

  @Override
  public RealtimeQueue<T> popFront() {
    return newQueue(head.popFront(), tail, tailReverseFrom, tailReverseTo, headReverseFrom, headReverseTo, headCopied);
  }

  @Override
  public T peekFront() {
    return head.peekFront();
  }

  private static <T> RealtimeQueue<T> newQueue(PStack<T> head, PStack<T> tail,
                                               PStack<T> tailReverseFrom, PStack<T> tailReverseTo, PStack<T> headReverseFrom,
                                               PStack<T> headReverseTo, long headCopied) {

    RealtimeQueue<T> result = new RealtimeQueue<>(head, tail, tailReverseFrom,
        tailReverseTo, headReverseFrom, headReverseTo, headCopied);

    if (result.needsStep()) {
      result = step(result.head, result.tail, result.tailReverseFrom, result.tailReverseTo, result.headReverseFrom, result.headReverseTo, result.headCopied);
    }

    if (result.needsStep()) {
      result = step(result.head, result.tail, result.tailReverseFrom, result.tailReverseTo,
          result.headReverseFrom, result.headReverseTo, result.headCopied);
    }

    return result;
  }

  private boolean needsStep() {
    return this.tailReverseFrom != null && this.tailReverseTo != null && this.headReverseFrom != null && this.headReverseTo != null;
  }

  private static <T> RealtimeQueue<T> step(PStack<T> head, PStack<T> tail, PStack<T> tailReverseFrom,
                                           PStack<T> tailReverseTo, PStack<T> headReverseFrom, PStack<T> headReverseTo, long headCopied) {
    if (tailReverseFrom == null || tailReverseTo == null || headReverseFrom == null || headReverseTo == null) {
      throw new IllegalStateException("Internal error: invariant failure.");
    }

    if (!tailReverseFrom.isEmpty()) {
      tailReverseTo = tailReverseTo.pushFront(tailReverseFrom.peekFront());
      tailReverseFrom = tailReverseFrom.popFront();
    }
    if (!tailReverseFrom.isEmpty()) {
      tailReverseTo = tailReverseTo.pushFront(tailReverseFrom.peekFront());
      tailReverseFrom = tailReverseFrom.popFront();
    }

    if (!headReverseFrom.isEmpty()) {
      headReverseTo = headReverseTo.pushFront(headReverseFrom.peekFront());
      headReverseFrom = headReverseFrom.popFront();
    }
    if (!headReverseFrom.isEmpty()) {
      headReverseTo = headReverseTo.pushFront(headReverseFrom.peekFront());
      headReverseFrom = headReverseFrom.popFront();
    }

    if (tailReverseFrom.isEmpty()) {
      if (!headReverseTo.isEmpty() && headCopied < head.size()) {
        headCopied++;
        tailReverseTo = tailReverseTo.pushFront(headReverseTo.peekFront());
        headReverseTo = headReverseTo.popFront();
      }

      if (headCopied == head.size()) {
        head = tailReverseTo;
        tailReverseFrom = null;
        tailReverseTo = null;
        headReverseFrom = null;
        headReverseTo = null;
        headCopied = 0;
      }
    }

    return new RealtimeQueue<>(head, tail, tailReverseFrom, tailReverseTo, headReverseFrom, headReverseTo, headCopied);
  }

  @Override
  public boolean isEmpty() {
    boolean result = head.isEmpty() && tail.isEmpty();
    if (result) {
      assertEmptyReverseStacks(headReverseFrom, headReverseTo, tailReverseFrom, tailReverseTo);
    }
    return result;
  }

  private static <T> void assertEmptyReverseStacks(PStack<T> headReverseFrom, PStack<T> headReverseTo,
                                                   PStack<T> tailReverseFrom, PStack<T> tailReverseTo) {
    if (tailReverseFrom != null || tailReverseTo != null || headReverseFrom != null || headReverseTo
        != null) {
      throw new IllegalStateException("Internal error: invariant failure.");
    }
  }

  @Override
  public int size() {
    int size = (int) (head.size() + tail.size() - headCopied);
    if (tailReverseTo != null) {
      size += tailReverseTo.size() + tailReverseFrom.size();
    }

    return size;
  }

  @Override
  public RealtimeQueue<T> clear() { return new RealtimeQueue<>(); }

  public static <T> RealtimeQueue<T> newRealtimeQueue() {
    return new RealtimeQueue<>();
  }


  @Override
  public String toString() {
    return Iterables.toString(this);
  }
}
