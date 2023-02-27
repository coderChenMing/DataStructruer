package com.learn;


import com.learn.queue.list.LinkedList;
import com.learn.queue.list.List;
import com.learn.queue.list.Person;

/**
 * 队列：first in first out 先进先出
 * 队尾入队，队头出队
 * 链表和数组都可实现
 * 数组队头出队，后面元素向前移动，时间复杂度相对较大o(n),而链表删除队头时间复杂度o(1)
 */
public class Queue<E> {
    private final List<E> list = new LinkedList<>();


    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 队尾入队
    public void enQueue(E element) {
        list.add(element);
    }

    // 队头出队
    public E deQueue() {
        return list.remove(0);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        Queue<Person> queue = new Queue<>();
        queue.enQueue(new Person(500, "孙悟空"));
        queue.enQueue(new Person(500, "猪八戒"));
        queue.enQueue(new Person(500, "唐三藏"));
        System.out.println(queue);
        System.out.println("=======================================================");

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }
}
