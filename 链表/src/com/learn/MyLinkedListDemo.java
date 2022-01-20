package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-17 21:41:21:41
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type MyLinkedListDemo.java
 * @desc
 * @date 2022/1/17 0017 21:41
 */
public class MyLinkedListDemo {
    public static void main(String[] args) {
       // testRemove();
        //testIndexOf();
        testSet();
    }

    public static void testRemove() {
        MyLinkedList<Person> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(new Person(11,"张三"));
        myLinkedList.add(new Person(21,"李四"));
        myLinkedList.add(new Person(31,"刘大"));
        myLinkedList.add(new Person(41,"王二"));
        Person person = myLinkedList.remove(1);
        System.out.println(person);
        System.out.println(myLinkedList);
    }

    public static void testIndexOf() {
        MyLinkedList<Person> myLinkedList = new MyLinkedList<>();
        Person zhangsan = new Person(11, "张三");
        myLinkedList.add(new Person(21,"李四"));
        myLinkedList.add(new Person(31,"刘大"));
        myLinkedList.add(new Person(41,"王二"));
        myLinkedList.add(zhangsan);
        System.out.println(myLinkedList.indexOf(zhangsan));
        System.out.println(myLinkedList);
    }

    public static void testSet() {
        MyLinkedList<Person> myLinkedList = new MyLinkedList<>();
        Person zhangsan = new Person(11, "张三");
        myLinkedList.add(new Person(21,"李四"));
        myLinkedList.add(new Person(31,"刘大"));
        myLinkedList.add(new Person(41,"王二"));
        myLinkedList.set(1, zhangsan);
        System.out.println(myLinkedList);
    }
}
