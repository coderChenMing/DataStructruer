package com.learn.printer;

import com.learn.AVLTree;
import com.learn.Person;

public class AVLTreeTest {
    private static Integer data[] = new Integer[]{
            7, 4, 9, 2, 5, 8, 11, 3, 12, 1
    };

    private static Person[] people = {
            new Person(7, "吕布"),
            new Person(4, "赵云"),
            new Person(9, "典韦"),
            new Person(2, "关羽"),
            new Person(5, "马超"),
            new Person(8, "张飞"),
            new Person(11, "黄忠"),
            new Person(3, "许褚"),
            new Person(12, "张郃"),
            new Person(1, "张辽"),
            new Person(12, "徐晃")
    };
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        //System.out.println(bst.size());
        BinaryTrees.println(bst);
        //Files.writeToFile("E:\\remove.txt",BinaryTrees.printString(bst));
    }
}
