package com.learn.sort;

/**
 * 最好时间复杂度o(n)
 * <p>
 * 最坏时间复杂度o(n^2)
 * 空间复杂度o(1) 稳定排序 in place
 */
public class BubbleSort3 extends Sort {
    @Override
    protected void sort() {
        /*// 数组在经历多次交换之后，尾部元素越来越有序，可以通过记录最后一次交换位置来控制循环比较范围
        for (int end = array.length - 1; end > 0; end--) {
            int lastExchangeIndex = 1;//如果数组完全有序,可以一轮退出
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    // 完全有序则，不会进入该条件
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    //每交换一次更新一次 交换位置
                    lastExchangeIndex = begin;
                }
            }
            end = lastExchangeIndex;

        }*/
        // 数组在经历多次交换之后，尾部元素越来越有序，可以通过记录最后一次交换位置来控制循环比较范围
        for (int end = array.length - 1; end > 0; end--) {
            int lastExchangeIndex = 1;//如果数组完全有序,可以一轮退出
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin - 1, begin) < 0) {
                    // 完全有序则，不会进入该条件
                    swap(begin - 1, begin);
                    //每交换一次更新一次 交换位置
                    lastExchangeIndex = begin;
                }
            }
            end = lastExchangeIndex;

        }
    }
}
