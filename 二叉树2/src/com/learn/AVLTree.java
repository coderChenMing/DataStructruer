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
                System.out.println("当前树失衡,需要旋转");
                reBalance(node);
                System.out.println("当前树旋转后平衡");
                break;//一旦失衡的祖父节点完成平衡,由改祖父节点向上的所有祖父节点全部恢复平衡,所以不必再循环下去
            }
        }
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private void reBalance(Node<E> grand) {
        //根据平衡二叉搜索树的特点,根据失衡的祖父节点grand找到parent 和node
        // parent:grand的两个子节点中比较高的节点
        Node<E> parent = ((AVLNode<E>) grand).tallerNode();
        Node<E> node = ((AVLNode<E>) parent).tallerNode();

        if (parent.isLeft()) {
            if (node.isLeft()) {
                // LL:grand向右旋转
                this.rotateRight(grand);
            } else {
                //LR:parent先向左旋转,grand再向右旋转
                this.rotateLeft(parent);
                this.rotateRight(grand);
            }
        } else {
            if (node.isLeft()) {
                //RL:parent先向右旋转,grand再向左旋转
                this.rotateRight(parent);
                this.rotateLeft(grand);
            } else {
                //RR:grand向左旋转
                this.rotateLeft(grand);
            }
        }

    }

    /**
     * 向右旋转:示例如下
 *              G
     *      P
     * N
     */
    private void rotateRight(Node<E> grand) {
        //由于确定向右旋转,G P N 的关系已经确定
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        Node<E> node = parent.left;

        // 1.更新 G P节点间关系
        grand.left = child;
        parent.right = grand;

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
        // 3.更新高度:先更新G的高度,再更新P的高度
        updateHeight(grand);
        updateHeight(parent);

    }

    /**
     * 向左旋转:示例如下
     * G
     *      P
     *          N
     */
    private void rotateLeft(Node<E> grand) {
        //由于确定向右旋转,G P N 的关系已经确定
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        Node<E> node = parent.right;

        // 1.更新 G P节点间关系
        grand.right = child;
        parent.left = grand;
        // 2.更新G P及P的原left子节点的parent
        // 2.1 原G.parent作为P.parent
        parent.parent = grand.parent;
        if (grand.isLeft()) {
            grand.parent.left = parent;
        } else if (grand.isRight()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        // 2.2更新原P.child.parent
        if (null != child) {
            child.parent = grand;
        }
        // 2.3更新G.parent
        grand.parent = parent;
        // 3.更新高度:先更新G的高度,再更新P的高度
        updateHeight(grand);
        updateHeight(parent);
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
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        private Node<E> tallerNode() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (leftHeight < rightHeight) {
                return right;
            } else {
                //高度相等,返回和grand节点同方向的节点
                return isLeft() ? left : right;
                /*if (isLeft()) {
                    return left;
                }else{
                    return right;
                }*/
            }
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
