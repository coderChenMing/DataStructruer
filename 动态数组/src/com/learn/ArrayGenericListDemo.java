package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 22:12:22:12
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type ArrayGenericListDemo.java
 * @desc
 * @date 2022/1/16 0016 22:12
 */
public class ArrayGenericListDemo {
    public static void main(String[] args) throws InterruptedException {
        //testClear();
        testIndexOf();

    }

    public static void testClear() throws InterruptedException {
        ArrayGenericList<Person> myList = new ArrayGenericList<>();
        myList.add(new Person(11,"张三"));
        myList.add(new Person(111,"李四"));
        myList.add(new Person(21,"王五"));
        myList.add(new Person(31,"何必"));
        System.out.println(myList);
        myList.clear();
        System.gc();
        Thread.sleep(5000);
        System.out.println(myList);
    }

    public static void testIndexOf() {
        ArrayGenericList<Person> myList = new ArrayGenericList<>();
        Person person = new Person(11, "张三");
        Person person1 = new Person(11, "张三");
        myList.add(person);
        int i = myList.indexOf(person);
        int j = myList.indexOf(person1);
        System.out.println(i);
        System.out.println(j);
    }
}
