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


Queue
======

    Queue<String> queue = new Queue<String>();
    queue = queue.push("a");
    queue = queue.push("b");
    assertEquals("a", queue.peek());
    queue = queue.pop();
    assertEquals("b", queue.peek());
    queue = queue.pop();
    assertTrue(queue.isEmpty());