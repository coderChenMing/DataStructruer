package com.learn;

import com.learn.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;

    private static final int DefaultCapacity = 10;


    public BinaryHeap() {
        this(null);
    }

    @Override
    protected E get() {
        return null;
    }

    @Override
    protected void add(E e) {

    }

    @Override
    protected void remove() {

    }

    @Override
    protected E replace(E e) {
        return null;
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DefaultCapacity];

    }



    @Override
    public Object root() {
        return null;
    }

    @Override
    public Object left(Object node) {
        return null;
    }

    @Override
    public Object right(Object node) {
        return null;
    }

    @Override
    public Object string(Object node) {
        return null;
    }
}
