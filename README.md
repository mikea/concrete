# Concrete
> [Fully persistent](https://en.wikipedia.org/wiki/Persistent_data_structure) data structures in Java.


[![CircleCI](https://img.shields.io/circleci/project/mikea/concrete.svg?style=flat)](https://circleci.com/gh/mikea/concrete)


Implementation follows "Purely Functional Data Structures" by Chris Okasaki unless said otherwise.

## PStack

O(1) operations: pushFront, peekFront, popFront, size, isEmpty.
O(N) operations: reverse.

```java
PStack<String> stack = PStack.newStack();
stack = stack.pushFront("a");
assertEquals("a", stack.peekFront());
stack = stack.popFront();
assertTrue(stack.isEmpty());
```

PStack runtime performance is always better than ArrayList and LinkedList (ignoring GC overhead): 
[benchmark report](https://microbenchmarks.appspot.com/runs/eba86353-07d3-4db3-87e5-875ec5d373eb#r:scenario.benchmarkSpec.parameters.implementation&c:scenario.benchmarkSpec.parameters.test,scenario.benchmarkSpec.parameters.length),
[code](https://github.com/mikea/concrete/blob/master/src/test/java/com/mikea/concrete/benchmarks/StackBenchmark.java).

Stacks support constant append operation:

```java
PStack<String> stack1 = PStack.newStack("a", "b");
PStack<String> stack2 = PStack.newStack("c", "d");
assertEquals("[a, b, c, d]", stack1.append(stack2).toString());
```

## PQueue

O(1) operations: pushBack, popFront, peekFront, size, isEmpty

Queue with [amortized](https://en.wikipedia.org/wiki/Amortized_analysis) O(1) performance:
 
```java
PQueue<String> queue = PQueue.newAmortizedQueue();
queue = queue.pushBack("a");
queue = queue.pushBackAll("b", "c");
assertEquals("a", queue.peekFront());
queue = queue.popFront();
assertEquals("b", queue.peekFront());
queue = queue.popFront();
assertEquals("[c]", queue.toString());
```
 
Realtime queue with guaranteed O(1) performance: 

```java
PQueue<String> queue = PQueue.newRealtimeQueue();
queue = queue.pushBack("a");
queue = queue.pushBackAll("b", "c");
assertEquals("a", queue.peekFront());
queue = queue.popFront();
assertEquals("b", queue.peekFront());
queue = queue.popFront();
assertEquals("[c]", queue.toString());
```

## PDeque

O(1) operations: pushFront, popFront, peekFront, pushBack, peekBack, peekFront, size, isEmpty.

Deque with [amortized](https://en.wikipedia.org/wiki/Amortized_analysis) O(1) performance:

```java
PDeque<String> deque = PDeque.newAmortizedDeque();
deque = deque.pushBackAll("b", "c");
deque = deque.pushFront("a");
deque = deque.popBack();
assertEquals("[a, b]", deque.toString());
```

## PArray

Random-access array.

O(log N) operations: pushFront, popFront, peekFront, set, get.

```java
PArray<String> array = PArray.newBinaryArray();
array = array.pushFrontAll("a", "b", "c");
assertEquals("b", array.get(1));
array = array.set(1, "X");
assertEquals("[a, X, c]", array.toString());
```

### Base Classes

- PIterable with O(1) operations: popFront, peekFront, isEmpty.
- PCollection with O(1) operations: isEmpty, clear, size.