package com.learn.bt;

import com.learn.bt.BinarySearchTree;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Comparator;

/**
 * <p>红黑树
 * 理解红黑树的红黑作用，首先需要了解BTREE
 * 红黑树等价于BTree中的2-4树
 */
public class RBTree<E> extends BalanceBinarySearchTree<E> {
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

    private Node<E> color(Node<E> node, boolean color) {
        ((RBTreeNode<E>) node).color = color;
        return node;
    }
    private Node<E> black(Node<E> node) {
        ((RBTreeNode<E>) node).color = BLACK;
        return node;
    }

    private Node<E> red(Node<E> node) {
        ((RBTreeNode<E>) node).color = RED;
        return node;
    }
    private boolean isRed(Node<E> node) {
        return ((RBTreeNode<E>) node).color == RED;
    }

    private boolean isBlack(Node<E> node) {
        return ((RBTreeNode<E>) node).color == BLACK;
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBTreeNode<E>) node).color;
    }

    @Override
    protected void afterAdd(Node<E> node) {
        //添加节点的12种情况
        Node<E> parent = node.parent;// RBTree和BinaryTree同包才行
        if (parent == null) {
            //根节点染成黑色
            black(node);
        }
        // 父节点是黑色直接返回
        if (isBlack(parent)) {
            return;
        }
        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterAdd(grand);
            return;
        }
        // 叔父节点不是红色
        if (parent.isLeft()) { // L
            if (node.isLeft()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeft()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }



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

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
