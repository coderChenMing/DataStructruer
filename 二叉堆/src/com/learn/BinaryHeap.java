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
    public E get() {
        emptyCheck();
        return elements[0];
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is null");
        }
    }

    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E e) {
        elementCheck(e);
        //扩容
        ensureCapacity(size + 1);
        // 添加到二叉堆尾部,然后上滤
        elements[size++] = e;
        // 上滤最后一个元素
        // siftUp(size - 1);
        siftUp2(size - 1);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);// 扩容1.5倍
        if (capacity <= oldCapacity) return;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            newElements[i] = elements[i];
        }
        System.out.println(oldCapacity + "扩容为" + newCapacity);
        elements = newElements;
    }

    /**
     * 上滤
     */
    private void siftUp(int index) {
        // 要上滤的元素
        //E e = elements[index];
        while (index > 0) {// 一直上滤到堆顶
            // 获取父节点索引
            int parentIndex = (index - 1) >> 1;
            if (compare(elements[index], elements[parentIndex]) <= 0) return;
            // 如果要上滤的元素值比较大
            E temp = elements[parentIndex];
            elements[parentIndex] = elements[index];
            elements[index] = temp;
            index = parentIndex;
        }
    }


    /**
     * 上滤优化:相比siftUp方法
     * 循环内少执行了
     * elements[index] = parent;
     * index = parentIndex;
     * 循环外多执行了一次
     * elements[index] = now;
     * 相当于
     * siftUp:3倍的O(logN)
     * siftUp2: O(logN)+1
     */
    private void siftUp2(int index) {
        // 要上滤的元素
        E now = elements[index];
        while (index > 0) {// 一直上滤到堆顶
            // 获取父节点索引: (index-1)/2
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(now, parent) <= 0) break;
            // break:跳出当前循环,return:结束整个方法
            // 如果要上滤的元素值比较大
            elements[index] = parent;
            index = parentIndex;// index一直向堆顶替换
        }
        elements[index] = now;
    }

    private void elementCheck(E e) {
        if (null == e) {
            throw new IllegalArgumentException("param is null");
        }
    }

    @Override
    public E remove() {
        emptyCheck();
        E top = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;// 删除最后一个元素
        size--;
        // 堆顶下滤
        siftDown(0);
        return top;
    }

    private void siftDown(int index) {
        E now = elements[index];
        // 必须保证index位置是非叶子节点才能下滤,叶子节点下滤有什么意义
        // 第一个叶子节点索引 = 非叶子节点数量
        // index < 第一个叶子节点索引
        while (index < (size >> 1)) {
            // 获取左右子节点
            int left = (index << 1) + 1;
            E leftE = elements[left];
            int right = left + 1;
            E rightE = elements[right];
            if (right < size && compare(rightE, leftE) > 0) {
                left = right;
                leftE = rightE;
            }
            // 比较当前元素与左右子节点中比较大的一个
            if (compare(now, leftE) >= 0) break;
            elements[index] = leftE;
            index = left;
        }
        elements[index] = now;
    }

    /*private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
    }*/

    @Override
    public E replace(E e) {
        return null;
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DefaultCapacity];

    }


    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = (int) node;
        if (((index << 1) + 1) < size) {
            return (index << 1) + 1;
        } else {
            return null;
        }
    }

    @Override
    public Object right(Object node) {
        int index = (int) node;
        if (((index << 1) + 2) < size) {
            return (index << 1) + 2;
        } else {
            return null;
        }
    }

    @Override
    public Object string(Object node) {
        int index = (int) node;
        return elements[index];
    }
}
