package com.mikea.concrete;

import static com.mikea.concrete.AmortizedQueue2.newAmortizedQueue2;

public class AmortizedQueue2Test extends QueueTestCase<AmortizedQueue2<String>> {

  @Override
  protected AmortizedQueue2<String> newQueue() {
    return newAmortizedQueue2();
  }
}
