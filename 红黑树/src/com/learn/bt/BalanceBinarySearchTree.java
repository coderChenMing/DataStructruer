package com.learn.bt;

import java.util.Comparator;

public class BalanceBinarySearchTree<E> extends BinarySearchTree<E> {

    public BalanceBinarySearchTree() {
        this(null);
    }

    public BalanceBinarySearchTree(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateRight(Node<E> grand) {
        //由于确定向右旋转,G P N 的关系已经确定
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        Node<E> node = parent.left;

        // 1.更新 G P节点间关系
        grand.left = child;
        parent.right = grand;
        // 3.更新高度:先更新G的高度,再更新P的高度
        afterRotate(grand, parent, child);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 2.更新G P及P的原right子节点的parent
        // 2.1 原G.parent作为P.parent
        parent.parent = grand.parent;
        if (grand.isLeft()) {
            grand.parent.left = parent;
        } else if (grand.isRight()) {
            grand.parent.right = parent;
        } else {
            //grand为根节点.grand.parent==null
            root = parent;
        }
        // 2.2更新原P.child.parent
        if (null != child) {
            child.parent = grand;
        }
        // 2.3更新G.parent
        grand.parent = parent;
    }
}
