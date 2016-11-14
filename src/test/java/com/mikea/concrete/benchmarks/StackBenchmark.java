package com.mikea.concrete.benchmarks;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.VmOptions;
import com.google.caliper.runner.CaliperMain;
import com.mikea.concrete.PStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.mikea.concrete.impl.Stack.newStack;

// bug in caliper: https://stackoverflow.com/questions/29199509/caliper-error-cicompilercount-of-1-is-invalid-must-be-at-least-2
@VmOptions("-XX:-TieredCompilation")
public class StackBenchmark {
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
      ArrayList<Object> arrayList = newArrayList();
      LinkedList<Object> linkedList = newLinkedList();
      PStack<Object> stack = newStack();

      for (Op op : ops) {
        switch (op) {
          case PUSH_FRONT:
            switch (implementation) {
              case LINKED_LIST:
                linkedList.addFirst(o);
                break;
              case PSTACK:
                stack = stack.pushFront(o);
                break;
              case ARRAY_LIST:
                arrayList.add(o);
                break;
            }
            break;
          case POP_FRONT:
            switch (implementation) {
              case LINKED_LIST:
                linkedList.removeFirst();
                break;
              case PSTACK:
                stack = stack.popFront();
                break;
              case ARRAY_LIST:
                arrayList.remove(arrayList.size() - 1);
                break;
            }
            break;
        }
      }
    }
  }

  enum Implementation {
    LINKED_LIST, PSTACK, ARRAY_LIST
  }

  enum Op {
    PUSH_FRONT, POP_FRONT
  }

  @SuppressWarnings("UnusedDeclaration")
  enum Test {
    PUSH_FRONT {
      @Override
      List<Op> createOps(int length) {
        List<Op> result = newArrayList();
        for (int i = 0; i < length; ++i) {
          result.add(Op.PUSH_FRONT);
        }
        return result;
      }
    },

    PUSH_ALL_POP_ALL {
      @Override
      List<Op> createOps(int length) {
        List<Op> result = newArrayList();
        for (int i = 0; i < length; ++i) {
          result.add(Op.PUSH_FRONT);
        }
        for (int i = 0; i < length; ++i) {
          result.add(Op.POP_FRONT);
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
              result.add(Op.PUSH_FRONT);
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
      List<Op> createOps(int length) {
        List<Op> result = newArrayList();

        for (int i = 0; i < length; ++i) {
          for (int j = i; j < length; ++ j) {
            result.add(Op.PUSH_FRONT);
          }
          for (int j = i; j < length - 1; ++ j) {
            result.add(Op.POP_FRONT);
          }
        }

        return result;
      }
    };

    abstract List<Op> createOps(int length);
  }

  public static void main(String[] args) {
    CaliperMain.main(StackBenchmark.class, args);
  }
}
