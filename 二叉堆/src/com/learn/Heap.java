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
     * 先用最后一个元素替换堆顶,新的堆顶元素再进行下滤
     */
    E remove();

    /**
     * 替换堆顶元素
     */
    E replace(E e);
}
