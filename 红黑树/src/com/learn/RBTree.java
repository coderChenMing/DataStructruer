package com.learn;

import com.learn.bt.BinarySearchTree;

import java.util.Comparator;

/**
 * <p>红黑树
 * 理解红黑树的红黑作用，首先需要了解BTREE
 * 红黑树等价于BTree中的2-4树
 */
public class RBTree<E> extends BinarySearchTree<E> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBTreeNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }

    private static class RBTreeNode<E> extends Node<E> {
        boolean color = RED;

        /**
         * 除了根节点，任意节点的parent一定非空,而 左右节点 有可能是空的，比较适合提供如下实例方法
         *
         * @param element
         * @param parent
         */
        protected RBTreeNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
