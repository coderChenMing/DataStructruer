package com.learn;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

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
