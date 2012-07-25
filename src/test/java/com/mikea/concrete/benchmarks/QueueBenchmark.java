package com.mikea.concrete.benchmarks;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import com.mikea.concrete.AmortizedQueue;
import com.mikea.concrete.RealtimeQueue;

import java.util.Queue;

import static com.google.common.collect.Lists.newLinkedList;
import static com.mikea.concrete.AmortizedQueue.newAmortizedQueue;

public class QueueBenchmark extends SimpleBenchmark {
  @Param
  private Test test;

  @Param({"10", "100", "1000"})
  private int length;

  @Param
  private Implementation implementation;

  public void time(int reps) {
    for (int i = 0; i < reps; ++i) {
      test.rep(implementation, length);
    }
  }

  enum Implementation {
    AMORTIZED_QUEUE, LINKED_LIST, REALTIME_QUEUE
  }

  enum Test {
    PUSH_BACK {
      @Override
      void rep(Implementation implementation, int length) {
        switch (implementation) {
          default: throw new IllegalArgumentException();

            case AMORTIZED_QUEUE: {
              AmortizedQueue<Object> queue = newAmortizedQueue();
              String object = "foo";
              for (int j = 0; j < length; ++j) {
                queue = queue.pushBack(object);
              }
              break;
            }

            case REALTIME_QUEUE: {
              RealtimeQueue<Object> queue = new RealtimeQueue<Object>();
              String object = "foo";
              for (int j = 0; j < length; ++j) {
                queue = queue.pushBack(object);
              }
              break;
            }

            case LINKED_LIST: {
              Queue<Object> queue = newLinkedList();
              String object = "foo";
              for (int j = 0; j < length; ++j) {
                queue.add(object);
              }
            }
        }
      }
    };

    abstract void rep(Implementation implementation, int length);
  }

  public static void main(String[] args) {
    Runner.main(QueueBenchmark.class, args);
  }
}
