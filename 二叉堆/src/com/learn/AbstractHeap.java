package com.learn;

import java.util.Comparator;

public abstract class AbstractHeap<E> {
    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    protected int size() {
        return size;
    }

    protected boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 获取堆顶元素
     */
    protected abstract E get();

    /**
     * 添加元素
     */
    protected abstract void add(E e);

    /**
     * 删除堆顶元素
     */
    protected abstract void remove();

    /**
     * 替换堆顶元素
     */
    protected abstract E replace(E e);
}
