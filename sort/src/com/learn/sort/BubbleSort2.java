package com.learn.sort;

public class BubbleSort2 extends Sort {
    @Override
    protected void sort() {
        /*// 如果数组完全有序,可以提前终止循环
        for (int end = array.length - 1; end > 0; end--) {
            boolean isOrder = true;// 默认有序
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    // 完全有序则，不会进入该条件
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    isOrder = false;//只要有一次交换，说明无序
                    //有可能经过本次交换变的完全有序
                    //有可能经过本轮交换完全有序，注意本次和本轮有区别
                    // 这里的逻辑控制越来越复杂
                }
            }
            if (isOrder) {
                break;
            }
        }*/
        // 如果数组完全有序,可以提前终止循环
        for (int end = array.length - 1; end > 0; end--) {
            boolean isOrder = true;// 默认有序
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    // 完全有序则，不会进入该条件
                    swap(begin, begin - 1);
                    isOrder = false;//只要有一次交换，说明无序
                    //有可能经过本次交换变的完全有序
                    //有可能经过本轮交换完全有序，注意本次和本轮有区别
                    // 这里的逻辑控制越来越复杂
                }
            }
            if (isOrder) {
                break;
            }
        }
    }
}
