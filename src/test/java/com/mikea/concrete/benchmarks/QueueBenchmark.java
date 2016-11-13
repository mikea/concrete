package com.mikea.concrete.benchmarks;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.VmOptions;
import com.google.caliper.runner.CaliperMain;
import com.mikea.concrete.impl.AmortizedQueue;
import com.mikea.concrete.impl.RealtimeQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.mikea.concrete.impl.AmortizedQueue.newAmortizedQueue;
import static com.mikea.concrete.impl.RealtimeQueue.newRealtimeQueue;

// bug in caliper: https://stackoverflow.com/questions/29199509/caliper-error-cicompilercount-of-1-is-invalid-must-be-at-least-2
@VmOptions("-XX:-TieredCompilation")
public class QueueBenchmark {
  @Param
  private Test test;

  @Param({"1", "4", "16", "64", "256"})
  private int length;

  @Param
  private Implementation implementation;

  private List<Op> ops;


  @BeforeExperiment
  protected void setUp() throws Exception {
    ops = test.createOps(length);
  }

  @Benchmark
  public void time(int reps) {
    Object o = "foo";
    for (int i = 0; i < reps; ++i) {
      AmortizedQueue<Object> amortizedQueue = newAmortizedQueue();
      RealtimeQueue<Object> realtimeQueue = newRealtimeQueue();
      LinkedList<Object> linkedList = newLinkedList();
      ArrayList<Object> arrayList = newArrayList();

      for (Op op : ops) {
        switch (op) {

          case PUSH_BACK:
            switch (implementation) {
              case AMORTIZED_QUEUE:
                amortizedQueue = amortizedQueue.pushBack(o);
                break;
              case REALTIME_QUEUE:
                realtimeQueue = realtimeQueue.pushBack(o);
                break;
              case LINKED_LIST:
                linkedList.addLast(o);
                break;
              case ARRAY_LIST:
                arrayList.add(o);
                break;
            }
            break;
          case POP_FRONT:
            switch (implementation) {
              case AMORTIZED_QUEUE:
                amortizedQueue = amortizedQueue.popFront();
                break;
              case REALTIME_QUEUE:
                realtimeQueue = realtimeQueue.popFront();
                break;
              case LINKED_LIST:
                linkedList.removeFirst();
                break;
              case ARRAY_LIST:
                arrayList.remove(0);
                break;
            }
            break;
        }
      }
    }
  }

  enum Implementation {
    AMORTIZED_QUEUE, REALTIME_QUEUE, LINKED_LIST, ARRAY_LIST
  }

  enum Op {
    PUSH_BACK, POP_FRONT
  }

  @SuppressWarnings("UnusedDeclaration")
  enum Test {
    PUSH_BACK {
      @Override
      public List<Op> createOps(int length) {
        List<Op> result = newArrayList();

        for (int i = 0; i < length; ++i) {
          result.add(Op.PUSH_BACK);
        }

        return result;
      }
    },

    RANDOM {
      @Override
      public List<Op> createOps(int length) {
        Random rnd = new Random();
        List<Op> result = newArrayList();

        int sz = 0;

        while (result.size() < length) {
          switch (rnd.nextInt(2)) {
            case 0: {
              sz++;
              result.add(Op.PUSH_BACK);
              break;
            }

            case 1: {
              if (sz > 0) {
                result.add(Op.POP_FRONT);
                sz--;
              }
              break;
            }
          }
        }

        return result;
      }
    },

    ZIGZAG {
      @Override
      public List<Op> createOps(int length) {
        List<Op> result = newArrayList();

        for (int i = 0; i < length; ++i) {
          for (int j = i; j < length; ++ j) {
            result.add(Op.PUSH_BACK);
          }
          for (int j = i; j < length - 1; ++ j) {
            result.add(Op.POP_FRONT);
          }
        }

        return result;
      }
    };

    public abstract List<Op> createOps(int length);
  }

  public static void main(String[] args) {
    CaliperMain.main(QueueBenchmark.class, args);
  }
}
