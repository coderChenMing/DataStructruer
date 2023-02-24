package com.learn;

import com.learn.list.ArrayList;
import com.learn.list.List;
/**
 * 栈特点:first in last out
 * 应用:浏览器网址前进后退
 * 实现方式：可以使用数组和链表实现
 * 以下是用数组实现栈功能的示例
 * */
public class Stack<E> {
    private final List<E> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
        // 0 1 2 3 4 5
    }

    public E pop() {
       return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size()-1);
    }
}
