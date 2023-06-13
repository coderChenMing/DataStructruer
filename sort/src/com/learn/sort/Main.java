package com.learn.sort;

import com.learn.tools.Integers;
import com.learn.tools.Times;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = Integers.random(1000, 1, 2000);
        //test1(array);
        testSorts(array, new InsertionSort(), new InsertionSort2(), new InsertionSort3(), new HeapSort(), new SelectionSort(), new BubbleSort3());
    }

    public static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
        }
        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

    public static void test1(Integer[] array) {
        Integer[] array2 = Integers.copy(array);
        Integer[] array3 = Integers.copy(array);
        Times.test("HeapSort", () -> {
            new HeapSort().sort(array);
        });
        Times.test("SelectionSort", () -> {
            new SelectionSort().sort(array2);
        });
        Times.test("BubbleSort3", () -> {
            new BubbleSort3().sort(array3);
        });
    }
}
