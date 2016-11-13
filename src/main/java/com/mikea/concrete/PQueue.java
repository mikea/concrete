package com.mikea.concrete;

import com.mikea.concrete.impl.AmortizedQueue;
import com.mikea.concrete.impl.RealtimeQueue;

import java.util.Iterator;

public interface PQueue<T> extends PCollection<T> {
    PQueue<T> pushBack(T value);

    PQueue<T> popFront();

    T peekFront();

    @Override
    PQueue<T> clear();

    default PQueue<T> pushBackAll(T... args) {
        PQueue<T> queue = this;
        for (T t : args) {
            queue = queue.pushBack(t);
        }
        return queue;
    }

    static PQueue<String> newAmortizedQueue() {
        return AmortizedQueue.newAmortizedQueue();
    }

    static PQueue<String> newRealtimeQueue() {
        return RealtimeQueue.newRealtimeQueue();
    }

    @Override
    default Iterator<T> iterator() {
        return new Iterator<T>() {
            private PQueue<T> ptr = PQueue.this;

            @Override
            public boolean hasNext() {
                return !ptr.isEmpty();
            }

            @Override
            public T next() {
                T result = ptr.peekFront();
                ptr = ptr.popFront();
                return result;
            }
        };
    }
}
