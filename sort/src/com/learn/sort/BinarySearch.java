package com.learn.sort;

import com.learn.tools.Integers;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        Integer[] array = Integers.ascOrder(1, 20);
        Integers.println(array);
        System.out.println(binarySearch(array, 9));
        Integer[] array2 = {2, 4, 8, 8, 8, 12, 14};
        Arrays.toString(array2);
        System.out.println(firstLargeVIndex(array2, 8));
    }

    public static int binarySearch(Integer[] array, int v) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 获取第一个大于待插入元素v的位置
     */
    public static int firstLargeVIndex(Integer[] array, int v) {
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
