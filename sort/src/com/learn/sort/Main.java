package com.learn.sort;

import com.learn.tools.Integers;
import com.learn.tools.Times;

public class Main {
    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 20000);
        //test1(array);
        testSorts(array, new HeapSort(), new SelectionSort(), new BubbleSort3());
    }

    public static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
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
