package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-17 21:22:21:22
 * {@link }
 * 索引值和size息息相关,linkedList有size,索引就可以使用
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type MyLinkedList.java
 * @desc
 * @date 2022/1/17 0017 21:22
 */
public class MyLinkedList<E> extends AbstractGenericList<E> {

    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public E get(int index) {
        // 先获取节点,再获取元素
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        //设置节点新元素值
        node.element = element;
        return old;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }


    @Override
    public void add(int index, E element) {
        if (0 == index) {
            first = new Node<>(element, first);
        } else {
            // 获取旧节点前一个节点
            Node<E> oldPre = node(index - 1);
            // 创建新节点,并且旧节点的前置节点指向新节点,新节点的后继节点指向旧节点:node(index)
            // oldPre.next = new Node<>(element, node(index));
            oldPre.next = new Node<>(element, oldPre.next);
        }
        size++;
    }

    /**
     * 获取指定索引的节点
     * index = 0  返回 first
     * index = 1 返回 first.next
     * index = 2 返回 first.next.next
     * ...
     * index = n first.next执行n次
     * index等于多少,执行多少次
     */
    private Node<E> node(int index) {
        checkIndex(index);
        Node<E> node = first;
        // 调用next的次数 index==0时,循环不执行
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E remove(int index) {
        // 删除过程: 1->2->3 --> 1->3
       /* E old = null;
        if (0 == index) {
            old = first.element;
            first = first.next;
        } else {
            Node<E> pre = node(index - 1);
            old = pre.next.element;
            pre.next = pre.next.next;
        }*/
        // 优化版
        Node<E> node = first;
        if (0 == index) {
            first = first.next;
        } else {
            Node<E> pre = node(index - 1);
            node = pre.next;
            pre.next = node.next;
        }
        size--;
        return node.element;
    }

    @Override
    public E remove(E element) {
        // 获取元素索引
        int index = indexOf(element);
        return remove(index);
    }

    @Override
    public int indexOf(E element) {
        /*Node<E> node = null;
        E e = null;
        if (null == element) {
            for (int i = 0; i < size; i++) {
                node = first.next;
                e = node.element;
                if (e == null) {
                    return i;
                }
                first = node;
            }
        } else {
            for (int i = 0; i < size; i++) {
                node = first.next;
                e = node.element;
                if (element.equals(e)) {
                    return i;
                }
                first = node;
            }
        }*/
        //优化版
        Node<E> node = first;
        if (null == element) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean contains(E element) {
        return ELEMENT_NOT_FOUND != indexOf(element);
    }

    @Override
    public void clear() {
        // 头节点指向null,其后节点依次被gc
        first = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" { size = [")
                .append(size).append("] ,")
                .append(" elements = [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(" , ");
            }
            sb.append(node.element);
            node = node.next;
        }
        sb.append("] }");
        return sb.toString();
    }
}
