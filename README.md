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
