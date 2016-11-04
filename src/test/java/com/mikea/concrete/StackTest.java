package com.mikea.concrete;

import com.google.common.collect.Iterables;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.mikea.concrete.Stack.newStack;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testEmptyStack() throws Exception {
        Stack<String> stack = newStack();
        assertTrue(stack.isEmpty());
        assertEquals("[]", Iterables.toString(stack));

        assertNull(stack.peekFront());

        try {
            stack.popFront();
            fail();
        } catch (NoSuchElementException e) {
            // expected;
        }
    }

    @Test
    public void testIterator() throws Exception {
        Stack<String> stack = newStack();
        stack = stack.pushFront("a");
        stack = stack.pushFront("b");
        stack = stack.pushFront("c");
        stack = stack.pushFront("d");
        stack = stack.pushFront("e");

        assertFalse(stack.isEmpty());
        assertEquals("[e, d, c, b, a]", Iterables.toString(stack));
    }

    @Test
    public void testPopAndPeek() throws Exception {
        Stack<String> stack = newStack();
        assertTrue(stack.isEmpty());

        stack = stack.pushFront("a");
        assertEquals("a", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.pushFront("b");
        assertEquals("b", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.pushFront("c");
        assertEquals("c", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.pushFront("d");
        assertEquals("d", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.pushFront("e");
        assertEquals("e", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.popFront();
        assertEquals("d", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.popFront();
        assertEquals("c", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.popFront();
        assertEquals("b", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.popFront();
        assertEquals("a", stack.peekFront());
        assertFalse(stack.isEmpty());

        stack = stack.popFront();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testEmptyStackIterator() throws Exception {
        Stack<String> stack = newStack();
        Iterator<String> iterator = stack.iterator();
        assertFalse(iterator.hasNext());

        try {
            iterator.next();
            fail();
        } catch (NoSuchElementException e) {
            // expected
        }
        try {
            iterator.remove();
            fail();
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    @Test
    public void testReverse() throws Exception {
        Stack<String> stack = newStack();
        stack = stack.pushFront("a");
        stack = stack.pushFront("b");
        stack = stack.pushFront("c");
        stack = stack.pushFront("d");
        stack = stack.pushFront("e");

        assertEquals("[e, d, c, b, a]", Iterables.toString(stack));
        assertEquals("[a, b, c, d, e]", Iterables.toString(stack.reverse()));
    }

    @Test
    public void testClear() throws Exception {
        Stack<String> stack = newStack();
        stack = stack.pushFront("a");

        assertEquals(1, stack.size());

        stack = stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testToString() throws Exception {
        Stack<String> stack = newStack();
        stack = stack.pushFront("a");
        stack = stack.pushFront("b");
        stack = stack.pushFront("c");
        stack = stack.pushFront("d");
        stack = stack.pushFront("e");

        assertEquals("[e, d, c, b, a]", stack.toString());
    }
}
