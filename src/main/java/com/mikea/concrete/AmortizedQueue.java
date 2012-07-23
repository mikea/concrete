package com.mikea.concrete;

public class AmortizedQueue<T> extends AmortizedQueueImpl<T, AmortizedQueue<T>> {
  private AmortizedQueue() {
  }

  protected AmortizedQueue(Stack<T> head, Stack<T> tail) {
    super(head, tail);
  }

  @Override
  protected AmortizedQueue<T> newSelf(Stack<T> head, Stack<T> tail) {
    return new AmortizedQueue<T>(head, tail);
  }

  public static <T> AmortizedQueue<T> newAmortizedQueue() {
    return new AmortizedQueue<T>();
  }
}
