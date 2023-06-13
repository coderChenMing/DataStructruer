package com.learn.sort;

import java.util.Comparator;

/**
 * 终极优化:对有序部分通过二分查找定位当前待插入元素要插入的位置
 */
public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
    public InsertionSort3() {
        super();
    }

    public InsertionSort3(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            E insertEle = array[begin];
            int insertIndex = binarySearch(begin);
            // 将insertIndex ~ begin范围元素进行挪动
            for (int i = begin - 1; i >= insertIndex; i--) {
                array[i + 1] = array[i];
            }
            /*for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }*/
            array[insertIndex] = insertEle;
        }
    }

    private int binarySearch(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmpElements(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
