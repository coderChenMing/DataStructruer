package com.learn;

import java.util.Comparator;

/**
 * <p>平衡二叉搜索树
 * 尽可能少的移动实现二叉树的平衡,保证平均复杂度在O(log(n))
 * 平衡二叉搜索树是二叉搜索树的一种
 * 平衡时机:二叉搜索树完成添加节点之后
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 对二叉搜索树进行平衡
     * 步骤:
     * 1.首先向上寻找在添加节点后失衡的第一个祖父节点
     * 2.分析该失衡祖父节点属于LL,LR,RR,RL的哪种类型,进行单旋或或者多旋,完成平衡
     * 3.一旦失衡的祖父节点完成平衡,由改祖父节点向上的所有祖父节点全部恢复平衡
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断节点是否平衡:获取当前节点平衡因子（左子树高度-右子树高度的绝对值）是否小于等于1
            if (isBalance(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //如果不平衡就进行平衡
                reBalance(node);
                break;//一旦失衡的祖父节点完成平衡,由改祖父节点向上的所有祖父节点全部恢复平衡,所以不必再循环下去
            }
        }
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private void reBalance(Node<E> node) {

    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        private int balanceFactor() {
            // 获取左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        private void updateHeight() {
        }
    }
}
