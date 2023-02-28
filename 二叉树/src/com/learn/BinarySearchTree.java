package com.learn;

import com.learn.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树:
 * 任意节点的左子树所有节点值小于当前节点值，右子树所有节点值大于当前节点值
 * <p>
 * 节点必须是可以比较的（直接实现Comparable接口,或者自定义比较器内容，如果是引用对象，引用对象要入二叉树的属性符合前面两种情况）
 * <p>
 * 应用：可以思考下，mysql数据库按照某个字段进行排序查询的过程
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    //public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeInfo {
    // 使用BinarySearchTree<E extends Comparable<E>> 方式，第三方调用必须保证添加的元素实现Comparable接口，不太友好
    // 可以增加比较器，由第三方确定比较规则，这样可以比较灵活调用
    private int size;
    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
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
            // 当 node == null ,退出循环,需要找到叶子节点并作为新节点的父节点，使用parent记录原叶子节点
            result = this.compare(element, node.element);
            parent = node;
            if (result > 0) {
                node = node.right;
            } else if (result < 0) {
                node = node.left;
            } else {
                node.element = element;// 是否有意义？有的，比如同龄不同名，直接替换新名
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
        if (null != comparator) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    private void checkElement(E element) {
        if (null == element) {
            throw new IllegalArgumentException("调用 add 函数参数有误!!");
        }
    }

    // 四种遍历方式: 前中后序遍历,层序遍历
    // 不局限于二叉搜索树，适用于所有二叉树

    /**
     * 前序遍历
     * 前序遍历:先遍历根节点，再遍历左子树，再遍历右子树
     * 对于左子树：先遍历左子树根节点，再遍历左子树的左子树，再遍历左子树的右子树。。。。
     * 建议画图走流程
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
     * 中序遍历
     * 先遍历左子树，再遍历根节点，再遍历右子树
     * 遍历左子树，先遍历左子树的左子树，再遍历左子树的根节点，再遍历左子树的右子树 ....
     * 对于二叉搜索树:中序遍历 各节点数据会从小到大，或者从大到小排序（由比较器比较规则控制）
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
     * 后序遍历
     * 先遍历左子树，再遍历右子树，最后遍历根节点
     * 遍历左子树：先遍历左子树的左子树，再遍历左子树的右子树，再遍历左子树的根节点
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
     * 层序遍历
     * 从根节点开始从上向下，从左向右一层一层的遍历
     * 遍历过程类似队列入队出队，使用队列实现
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

    // toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preorderTraversalToString(root, sb);
        return sb.toString();
    }

    private void preorderTraversalToString(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.element);
        preorderTraversalToString(node.left, sb);
        preorderTraversalToString(node.right, sb);
    }


    // 工具类打印器的使用
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
        // 添加打印父节点元素
       /* Node<E> newNode = (Node<E>) node;
        String parent = newNode.parent == null ? "null" : newNode.parent.element+"";
        return " parent_" + parent + " > ele_"+ newNode.element;*/
    }

    private static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * 除了根节点，任意节点的parent一定非空,而左右节点有可能是空的，比较适合提供如下实例方法
         */
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
