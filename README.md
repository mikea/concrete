# Concrete
> Fully persistent data structures in Java.


[![CircleCI](https://img.shields.io/circleci/project/mikea/concrete.svg?style=flat)](https://circleci.com/gh/mikea/concrete)




Stack
======

    PStack<String> stack = PStack.newStack();
    stack = stack.push("a");
    assertEquals("a", stack.peek());
    stack = stack.pop();
    assertTrue(stack.isEmpty());

`PStack` performs better (runtime without GC overhead) than `ArrayList` and `LinkedList`:  
([benchmark report](https://microbenchmarks.appspot.com/runs/eba86353-07d3-4db3-87e5-875ec5d373eb#r:scenario.benchmarkSpec.parameters.implementation&c:scenario.benchmarkSpec.parameters.test,scenario.benchmarkSpec.parameters.length),
[code](https://github.com/mikea/concrete/blob/master/src/test/java/com/mikea/concrete/benchmarks/StackBenchmark.java))

Queue
======

    RealtimeQueue<String> queue = RealtimeQueue.newRealtimeQueue();
    queue = queue.push("a");
    queue = queue.push("b");
    assertEquals("a", queue.peek());
    queue = queue.pop();
    assertEquals("b", queue.peek());
    queue = queue.pop();
    assertTrue(queue.isEmpty());
