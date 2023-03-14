package com.learn;

import com.learn.printer.BinaryTrees;

public class AVLTreeTest {
    private static Integer data[] = new Integer[]{
            67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
    };

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
            avl.add(data[i]);
            BinaryTrees.println(avl);
            System.out.println("---------------------------------------------------------------------------------------------------");
        }
    }
}
