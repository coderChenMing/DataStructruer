package com.learn.sort;

/**
 * 最好最坏时间复杂度都是o(n^2)
 * 空间复杂度o(1),稳定排序,in place
 **/
public class SelectionSort extends Sort {
    @Override
    protected void sort() {
        /*for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin < end; begin++) {
                //if (array[maxIndex] < array[begin]) {
                // 为了保持排序的稳定性:对于相等元素的处理,排序之后依然保持排序之前相等元素的相对位置
                // 对于array[maxIndex] < array[begin] eg: 10 10 1 3 排序后: 3 10 1 10,显然两个10位置变了
                if (array[maxIndex] < array[begin]) {
                    maxIndex = begin;// 最大元素索引
                }
            }
            // 没循环一次交换一次
            int temp = array[maxIndex];
            array[maxIndex] = array[end];//end以1进行递减,满足每次交换都交换(上次交换得到最大元素的上一个元素)与之交换
            array[end] = temp;
        }*/
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin < end; begin++) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
