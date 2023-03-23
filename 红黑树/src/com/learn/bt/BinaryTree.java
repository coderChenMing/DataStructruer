package com.learn.bt;

import com.learn.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    protected static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        /**
         * 除了根节点，任意节点的parent一定非空,而 左右节点 有可能是空的，比较适合提供如下实例方法
         */
        protected Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChild() {
            return left != null && right != null;
        }

        public boolean isLeft() {
            return parent != null && this == parent.left;
        }

        public boolean isRight() {
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() {
            if (isLeft()) {
                return parent.right;
            }
            if (isRight()) {
                return parent.left;
            }
            return null;
        }
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空二叉树
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * <p>前序遍历
     * <p>前序遍历:先遍历根节点，再遍历左子树，再遍历右子树
     * <p>对于左子树：先遍历左子树根节点，再遍历左子树的左子树，再遍历左子树的右子树。。。。
     * <p>建议画图走流程
     */
    public void preorderTraversal() {
        Node<E> node = root;
        preorderTraversal(node);
    }

    private void preorderTraversal(Node<E> node) {
        if (null == node) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * <p>中序遍历
     * <p>先遍历左子树，再遍历根节点，再遍历右子树
     * <p>遍历左子树，先遍历左子树的左子树，再遍历左子树的根节点，再遍历左子树的右子树 ....
     * <p>对于二叉搜索树:中序遍历 各节点数据会从小到大，或者从大到小排序（由比较器比较规则控制）
     */
    public void inorderTraversal() {
        Node<E> node = root;
        inorderTraversal(node);
    }

    private void inorderTraversal(Node<E> node) {
        if (null == node) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    /**
     * <p>后序遍历
     * <p>先遍历左子树，再遍历右子树，最后遍历根节点
     * <p>遍历左子树：先遍历左子树的左子树，再遍历左子树的右子树，再遍历左子树的根节点
     */
    public void postorderTraversal() {
        Node<E> node = root;
        postorderTraversal(node);
    }

    private void postorderTraversal(Node<E> node) {
        if (null == node) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * <p>层序遍历
     * <p>从根节点开始从上向下，从左向右一层一层的遍历
     * <p>遍历过程类似队列入队出队，使用队列实现
     */
    public void levelOrderTraversal() {
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.element);
            // 从左向右，先向队尾加左
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 从左向右，后向队尾加右节点
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * <p>前序遍历增强
     */
    public void preorderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            // 如果你没有传，我就全部遍历打印
            preorderTraversal();
            return;
        }
        // 传了visitor,我就根据实现进行操作
        this.preorderTraversalExt(root, visitor);
    }

    private void preorderTraversalExt(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorderTraversalExt(node.left, visitor);
        preorderTraversalExt(node.right, visitor);

    }

    /**
     * <p>中序遍历增强
     */
    public void inorderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            // 如果你没有传，我就全部遍历打印
            inorderTraversal();
            return;
        }
        // 传了visitor,我就根据实现进行操作
        this.inorderTraversalExt(root, visitor);
    }

    private void inorderTraversalExt(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inorderTraversalExt(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inorderTraversalExt(node.right, visitor);

    }

    /**
     * <p>后序遍历增强
     */
    public void postorderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            // 如果你没有传，我就全部遍历打印
            postorderTraversal();
            return;
        }
        // 传了visitor,我就根据实现进行操作
        this.postorderTraversalExt(root, visitor);
    }

    private void postorderTraversalExt(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postorderTraversalExt(node.left, visitor);
        postorderTraversalExt(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * <p>层序遍历增强
     */
    public void levelOrderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            levelOrderTraversal();
            return;
        }
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (visitor.visit(node.element)) return;
            // 从左向右，先向队尾加左
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 从左向右，后向队尾加右节点
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * <p>计算二叉树的高度
     */
    public int height() {
        if (root == null) return 0;
        return height(root);
    }

    /**
     * <p>计算 二叉树 任一 节点的高度
     * <p>使用递归实现：画图归纳,任意节点高度=Math.max(左子节点高度，右子节点高度)+1
     */
    private int height(Node<E> node) {
        // 一致向左递归或者向右递归，最后到达叶子节点的左右节点 ,不存在，返回0
        if (node == null) return 0;//递归出口
        return 1 + Math.max(height(node.left), height(node.right));
    }


    /**
     * <p>层序遍历计算树高
     * <p>归纳: 初始height=0,每一层完成出队,height++,关键在于每一层节点全部出队，下一层的levelSize=queue.size()
     */
    public int height2() {
        //从根节点开始层序遍历
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;//记录树高
        int levelSize = 1;// 记录每一层节点个数,根节点所在第一层LevelSize=1
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelSize--;
            // 左右节点全部入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * <p>判断一颗 二叉树 是否是 完全二叉树
     * <p>完全二叉树 :节点的度可能是0,1,2 ，如果有度为1的节点只能有一个，且向左靠齐
     * <p>需要遍历整个 二叉树 的每个节点,根据左右子节点是否为空进行区分
     */
    public boolean isComplete() {
        if (root == null) return false;
        // 层序遍历方式判断是否完全二叉树
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (isLeaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // 一个节点，左子节点为空,右子节点不为空，一定不是完全二叉树
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // 当前出队节点的左子节点可能为空也可能不空,右子节点为空
                // 无论左子节点是否为空,只有保证之后出队的节点都是叶子节点才能是 完全二叉树
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * <p>对于中序遍历的 二叉树 获取任意一个节点的前驱节点
     * <p>测试数据
     * <p>向 二叉树依次添加 8 4 13 2 6 10 1 3 5 7 9 12 11
     * <p>先观察具有左右子树的节点，找其前驱节点
     * <p>再观察只具有左子树的节点，找其前驱节点
     * <p>再观察只有右子树的节点找其前驱节点
     * <p>再观察叶子节点找其前驱节点
     * <p>再观察根节点找其前驱节点
     * <p>归纳规律，同一代码
     */
    public Node<E> precursorNode(Node<E> node) {
        if (node == null) return null;
        Node<E> left = node.left;
        if (left != null) {
            while (left.right != null) {
                left = left.right;
            }
            return left;
        }
        // 三种情况:
        // 对于二叉树 8 4 13 2 6 10 1 3 5 7 9 12 11
        // 1.前驱节点为null,也可以视为没有节点.right=node.parent 比如1
        // 2.前驱节点为node.parent 比如3 7
        // 3.前驱结点.right=node.parent.parent... 比如9
        // 既然是多次.parent,可以使用while循环
        // 终止条件:node在parent的右子树中
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * <p>获取任意一个节点的 后继节点
     * <p>测试数据
     * <p>向 二叉树依次添加 4 1 8 2 7 10 3 5 9 11
     */
    public Node<E> successorNode(Node<E> node) {
        Node<E> right = node.right;
        if (right != null) {
            // 节点 8的后继节点为9
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }
        // 执行到这里,说明该节点无右子节点
        // 比如节点3 的后继节点是 4  3.parent.parent.parent... =4.left  终止
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        // 对于根节点 ，他的后继节点是右子树最小的节点
        // 对于节点11 ，11.parent.parent..一直到parent==null终止,他的后继节点为null
        return node.parent;
    }

    // 工具类打印器的使用
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((BinarySearchTree.Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((BinarySearchTree.Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        //return ((BinarySearchTree.Node<E>) node).element;
        // 添加打印父节点元素
        /*Node<E> newNode = (Node<E>) node;
        String parent = newNode.parent == null ? "null" : newNode.parent.element + "";
        return newNode.element + "_p( " + parent + " )";*/
        return node;//由AVLTree和RBTree的节点重写toString方法决定
    }
}
