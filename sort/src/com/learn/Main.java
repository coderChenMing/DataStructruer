package com.learn;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {10, 9, 19, 28, 37, 56, 34};
        //self(array);
        //oneLoop(array);
        //multiLoop(array);
        //existSort(array);
        rememberLastExchange(array);
        System.out.println(Arrays.toString(array));
    }

    public static void rememberLastExchange(int[] array) {
        // 数组在经历多次交换之后，尾部元素越来越有序，可以通过记录最后一次交换位置来控制循环比较范围
        for (int end = array.length - 1; end > 0; end--) {
            int lastExchangeIndex = 1;
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

        }
    }

    public static void existSort(int[] array) {
        // 如果数组完全有序,可以提前终止循环
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
        }
    }

    public static void multiLoop(int[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                }
            }
        }
    }

    public static void oneLoop(int[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            if (array[begin - 1] > array[begin]) {
                int temp = array[begin - 1];
                array[begin - 1] = array[begin];
                array[begin] = temp;
            }
        }

    }

    // 这个根本就不是冒泡排序：i每递增一次，都要和1+1开始的，length-1结束的数据进行一次比较
    // O(n^2)
    // 仔细想想，其实效率比multiLoop还要高，因为这里做 j=i+1执行了n次，而multiLoop的begin-1 执行了n^2次
    public static void self(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }


    }

}
