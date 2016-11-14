package com.mikea.concrete.impl;

import com.google.common.collect.Iterables;
import com.mikea.concrete.PArray;
import com.mikea.concrete.PStack;

import java.util.NoSuchElementException;

import static com.mikea.concrete.PStack.newStack;

public class BinaryArray<T> implements PArray<T> {
  private final int size;
  private final PStack<Node<T>> nodes;

  private BinaryArray() {
    this(newStack(), 0);
  }

  private BinaryArray(PStack<Node<T>> nodes, int size) {
    this.nodes = nodes;
    this.size = size;
  }

  public static <T> BinaryArray<T> newBinaryArray() {
    return new BinaryArray<>();
  }

  @Override
  public PArray<T> pushFront(T t) {
    Node<T> tree = new Leaf<>(t);
    if (isEmpty()) {
      return new BinaryArray<>(newStack(tree), 1);
    }

    PStack<Node<T>> ptr = nodes;
    PStack<Node<T>> front = newStack();
    while (!ptr.isEmpty() && ptr.peekFront() != null) {
      Node<T> n = ptr.peekFront();
      tree = new Tree<>(tree, n);
      ptr = ptr.popFront();
      front = front.pushFront(null);
    }

    if (!ptr.isEmpty()) {
      // will replace this null
      ptr = ptr.popFront();
    }

    return new BinaryArray<>(front.append(ptr.pushFront(tree)), size + 1);
  }

  @Override
  public T peekFront() {
    if (isEmpty()) return null;
    PStack<Node<T>> ptr = nodes;
    while (ptr.peekFront() == null) {
      ptr = ptr.popFront();
    }
    return ptr.peekFront().leftMostLeaf();
  }

  @Override
  public PArray<T> popFront() {
    if (isEmpty()) throw new NoSuchElementException();

    PStack<Node<T>> ptr = nodes;
    int frontLen = 0;

    while (ptr.peekFront() == null) {
      ptr = ptr.popFront();
      frontLen++;
    }

    Node<T> node = ptr.peekFront();
    PStack<Node<T>> tail = ptr.popFront();
    PStack<Node<T>> front = newStack();
    for (int i = 0; i < frontLen; ++i) {
      Tree<T> tree = (Tree<T>) node;
      front = front.pushFront(tree.right);
      node = tree.left;
    }

    if (tail.isEmpty()) {
      return new BinaryArray<>(front, size - 1);
    }

    return new BinaryArray<>(front.append(tail.pushFront(null)), size - 1);
  }

  @Override
  public T get(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public PArray<T> set(int index, T t) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEmpty() {
    return nodes.isEmpty();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public PArray<T> clear() {
    return newBinaryArray();
  }

  @Override
  public String toString() {
    return Iterables.toString(this);
  }

  private static abstract class Node<T> {
    // todo: kill
    public abstract T leftMostLeaf();
  }

  private static class Leaf<T> extends Node<T> {
    private final T t;

    private Leaf(T t) {
      this.t = t;
    }

    @Override
    public String toString() {
      return String.valueOf(t);
    }

    @Override
    public T leftMostLeaf() {
      return t;
    }
  }

  private static class Tree<T> extends Node<T> {
    private final Node<T> left;
    private final Node<T> right;

    private Tree(Node<T> left, Node<T> right) {

      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return String.format("(%s, %s)", left, right);
    }

    @Override
    public T leftMostLeaf() {
      return left.leftMostLeaf();
    }
  }
}
