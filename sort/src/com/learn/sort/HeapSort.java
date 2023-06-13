package com.learn.sort;

import java.util.Comparator;

/**
 * 堆排序是对选择排序的优化
 * 时间复杂度:n(原地建堆)+nlog(n)(循环执行操作)= nlog(n)
 * 空间复杂度o(1),不是稳定排序,属于in place 排序
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    public HeapSort() {
        super();
    }

    public HeapSort(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void sort() {
        // 原地建堆
        heapSize = array.length;
        // 自下而上的下滤进行原地建堆
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, --heapSize);
            // 对0位置进行siftDown（恢复堆的性质）
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E element = array[index];
        int original = index;
        int half = heapSize >> 1;
        while (index < half) { // index必须是非叶子节点
            // 默认是左边跟父节点比
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            int rightIndex = childIndex + 1;
            // 右子节点比左子节点大
            if (rightIndex < heapSize &&
                    cmp(rightIndex, childIndex) > 0) {
                child = array[childIndex = rightIndex];
            }

            // 大于等于子节点
            if (cmp(original, childIndex) >= 0) break;

            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
