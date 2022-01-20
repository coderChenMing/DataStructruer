package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 15:47:15:47
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type ArrayIntListTest.java
 * @desc
 * @date 2022/1/16 0016 15:47
 */
public class ArrayIntListTest {
    public static void main(String[] args) {
        ArrayIntList mylist = new ArrayIntList();
        mylist.add(0);
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        mylist.add(4);
        mylist.add(5);
        mylist.add(6);
        mylist.add(7);
        mylist.add(8);
        mylist.add(9);
        mylist.add(10);
        System.out.println(mylist);
        System.out.println(mylist.getElements().length);
    }
}
