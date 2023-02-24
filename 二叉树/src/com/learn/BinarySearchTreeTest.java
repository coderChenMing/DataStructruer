package com.learn;

import com.learn.printer.BinaryTrees;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(7);
        bst.add(4);
        bst.add(9);
        bst.add(2);
        bst.add(5);
        bst.add(8);
        bst.add(11);
        bst.add(1);
        bst.add(3);
        bst.add(10);
        bst.add(12);

        System.out.println(bst.size());
        BinaryTrees.println(bst);

    }
}
