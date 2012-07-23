package com.mikea.concrete;

public interface PDeque<T, Self extends PDeque> extends PQueue<T, Self> {
    Self pushFront(T value);
    Self popBack();
    T peekBack();
}
