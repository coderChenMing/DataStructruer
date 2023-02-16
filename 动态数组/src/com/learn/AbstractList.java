package com.learn;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    protected void check4Add(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引参数非法");
        }
    }

    protected void check4Index(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引参数非法");
        }
    }
}
