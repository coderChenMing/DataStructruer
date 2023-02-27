package com.learn;

import com.learn.list.Person;

/**
 * 用数组的方式实现一个栈
 * 栈：first int last out
 * 向最后size-1 添加元素，从index=0弹出元素
 */
public class StackByArray<E> {
    public static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public StackByArray(int capacity) {
        capacity = Math.min(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E e) {
        //需要考虑扩容的问题?
        //如果不扩容能否实现?
        this.ensureCapacity();
        elements[size] = e;
        size++;
    }

    /**
     * 扩容
     */
    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }
        int newCapacity = elements.length + (elements.length >> 1);
        E[] newEles = (E[]) new Object[newCapacity];
        System.out.println("size = " + size + "原数组容量:" + elements.length + "扩容为 " + newCapacity);
        System.arraycopy(elements, 0, newEles, 0, elements.length);
        elements = newEles;
    }

    public E pop() {
        if (size < 1) {
            throw new IllegalArgumentException("栈内无元素，不能pop");
        }
        //删除，删除数组最后一个元素，size--保证最后访问不到即可
        // 同时为了防止内存泄露，需要将原来 size-1 位置置空
        E oldEle = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return oldEle;
    }

    public E peek() {
        // 获取第一个元素
        return elements[size - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = [").append(size).append("] stackByArray = [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        StackByArray<Person> stack = new StackByArray<>(2);
        stack.push(new Person(60, "刘备"));
        stack.push(new Person(55, "关羽"));
        stack.push(new Person(54, "张飞"));
        stack.push(new Person(66, "曹操"));
        System.out.println(stack);
        System.out.println(stack.peek());

        System.out.println("====================================================================");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println(stack);
        System.gc();//查看pop后是否销毁了数组不能访问的元素
    }
}
