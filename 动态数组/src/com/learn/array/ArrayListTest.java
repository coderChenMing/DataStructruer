package com.learn.array;

import com.learn.ArrayList;
import com.learn.Person;

public class ArrayListTest {
    private static final com.learn.ArrayList<Object> arrayList = new ArrayList<>();
    public static void main(String[] args) {
        peopleTest();
        //intTest();
    }

    public static void peopleTest() {
        arrayList.add(new Person(12, "张三"));
        arrayList.add(new Person(22, "李四"));
        arrayList.add(new Person(55, "王五"));
        arrayList.add(new Person(34, "何六"));
        arrayList.add(new Person(131, "钱七"));
        System.out.println("初始化: "+arrayList);
        arrayList.set(1,new Person(66, "孙权"));
        arrayList.add(0,new Person(56,"曹操"));
        System.out.println("set and add 后: "+arrayList);
        arrayList.remove(5);
        System.out.println("remove 后: "+arrayList);
        arrayList.clear();
        System.out.println("clear 后: "+arrayList);
        System.gc();
    }

    public static void intTest() {
        arrayList.add(0,10);
        arrayList.add(1,22);
        arrayList.add(0,33);
        arrayList.add(0,44);
        arrayList.add(0,55);
        arrayList.add(66);
        arrayList.add(88);
        arrayList.add(777);
        arrayList.add(555);
        arrayList.add(121);
        arrayList.add(232);
        arrayList.add(211);
        arrayList.add(2265);
        arrayList.add(9876);
        arrayList.add(89);
        arrayList.add(134);
        System.out.println("是否为空: "+arrayList.isEmpty());
        System.out.println("是否包含: "+arrayList.contains(66));
        System.out.println(arrayList.get(1));
        System.out.println("被替换的是："+arrayList.set(1, 111));
        System.out.println("替换后是: "+arrayList.get(1));
        System.out.println("size :"+arrayList.size());
        System.out.println("删除前:"+arrayList);
        arrayList.remove(arrayList.size()-1);
        System.out.println("删除后:"+arrayList);
    }
}
