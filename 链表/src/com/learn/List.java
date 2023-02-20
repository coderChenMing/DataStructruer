package com.learn;

public interface List<E> {
    int DEFAULT_CAPACITY = 10;
    int ELEMENT_NOT_FOUND = -1;

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    int indexOf(E element);

    void add(int index, E element);

    void add(E element);

    E remove(int index);

    void remove(E element);

    E get(int index);

    E set(int index, E element);
}
