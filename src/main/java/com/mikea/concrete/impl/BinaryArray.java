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
    if (index >= size()) {
      throw new NoSuchElementException();
    }

    return lookup(nodes, index);
  }

  // todo: norecurse
  private T lookup(PStack<Node<T>> ptr, int index) {
    Node<T> node = ptr.peekFront();
    if (node == null) {
      return lookup(ptr.popFront(), index);
    } else {
      int sz = node.size();
      if (index < sz) {
        return lookupTree(node, index);
      } else {
        return lookup(ptr.popFront(), index - sz);
      }
    }
  }

  // todo: norecurse
  private T lookupTree(Node<T> node, int index) {
    if (node instanceof Leaf) {
      assert index == 0;
      return ((Leaf<T>) node).t;
    } else {
      Tree<T> tree = (Tree<T>) node;
      if (index < tree.size() / 2) {
        return lookupTree(tree.left, index);
      } else {
        return lookupTree(tree.right, index - tree.size() / 2);
      }
    }
  }

  @Override
  public PArray<T> set(int index, T t) throws IndexOutOfBoundsException {
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    return new BinaryArray<>(update(nodes, index, t), size);
  }

  // todo: norecurse
  private PStack<Node<T>> update(PStack<Node<T>> nodes, int index, T t) {
    Node<T> node = nodes.peekFront();
    PStack<Node<T>> tail = nodes.popFront();

    if (node == null) {
      return update(tail, index, t).pushFront(null);
    } else {
      int size = node.size();
      if (index < size) {
        return tail.pushFront(updateTree(node, index, t));
      } else {
        return update(tail, index - size, t).pushFront(node);
      }
    }
  }

  // todo: norecurse
  private Node<T> updateTree(Node<T> node, int index, T t) {
    assert index >= 0;

    if (node instanceof Leaf) {
      assert index == 0 : String.valueOf(index);
      return new Leaf<>(t);
    } else {
      Tree<T> tree = (Tree<T>) node;
      int size = tree.size();
      if (index < size / 2) {
        return new Tree<>(updateTree(tree.left, index, t), tree.right);
      } else {
        return new Tree<>(tree.left, updateTree(tree.right, index - size / 2, t));
      }
    }
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
    private final int size;

    Node(int size) {
      this.size = size;
    }

    // todo: kill
    public abstract T leftMostLeaf();

    public final int size() {
      return size;
    }
  }

  private static class Leaf<T> extends Node<T> {
    private final T t;

    private Leaf(T t) {
      super(1);
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
      super(left.size() + right.size());
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
