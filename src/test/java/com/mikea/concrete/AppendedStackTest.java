package com.mikea.concrete;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppendedStackTest {
    @Test
    public void testAppend() {
        PStack<String> stack1 = PStack.newStack("1", "2", "3");
        PStack<String> stack2 = PStack.newStack("4", "5", "6");
        PStack<String> s = stack1.append(stack2);

        assertEquals("[1, 2, 3, 4, 5, 6]", s.toString());
        assertEquals(6, s.size());
    }

    @Test
    public void testClear() {
        PStack<String> s = PStack.newStack("1", "2", "3").append(
            PStack.newStack("4", "5", "6"));

        s = s.clear();
        assertEquals("[]", s.toString());
        assertEquals(0, s.size());
    }

    @Test
    public void testPop() {
        PStack<String> s = PStack.newStack("1", "2", "3").append(
            PStack.newStack("4", "5", "6"));

        assertEquals("[1, 2, 3, 4, 5, 6]", s.toString());
        assertEquals(6, s.size());
        s = s.popFront();
        assertEquals("[2, 3, 4, 5, 6]", s.toString());
        assertEquals(5, s.size());
        s = s.popFront();
        assertEquals("[3, 4, 5, 6]", s.toString());
        assertEquals(4, s.size());
        s = s.popFront();
        assertEquals("[4, 5, 6]", s.toString());
        assertEquals(3, s.size());
        s = s.popFront();
        assertEquals("[5, 6]", s.toString());
        assertEquals(2, s.size());
        s = s.popFront();
        assertEquals("[6]", s.toString());
        assertEquals(1, s.size());
        s = s.popFront();
        assertEquals("[]", s.toString());
        assertEquals(0, s.size());
    }
}
