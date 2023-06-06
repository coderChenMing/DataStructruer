package com.learn.bubble;

import com.learn.tools.Integers;
import com.learn.tools.Times;

public class BubbleSort {

    public static void bubbleSort1(Integer[] array) {
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

    public static void bubbleSort2(Integer[] array) {
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

    public static void bubbleSort3(Integer[] array) {
        // 数组在经历多次交换之后，尾部元素越来越有序，可以通过记录最后一次交换位置来控制循环比较范围
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

        }
    }

    public static void main(String[] args) {
        //Integer[] array = Integers.random(10000, 1, 100000);//无序
        //Integer[] array = Integers.ascOrder(1, 100000);//升序
        Integer[] array = Integers.tailAscOrder(1, 100000,1000);//升序
        //深度拷贝
        Integer[] array2 = Integers.copy(array);
        Integer[] array3 = Integers.copy(array);
        //Integers.println(array);
        Times.test("冒泡测试1", () -> {
            bubbleSort1(array);
        });
        Times.test("冒泡测试2", () -> {
            bubbleSort2(array2);
        });

        Times.test("冒泡测试3", () -> {
            bubbleSort3(array3);
        });
    }
}
