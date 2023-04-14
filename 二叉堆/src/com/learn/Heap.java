package com.learn;

public interface Heap<E> {
    int size();    // 元素的数量

    boolean isEmpty();    // 是否为空

    void clear();    // 清空

    /**
     * 获取堆顶元素
     */
    E get();

    /**
     * 添加元素
     */
    void add(E e);

    /**
     * 删除堆顶元素
     */
    void remove();

    /**
     * 替换堆顶元素
     */
    E replace(E e);
}
