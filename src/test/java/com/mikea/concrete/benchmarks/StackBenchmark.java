package com.mikea.concrete.benchmarks;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import com.mikea.concrete.Stack;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.mikea.concrete.Stack.newStack;

public class StackBenchmark extends SimpleBenchmark {
  @Param
  private Test test;

  @Param({"10", "100", /*"1000"*/})
  private int length;

  @Param
  private Implementation implementation;

  public void time(int reps) {
    for (int i = 0; i < reps; ++i) {
      test.rep(implementation, length);
    }
  }

  enum Implementation {
    LINKED_LIST, PSTACK, ARRAY_LIST
  }

  enum Test {
    PUSH_FRONT {
      @Override
      void rep(Implementation implementation, int length) {
        switch (implementation) {
          default:
            throw new IllegalArgumentException();

          case LINKED_LIST: {
            LinkedList<String> stack = newLinkedList();
            String object = "foo";
            for (int j = 0; j < length; ++j) {
              stack.add(object);
            }
            break;
          }

          case PSTACK: {
            Stack<String> stack = newStack();
            String object = "foo";
            for (int j = 0; j < length; ++j) {
              stack = stack.pushFront(object);
            }
            break;
          }

          case ARRAY_LIST: {
            ArrayList<String> stack = newArrayList();
            String object = "foo";
            for (int j = 0; j < length; ++j) {
              stack.add(object);
            }
            break;
          }

        }
      }
    },
    PUSH_ALL_POP_ALL {
      @Override
      void rep(Implementation implementation, int length) {
        switch (implementation) {
          default:
            throw new IllegalArgumentException();

          case LINKED_LIST: {
            LinkedList<String> stack = newLinkedList();
            String object = "foo";
            for (int j = 0; j < length; ++j) {
              stack.addFirst(object);
            }
            for (int j = 0; j < length; ++j) {
              stack.pop();
            }
            break;
          }

          case PSTACK: {
            Stack<String> stack = newStack();
            String object = "foo";
            for (int j = 0; j < length; ++j) {
              stack = stack.pushFront(object);
            }
            for (int j = 0; j < length; ++j) {
              stack = stack.popFront();
            }
            break;
          }

          case ARRAY_LIST: {
            ArrayList<String> stack = newArrayList();
            String object = "foo";
            for (int j = 0; j < length; ++j) {
              stack.add(object);
            }
            for (int j = 0; j < length; ++j) {
              stack.remove(stack.size() - 1);
            }
            break;
          }

        }
      }
    };

    abstract void rep(Implementation implementation, int length);
  }

  public static void main(String[] args) {
    Runner.main(StackBenchmark.class, args);
  }
}
