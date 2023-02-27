package com.learn;

import com.learn.stack.list.Person;

/**
 * 通过链表实现栈
 */
public class StackByList<E> {

    private int size;
    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E element) {
        // 尾部添加
        if (first == null) {
            first = new Node<>(element);
            size++;
            return;
        }

        // 添加第二个节点,获取第三个节点，还得next.next,因为是向尾部添加。这是个循环
        //node.next = new Node<>(element);
        // 获取最后一个节点
       /* Node<E> lastNode = getLast(first);
        lastNode.next = new Node<>(element);*/
        node(size - 1).next = new Node<>(element);
        size++;
    }

    public E pop() {
        // 删除最后一个节点
        Node<E> node = node(size - 1);
        // getLast方法只能获取最后一个节点,无法获取倒数第二个节点,也就无法清除索引访问不到的数据，这样会有内存泄露
        node(size - 2).next = null;
        size--;
        return node.element;
    }


    /**
     * 计数索引方式方便获取任意位置元素
     */
    private Node<E> node(int index) {
        Node<E> node = first;
        /*
        // 第一种循环
        for (int i = 0; i < index; i++) {

            // System.out.println(i);// size= 5 size-1=4 打印 0,1,2,3
        }*/

       /*
       // 第二种循环
       while (index-- > 0) {
            node = node.next;
        }*/

        // 第三种循环
        while (index > 0) {
            node = node.next;
            index--;
        }
        return node;
    }

    /**
     * 获取最后一个节点
     */
    private Node<E> getLast(Node<E> first) {
        Node<E> node = first;
        Node<E> lastNode = first;
        while (node != null) {
            // node==null时结束循环
            lastNode = node;
            node = node.next;
        }
        return lastNode;
    }

    public E peek() {
        // 返回最后一个节点存储元素
        return getLast(first).element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = [ ").append(size).append(" ] StackByList = [ ");
        Node<E> node = first;
        while (node != null) {
            if (node.next != null) {
                sb.append(node.element).append(" , ");
            } else {
                sb.append(node.element);
            }
            node = node.next;
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        StackByList<Person> stack = new StackByList<>();
        stack.push(new Person(12, "张三"));
        stack.push(new Person(22, "李四"));
        stack.push(new Person(33, "王五"));
        stack.push(new Person(44, "刘大"));
        stack.push(new Person(55, "田七"));
        System.out.println(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.gc();
    }
}
