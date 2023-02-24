package com.learn;

import com.learn.printer.BinaryTreeInfo;

/**
 * 二叉搜索树:
 * 任意节点的左子树所有节点值小于当前节点值，右子树所有节点值大于当前节点值
 * 节点必须是可以比较的
 * 应用：可以思考下，mysql数据库按照某个字段进行排序查询的过程
 */
public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加元素
    public void add(E element) {
        checkElement(element);
        // 添加根节点
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }
        // 添加其他节点
        Node<E> parent = root;
        Node<E> node = root;
        int result = 0;
        while (node != null) {
            result = this.compare(element, node.element);
            parent = node;
            if (result > 0) {
                node = node.right;
            } else if (result < 0) {
                node = node.left;
            } else {
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        if (result > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 比较逻辑 : result >0 :e1> e2;
     * result=0 :e1=e2
     * result<0 : e1<e2
     */
    private int compare(E e1, E e2) {
        return e1.compareTo(e2);
    }

    private void checkElement(E element) {
        if (null == element) {
            throw new IllegalArgumentException("调用 add 函数参数有误!!");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    private static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
