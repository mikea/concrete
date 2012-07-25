package com.mikea.concrete;

import static com.mikea.concrete.Stack.newStack;

/**
 * Real-time queue operation as described in "Real Time Queue Operations in Pure LISP" by
 * Hood, Robert T. & Melville, Robert C.
 */
public class RealtimeQueue<T> implements PQueue<T> {
  private final Stack<T> head;
  private final Stack<T> tail;

  private final Stack<T> tailReverseFrom;
  private final Stack<T> tailReverseTo;
  private final Stack<T> headReverseFrom;
  private final Stack<T> headReverseTo;
  private final long headCopied;

  private RealtimeQueue(Stack<T> head, Stack<T> tail, Stack<T> tailReverseFrom,
      Stack<T> tailReverseTo, Stack<T> headReverseFrom, Stack<T> headReverseTo, long headCopied) {
    if (tail.size() > head.size()) {
      assertEmptyReverseStacks(headReverseFrom, headReverseTo, tailReverseFrom, tailReverseTo);
      if (tail.size() == 1) {
        this.head = tail;
        this.tail = newStack();
        this.tailReverseFrom = null;
        this.tailReverseTo = null;
        this.headReverseFrom = null;
        this.headReverseTo = null;
      } else {
        this.head = head;
        this.tail = newStack();

        this.tailReverseFrom = tail;
        this.tailReverseTo = newStack();
        this.headReverseFrom = head;
        this.headReverseTo = newStack();
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
    this(Stack.<T>newStack(), Stack.<T>newStack(), null, null, null, null, 0);
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

  private static <T> RealtimeQueue<T> newQueue(Stack<T> head, Stack<T> tail,
      Stack<T> tailReverseFrom, Stack<T> tailReverseTo, Stack<T> headReverseFrom,
      Stack<T> headReverseTo, long headCopied) {
    RealtimeQueue<T> result = new RealtimeQueue<T>(head, tail, tailReverseFrom,
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

  private static <T> RealtimeQueue<T> step(Stack<T> head, Stack<T> tail, Stack<T> tailReverseFrom,
      Stack<T> tailReverseTo, Stack<T> headReverseFrom, Stack<T> headReverseTo, long headCopied) {
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

    return new RealtimeQueue<T>(head, tail, tailReverseFrom, tailReverseTo, headReverseFrom, headReverseTo, headCopied);
  }

  @Override
  public boolean isEmpty() {
    boolean result = head.isEmpty() && tail.isEmpty();
    if (result) {
      assertEmptyReverseStacks(headReverseFrom, headReverseTo, tailReverseFrom, tailReverseTo);
    }
    return result;
  }

  private static <T> void assertEmptyReverseStacks(Stack<T> headReverseFrom, Stack<T> headReverseTo,
      Stack<T> tailReverseFrom, Stack<T> tailReverseTo) {
    if (tailReverseFrom != null || tailReverseTo != null || headReverseFrom != null || headReverseTo
        != null) {
      throw new IllegalStateException("Internal error: invariant failure.");
    }
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException(
        "size is not implemented in com.mikea.concrete.RealtimeQueue");
  }

  @Override
  public RealtimeQueue<T> clear() {
    return new RealtimeQueue<T>();
  }

  public static <T> RealtimeQueue<T> newRealtimeQueue() {
    return new RealtimeQueue<T>();
  }
}
