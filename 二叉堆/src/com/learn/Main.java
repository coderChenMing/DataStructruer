package com.learn;

import com.learn.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }

    public static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(11);
        heap.add(111);
        heap.add(66);
        heap.add(99);
        heap.add(50);
        heap.add(20);
        heap.add(200);

        BinaryTrees.print(heap);

    }

    public static void test2() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(11);
        System.out.println(heap.remove());
        BinaryTrees.print(heap);

    }

    public static void test3() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(11);
        heap.add(111);
        heap.add(66);
        heap.add(99);
        heap.add(50);
        heap.add(20);
        heap.add(200);
        BinaryTrees.println(heap);
        System.out.println(heap.remove());
        BinaryTrees.print(heap);

    }
}
