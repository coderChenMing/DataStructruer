package com.learn.sort;

import java.util.Comparator;

/**
 * 插入排序:类似抓扑克牌,分成两部分,一部分有序,一部分无序而进行插入排序
 * 稳定算法:<  不稳定:<=
 * 逆序对越大,复杂度越高
 * 交换次数:(n^2)/2 三角形面积
 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {

    public InsertionSort() {
        super();
    }

    public InsertionSort(Comparator<E> comparator) {
        super(comparator);
    }
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            // 拿到当前值向前比较,每次索引递减1,直到到达第一个元素和第二个元素比较
            int curr = begin;//保留begin不变,可以在外面循环,由curr完成比较交换动作
            while (curr > 0 && cmp(curr, curr - 1) < 0) {
                /* while (cmp(curr, curr - 1) < 0&& curr<0) {  curr<0 放在后面会报错*/
                /*E temp = array[begin];
                array[begin] = array[begin - 1];
                array[begin - 1] = temp;*/
                swap(curr, curr - 1);
                curr--;
            }
        }
    }
}
