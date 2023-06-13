package com.learn.sort;

import java.util.Comparator;

/**
 * 交换转为挪动
 */
public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {

    public InsertionSort2() {
        super();
    }

    public InsertionSort2(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int curr = begin;
            E ele = array[curr];// 先将待插入元素备份
            while (curr > 0 && cmpElements(ele, array[curr - 1]) < 0) {
                array[curr] = array[curr - 1];// 头部有序数据中比待插入元素大的,统一向尾部挪动一个位置
                curr--;
            }
            array[curr] = ele;//将带插入元素放到合适位置
        }
    }
}
