package com.mikea.concrete;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for examples in README
 */
public class ExamplesTest {
    @Test
    public void stack() {
        PStack<String> stack = PStack.newStack();
        stack = stack.pushFront("a");
        assertEquals("a", stack.peekFront());
        stack = stack.popFront();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void stackAppend() {
        PStack<String> stack1 = PStack.newStack("a", "b");
        PStack<String> stack2 = PStack.newStack("c", "d");
        assertEquals("[a, b, c, d]", stack1.append(stack2).toString());
    }

    @Test
    public void amortizedQueue() {
        PQueue<String> queue = PQueue.newAmortizedQueue();
        queue = queue.pushBack("a");
        queue = queue.pushBackAll("b", "c");
        assertEquals("a", queue.peekFront());
        queue = queue.popFront();
        assertEquals("b", queue.peekFront());
        queue = queue.popFront();
        assertEquals("[c]", queue.toString());
    }

    @Test
    public void realtimeQueue() {
        PQueue<String> queue = PQueue.newRealtimeQueue();
        queue = queue.pushBack("a");
        queue = queue.pushBackAll("b", "c");
        assertEquals("a", queue.peekFront());
        queue = queue.popFront();
        assertEquals("b", queue.peekFront());
        queue = queue.popFront();
        assertEquals("[c]", queue.toString());
    }

    @Test
    public void amortizedDeque() {
        PDeque<String> deque = PDeque.newAmortizedDeque();
        deque = deque.pushBackAll("b", "c");
        deque = deque.pushFront("a");
        deque = deque.popBack();
        assertEquals("[a, b]", deque.toString());
    }
}
