package com.learn.array;

public class MyInterListTest {
    public static void main(String[] args) {

        MyInterList myInterList = new MyInterList(8);
        myInterList.add(0,10);
        myInterList.add(1,22);
        myInterList.add(0,33);
        myInterList.add(0,44);
        myInterList.add(0,55);
        myInterList.add(66);
        myInterList.add(88);
        myInterList.add(777);
        myInterList.add(555);
        myInterList.add(121);
        myInterList.add(232);
        myInterList.add(211);
        myInterList.add(2265);
        myInterList.add(9876);
        myInterList.add(89);
        myInterList.add(134);
        System.out.println("是否为空: "+myInterList.isEmpty());
        System.out.println("是否包含: "+myInterList.contains(66));
        System.out.println(myInterList.get(1));
        System.out.println("被替换的是："+myInterList.set(1, 111));
        System.out.println("替换后是: "+myInterList.get(1));
        System.out.println("size :"+myInterList.size());
        System.out.println("删除前:"+myInterList);
        myInterList.remove(myInterList.size()-1);
        System.out.println("删除后:"+myInterList);
        myInterList.clear();
        System.out.println("清空后"+myInterList);
    }
}
