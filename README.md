Concrete
========

Purely functional data structures in Java.


Stack
======

    Stack<String> stack = new Stack<String>();
    stack = stack.push("a");
    assertEquals("a", stack.peek());
    stack = stack.pop();
    assertTrue(stack.isEmpty());
