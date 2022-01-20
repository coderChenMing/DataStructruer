package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-17 21:04:21:04
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type List.java
 * @desc
 * @date 2022/1/17 0017 21:04
 */
public interface GenericList<E> {
    /**
     * 接口定义常量默认 static final
     */
    int ELEMENT_NOT_FOUND = -1;

    int size();

    boolean isEmpty();

    E get(int index);

    E set(int index, E element);

    void add(E element);

    void add(int index, E element);

    E remove(int index);

    E remove(E element);

    int indexOf(E element);

    boolean contains(E element);

    void clear();
}
